package de.jeha.demo.springboot.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
@RestController
public class ThrowExceptionResource {

    @RequestMapping(value = "/throwException", produces = "application/json")
    public String throwException() throws IOException {
        throw new RuntimeException("This exception was thrown by intention!");
    }

}
