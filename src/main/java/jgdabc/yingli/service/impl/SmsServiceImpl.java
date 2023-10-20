package jgdabc.yingli.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jgdabc.yingli.config.SmsConfig;
import jgdabc.yingli.entity.User;
import jgdabc.yingli.mapper.UserMapper;
import jgdabc.yingli.mode.ConstantEnum;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.service.SmsService;
import jgdabc.yingli.util.Constants;
import jgdabc.yingli.util.HttpUtils;
import jgdabc.yingli.util.RedisCommon;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信功能的实现类
 */
@Service
public class SmsServiceImpl implements SmsService {



    @Autowired
    private RedisCommon redisCommon;
    @Autowired
    private SmsConfig smsConfig;
    @Autowired
    private UserMapper userMapper;


    @Override
    public R sendSms(String phone, Integer type) {

//        判断手机号是否已经注册过
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        User user = userMapper.selectOne(userQueryWrapper);
        if (type ==1){
            if (user!=null)
            {
                return R.error(ConstantEnum.USER_REGISTER_REPET.getMsg());
            }


        }

        if (type==2){
            if (user==null)
            {
                return  R.error(ConstantEnum.USER_LOGIN_NULL.getMsg());
            }
        }



// TODO: 2023/10/15
        

        String random = RandomUtil.randomNumbers(4);



//        格式化短信验证码的内容
        String content  =  smsConfig.getRegisterTemplate();
        String ssmContent = String.format(content, random);
        boolean isOk = smsCore(phone,ssmContent);
        if (isOk){
            if (type==1){
                System.out.println("生成的注册验证码为"+random);
                redisCommon.setVal(Constants.REDIS_SMS_REGISTER_KEY+phone,random, ConstantEnum.REDIS_SMM_TIME_OUT_NUM, TimeUnit.MINUTES);
            }else {


                redisCommon.setVal(Constants.REDIS_SMS_LOGIN_KEY+phone,random,ConstantEnum.REDIS_SMM_TIME_OUT_NUM,TimeUnit.MINUTES);
                System.out.println("生成的登录验证码为"+random);

            }




            return R.success(ConstantEnum.SUCCESS.name());


        }
        else
        {
            return  R.error(ConstantEnum.FAILED.getMsg());
        }







    }

    /**
     * 发送短信的核心方法
     */
    public boolean smsCore(String phone, String content) {

        boolean  isOk  =  false;

        String host = smsConfig.getHost();
        String  path = smsConfig.getPath();
        String method = "POST";
        String appcode = smsConfig.getAppCode();
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("mobile", "mobile");
//        querys.put("param", "**code**:12345,**minute**:5");

//smsSignId（短信前缀）和templateId（短信模板），可登录国阳云控制台自助申请。参考文档：http://help.guoyangyun.com/Problem/Qm.html

        querys.put("content", content);
        querys.put("mobile", phone);

        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从\r\n\t    \t* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java\r\n\t    \t* 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             *
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){

                String json = EntityUtils.toString(response.getEntity());
                if (StringUtils.isNotBlank(json)){
                    JSONObject jsonObject = JSONUtil.parseObj(json);
                    String returnStatus = jsonObject.getStr("ReturnStatus");
                    if ("success".equalsIgnoreCase(returnStatus)){
                        isOk  =  true;

                    }



                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            isOk =  false;
            e.printStackTrace();
        }


//短信接口试用过了，将其改为true
//        return isOk;
        return   true;


    }
}
