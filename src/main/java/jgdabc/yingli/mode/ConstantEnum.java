package jgdabc.yingli.mode;

import lombok.Data;
import lombok.Getter;


public enum ConstantEnum {


    SUCCESS("成功",200),
    FAILED("失败",400),

    USER_REGISTER_REPET("手机号已经被注册过了",400),

    USER_LOGIN_NULL("用户没有找到",1002),

    PHONE_FOEMAT_ERR("手机号格式错误",1003),
    PHONE_REPEATE_ERR("注册手机号已经存在",1004),

    LOG_CODE_VERIFY("验证码校验通过", 1005),

    LOGIN_PASS_VERIFY_FAILED("用户登录密码错误", 1006),

    CODE_TIME_OUT("验证码已经过期，请重新获取",1007),


    CODE_VERIFY_FAILED("验证码错误", 1008),

    TOKEN_OUT_DATA("token过期",1009),

    REALNAME_AU_REPEATE("已经认证过了", 10010),

    REALNAME_AU_SUCCESS("实名认证成功,", 10011),
    REALNAME_AU_FAILED("实名认证失败", 10012),
    PUDATE_FAILED("数据更新失败",10013);






//    新手宝
    public static final int  PRODUCT_TYPE_XIN_BAO  = 0;
//    优选
    public static final  int PRUDUCT_TYPE_YOU_XUAN  = 1;
//    散标

    public static final  int PRODUCT_TYPE_SAN_BIAO  =  2;


    public static final  int REDIS_SMM_TIME_OUT_NUM  =  3;

    public static final int REDIS_JWT_TOKEN_EXIST_TIME  =  30;

    @Getter
    String msg;
    @Getter
    Integer code;

    ConstantEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
