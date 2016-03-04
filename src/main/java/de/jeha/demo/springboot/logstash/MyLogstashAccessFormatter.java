package de.jeha.demo.springboot.logstash;

import ch.qos.logback.core.spi.ContextAware;
import net.logstash.logback.LogstashAccessFormatter;
import net.logstash.logback.composite.accessevent.AccessEventPatternJsonProvider;

/**
 * @author jenshadlich@googlemail.com
 */
public class MyLogstashAccessFormatter extends LogstashAccessFormatter {

    public MyLogstashAccessFormatter(ContextAware declaredOrigin) {
        super(declaredOrigin);
        addPattern("tags", "access_log");
        setTimeZone("UTC");
    }

    private void addPattern(String key, String value) {
        AccessEventPatternJsonProvider provider = new AccessEventPatternJsonProvider();
        provider.setPattern(String.format("{\"%s\": \"%s\"}", key, value));
        getProviders().addPattern(provider);
    }

    @Override
    public void start() {
        fieldNames.setMessage("message");
        fieldNames.setFieldsMethod("http_method");
        fieldNames.setFieldsProtocol("http_protocol");
        fieldNames.setFieldsStatusCode("http_status_code");
        fieldNames.setFieldsRequestedUrl("http_requested_url");
        fieldNames.setFieldsRequestedUri("http_requested_uri");
        fieldNames.setFieldsRemoteHost("http_remote_host");
        fieldNames.setFieldsContentLength("http_content_length");
        fieldNames.setFieldsElapsedTime("http_elapsed_time");
        fieldNames.setFieldsRemoteUser("http_remote_user");
        fieldNames.setFieldsRequestHeaders("http_request_headers");
        fieldNames.setFieldsResponseHeaders("http_response_headers");
        fieldNames.setFieldsHostname("http_host_name");
        super.start();
    }

}
