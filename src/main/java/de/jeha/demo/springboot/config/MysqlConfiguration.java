package de.jeha.demo.springboot.config;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jenshadlich@googlemail.com
 */
@Configuration
@ConfigurationProperties(prefix = "mysql")
public class MysqlConfiguration {

    @NotEmpty
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
