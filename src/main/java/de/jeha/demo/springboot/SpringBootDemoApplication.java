package de.jeha.demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

/**
 * @author jenshadlich@googlemail.com
 */
@SpringBootApplication(exclude = {JmsAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class SpringBootDemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(SpringBootDemoApplication.class);
        application.run(args);
    }

}
