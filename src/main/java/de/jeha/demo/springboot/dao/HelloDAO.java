package de.jeha.demo.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author jenshadlich@googlemail.com
 */
@Component
public class HelloDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getHelloString() {
        return jdbcTemplate.queryForObject("SELECT val FROM hello_string", String.class);
    }

}
