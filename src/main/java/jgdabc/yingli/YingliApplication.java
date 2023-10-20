package jgdabc.yingli;

import jgdabc.yingli.config.RealnameConfig;
import jgdabc.yingli.config.SmsConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableConfigurationProperties({SmsConfig.class, RealnameConfig.class})
@MapperScan(basePackages = "jgdabc.yingli.mapper")
@SpringBootApplication
public class YingliApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingliApplication.class, args);
    }

}
