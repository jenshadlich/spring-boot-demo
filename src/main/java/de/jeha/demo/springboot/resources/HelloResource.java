package de.jeha.demo.springboot.resources;

import de.jeha.demo.springboot.api.HelloResponse;
import de.jeha.demo.springboot.dao.HelloDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
@RestController
public class HelloResource {

    private static final Logger LOG = LoggerFactory.getLogger(HelloResource.class);

    @Autowired
    private HelloDAO helloDAO;

    @RequestMapping(value = "/hello", produces = "application/json")
    public HelloResponse hello() throws IOException {
        LOG.info("hello@info");
        LOG.debug("hello@debug");
        return new HelloResponse(helloDAO.getHelloString());
    }

}
