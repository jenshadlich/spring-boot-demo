package de.jeha.demo.springboot.logstash;

import ch.qos.logback.access.spi.IAccessEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import net.logstash.logback.composite.AbstractFieldJsonProvider;
import net.logstash.logback.composite.JsonWritingUtils;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
public class UserAgentJsonProvider extends AbstractFieldJsonProvider<IAccessEvent> {

    public static final String FIELD_HTTP_USER_AGENT = "http_user_agent";

    public UserAgentJsonProvider() {
        setFieldName(FIELD_HTTP_USER_AGENT);
    }

    @Override
    public void writeTo(JsonGenerator generator, IAccessEvent event) throws IOException {
        final String userAgent = event.getRequestHeader("User-Agent");
        if (!IAccessEvent.NA.equals(userAgent)) {
            JsonWritingUtils.writeStringField(generator, getFieldName(), userAgent);
        }
    }

}