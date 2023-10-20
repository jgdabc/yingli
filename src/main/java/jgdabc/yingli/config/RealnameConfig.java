package jgdabc.yingli.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "realname" )
public class RealnameConfig {

    private String url;
    private String appCode;
}
