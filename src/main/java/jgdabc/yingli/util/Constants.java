package jgdabc.yingli.util;

import java.math.BigDecimal;

public class Constants {
    public static final  String REDIS_RANK_KEY  =  "yingli:user:rank";
    public static final  String REDIS_SMS_REGISTER_KEY    = "yingli:userRegister:sms";
    public static final String REDIS_SMS_LOGIN_KEY  =  "yingli:userLogin:sms";
    public static final  String SECRET_MD5_SALT  = "abbj1abndjn1KAnd0kanjkdnjk1nAJKB2NDJKNJ2Kanbb3dxjnbjknjk4NASDKNKLnak";
    public static final   BigDecimal NEW_USER_AVAILABLE_MONEY = BigDecimal.valueOf(50);

    public static final  String  NEW_USER_FINANCE_SUCCESS = "新用户插入成功";

    public static  final  String NEW_USER_FINANCE_FAILED  =  "新的用户插入失败";

    public static final  String JWT_SECRERT  =  "dnasjknasjd2fnknk5NMASLmdamnk1nm";

    public static final  String REDIS_JWT_KEY = "tokenforjwt";


}
