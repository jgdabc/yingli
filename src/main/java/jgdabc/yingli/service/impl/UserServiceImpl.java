package jgdabc.yingli.service.impl;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jgdabc.yingli.JwtUtils;
import jgdabc.yingli.config.RealnameConfig;
import jgdabc.yingli.entity.User;
import jgdabc.yingli.mapper.UserMapper;
import jgdabc.yingli.mode.ConstantEnum;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.mode.dto.UserRankDto;
import jgdabc.yingli.mode.query.Realname;
import jgdabc.yingli.mode.query.UserQuery;
import jgdabc.yingli.service.FinanceAccountService;
import jgdabc.yingli.service.UserService;
import jgdabc.yingli.util.Constants;
import jgdabc.yingli.util.JsonUtils;
import jgdabc.yingli.util.RealnameUtil;
import jgdabc.yingli.util.RedisCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RedisCommon redisCommon;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RealnameConfig realnameConfig;


    @Autowired
    private FinanceAccountService financeAccountService;

    @Override
    public List<UserRankDto> loadUserRank() {
        return userMapper.loadUserRank();
    }


    public List selectQueryUsers() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByAsc("id").last("limit 0,10");
        List<User> users = userMapper.selectList(userQueryWrapper);
        return users;
    }

    @Transactional(rollbackFor = Exception.class)

    @Override
    public R saveUsers(UserQuery userQuery) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", userQuery.getPhone());
        User userSelQuery = userMapper.selectOne(userQueryWrapper);
        if (!StrUtil.isEmptyIfStr(userSelQuery)) {
            return R.error(ConstantEnum.PHONE_REPEATE_ERR.getMsg());

        }
        Object value = redisCommon.getValue(Constants.REDIS_SMS_REGISTER_KEY + userQuery.getPhone());
        if (userQuery.getCode().equals(value)) {

            log.info(ConstantEnum.LOG_CODE_VERIFY.getMsg());
        }
        String mdPassWordWithSalt = DigestUtils.md5DigestAsHex((userQuery.getSecret() + Constants.SECRET_MD5_SALT).getBytes());

//        加密密码
        User user = new User();
        user.setPhone(userQuery.getPhone());
        user.setLoginPassword(mdPassWordWithSalt);

        user.setAddTime(new Date());
        int insertResult = userMapper.insert(user);
        Integer userId = user.getId();
        int saveResult = financeAccountService.saveUserFinance(Constants.NEW_USER_AVAILABLE_MONEY, userId);
        if (insertResult > 0 && saveResult > 0) {
            return R.success(Constants.NEW_USER_FINANCE_SUCCESS);
        }
        return R.error(Constants.NEW_USER_FINANCE_FAILED);


    }

    @Override
    public R userLogin(UserQuery userQuery) {
        String verifyCode = userQuery.getCode();
        String phone = userQuery.getPhone();
        String secret = userQuery.getSecret();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", phone);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            return R.error(ConstantEnum.USER_LOGIN_NULL.getMsg());
        }
        String loginPassMd5 = DigestUtils.md5DigestAsHex((secret + Constants.SECRET_MD5_SALT).getBytes());
        if (!loginPassMd5.equals(user.getLoginPassword())) {
            return R.error(ConstantEnum.LOGIN_PASS_VERIFY_FAILED.getMsg());
        }
        Object value = redisCommon.getValue(Constants.REDIS_SMS_LOGIN_KEY + phone);
        if (value == null) {
            return R.error(ConstantEnum.CODE_TIME_OUT.getMsg());
        }

        if (!value.equals(verifyCode)) {

            return R.error(ConstantEnum.CODE_VERIFY_FAILED.getMsg());
        }

        User userJson = new User();
        userJson.setId(user.getId());
        userJson.setPhone(user.getPhone());
        userJson.setName(user.getName());
        String jwtUser = JwtUtils.createJwt(JsonUtils.toJson(userJson));
//        将jwt存储到redis当中
        redisCommon.setVal(Constants.REDIS_JWT_KEY + phone, jwtUser, ConstantEnum.REDIS_JWT_TOKEN_EXIST_TIME, TimeUnit.MINUTES);


        return R.success(jwtUser);


    }


//        进行实名认证





    @Override
    public R remealMe(Realname realname) {
        String name  =  realname.getName();
        String idCard = realname.getIdCard();
        String phone  =  realname.getPhone();
        if (StrUtil.isNotEmpty(name)&&name.length()>1&& IdcardUtil.isValidCard(idCard))
        {
            Integer uid  =  0;
            //先判断是否做过实名认证
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("phone",phone);
            User user = userMapper.selectOne(userQueryWrapper);

            if (user == null) {
                return  R.error(ConstantEnum.USER_LOGIN_NULL.getMsg());
            }
            if (StrUtil.isNotEmpty(user.getName())||StrUtil.isNotEmpty(user.getIdCard()))
            {
                return  R.error(ConstantEnum.REALNAME_AU_REPEATE.getMsg());
            }
            boolean realnameResult = RealnameUtil.invokeIdCardApi(realnameConfig.getUrl(), realnameConfig.getAppCode(), realname.getName(), realname.getIdCard());
            if (realnameResult){
//                认证成功后更新数据库
                UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();

                userUpdateWrapper.set("name",name);
                userUpdateWrapper.set("id_card", idCard);
//                userUpdateWrapper.eq("id",uid);
                userUpdateWrapper.eq("phone",phone);

                int row = userMapper.update(null, userUpdateWrapper);
                if (row!=1){
//                    更新redis中的数据
                    return R.error(ConstantEnum.PUDATE_FAILED.getMsg());


                }


                return R.success(ConstantEnum.REALNAME_AU_SUCCESS);
            }




        }

        return R.error(ConstantEnum.REALNAME_AU_FAILED.getMsg());

    }

    @Override
    public User loadUser(QueryWrapper<User> userQueryWrapper) {
        return userMapper.selectOne(userQueryWrapper);
    }
}
