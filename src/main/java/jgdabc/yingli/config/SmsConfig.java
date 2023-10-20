package jgdabc.yingli.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "sms" )
public class SmsConfig {
    private String host;
    private String path;
    private String appCode;
    private String registerTemplate;
    private String loginTemplate;
}
