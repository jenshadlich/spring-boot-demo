package de.jeha.demo.springboot.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
@RestController
public class ThrowExceptionResource {

    private static final Logger LOG = LoggerFactory.getLogger(ThrowExceptionResource.class);

    @RequestMapping(value = "/throwException", produces = "application/json")
    public String throwException() throws IOException {
        RuntimeException e = new RuntimeException("This exception was thrown by intention!");
        LOG.error("", e);
        throw e;
    }

}
