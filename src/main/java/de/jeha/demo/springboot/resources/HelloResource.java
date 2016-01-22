package de.jeha.demo.springboot.resources;

import de.jeha.demo.springboot.api.HelloResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
@RestController
public class HelloResource {

    @RequestMapping(value = "/hello", produces = "application/json")
    public HelloResponse hello() throws IOException {
        return new HelloResponse("Hello World!");
    }
}
