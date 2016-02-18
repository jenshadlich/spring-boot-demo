package de.jeha.demo.springboot.logstash;

import com.fasterxml.jackson.core.JsonGenerator;
import net.logstash.logback.decorate.JsonGeneratorDecorator;

/**
 * @author jenshadlich@googlemail.com
 */
public class PrettyPrintingDecorator implements JsonGeneratorDecorator {

    @Override
    public JsonGenerator decorate(JsonGenerator generator) {
        return generator.useDefaultPrettyPrinter();
    }

}
