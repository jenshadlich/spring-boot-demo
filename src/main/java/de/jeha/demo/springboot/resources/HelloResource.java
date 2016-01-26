package de.jeha.demo.springboot.resources;

import de.jeha.demo.springboot.api.HelloResponse;
import de.jeha.demo.springboot.dao.HelloDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
@RestController
public class HelloResource {

    @Autowired
    private HelloDAO helloDAO;

    @RequestMapping(value = "/hello", produces = "application/json")
    public HelloResponse hello() throws IOException {
        return new HelloResponse(helloDAO.getHelloString());
    }

}
