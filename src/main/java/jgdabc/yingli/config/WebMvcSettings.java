package jgdabc.yingli.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  WebMvcSettings:相当于SpringMVC的配置文件
 */
@Configuration
public class WebMvcSettings  implements WebMvcConfigurer {
     @Autowired
     private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] addPath = {"/user/realname"};
       registry.addInterceptor(tokenInterceptor).addPathPatterns(addPath);
    }

    /**
     * 全局跨域处理
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("===========addCorsMappings===========");
        registry.addMapping("/**") //拦截的请求地址，应用跨域处理
                .allowedOriginPatterns("*")   //允许的请求来源， 这个地址的请求，应用跨域处理
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") //可以跨域的请求方式
                .allowCredentials(true)  //cookie
                .maxAge(3600)            //秒， 预检请求的有效期
                .allowedHeaders("*");    //允许包含的请求头。
    }
}
