package de.jeha.demo.springboot.resources;

import de.jeha.demo.springboot.api.HelloResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

/**
 * @author jenshadlich@googlemail.com
 */
@RestController
public class HelloResource {

    @RequestMapping(value = "/hello", produces = "application/json")
    public HelloResponse hello(Principal principal) throws IOException {
        return new HelloResponse("Hello " + principal.getName() + "!");
    }

}
