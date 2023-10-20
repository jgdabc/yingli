package jgdabc.yingli.config;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jgdabc.yingli.JwtUtils;
import jgdabc.yingli.entity.User;
import jgdabc.yingli.mode.ConstantEnum;
import jgdabc.yingli.mode.R;
import jgdabc.yingli.util.Constants;
import jgdabc.yingli.util.JsonUtils;
import jgdabc.yingli.util.RedisCommon;
import jgdabc.yingli.util.WirterOutUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisCommon redisCommon;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if ("OPTIONS".equals(request.getMethod())){
                return  true;
            }
        String jwtAuth = request.getHeader("jwtAuth");
            if (StrUtil.isNotBlank(jwtAuth)){


                String userJson = JwtUtils.parseJwt(jwtAuth);
                User user = JsonUtils.toObject(userJson, User.class);//反序列化到对象
                Object jwtUserToken = redisCommon.getValue(Constants.REDIS_JWT_KEY + user.getPhone());
                if (jwtUserToken!=null)
                {
                    if (jwtUserToken.equals(jwtAuth)){
                        return  true;
                    }
                }else {
                   R tokenResult  = R.error(ConstantEnum.TOKEN_OUT_DATA.getCode());
                    WirterOutUtil.write(response,tokenResult);


                }



            }
            return  false;

    }
}
