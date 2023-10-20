package jgdabc.yingli.controller;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jgdabc.yingli.JwtUtils;
import jgdabc.yingli.convert.ConvertUserRank;
import jgdabc.yingli.entity.User;
import jgdabc.yingli.mapper.UserMapper;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.mode.dto.UserRankDto;
import jgdabc.yingli.mode.query.Realname;
import jgdabc.yingli.mode.query.UserQuery;
import jgdabc.yingli.mode.vo.UserRankVo;
import jgdabc.yingli.service.UserService;
import jgdabc.yingli.util.Constants;
import jgdabc.yingli.util.JsonUtils;
import jgdabc.yingli.util.RedisCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Set;
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    RedisCommon redisCommon;
    @Autowired
    private ConvertUserRank userRank;
    @GetMapping("/loadRealName")
    public R loadRealName(@RequestHeader("jwtAuth") String jwtAuth){
        if (StrUtil.isNotEmpty(jwtAuth)){
            String userJson = JwtUtils.parseJwt(jwtAuth);
            User user = JsonUtils.toObject(userJson, User.class);//反序列化到对象
            String phone = user.getPhone();
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("phone",phone);
            User userReal  =   userService.loadUser(userQueryWrapper);
            String name = userReal.getName();



            return  R.success(name);
        }
        return  R.error("");


    }

    @GetMapping("/users")
    public List<User> query(){
       return  userService.selectQueryUsers();


    }


    @GetMapping("/loadRankList")
    public R loadRankList(){
        Set zetValueOfRange = redisCommon.getZetValueOfRange(Constants.REDIS_RANK_KEY, 0l, 2l);
        List list = zetValueOfRange.stream().toList();


        return  R.success(list);





    }

    @PostMapping("/user/register/")
    public R registerUser(@RequestBody UserQuery userQuery) {

            return   userService.saveUsers(userQuery);

    }
    @PostMapping("/user/login/")
    public R userLogin(@RequestBody UserQuery userQuery, HttpServletRequest request){



        log.info("执行登录功能");





        return  userService.userLogin(userQuery);
    }
    @PostMapping("/user/realname")
    public  R realName(@RequestBody Realname realname){

       return  userService.remealMe(realname);


    }


}
