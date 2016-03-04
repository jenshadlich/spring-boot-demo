package de.jeha.demo.springboot.logstash;

import ch.qos.logback.access.spi.IAccessEvent;
import net.logstash.logback.composite.CompositeJsonFormatter;
import net.logstash.logback.composite.accessevent.AccessEventPatternJsonProvider;
import net.logstash.logback.encoder.AccessEventCompositeJsonEncoder;

/**
 * @author jenshadlich@googlemail.com
 */
public class AccessLogLogstashEncoder extends AccessEventCompositeJsonEncoder {

    @Override
    protected CompositeJsonFormatter<IAccessEvent> createFormatter() {
        return new MyLogstashAccessFormatter(this);
    }

    @Override
    public void start() {
        super.start();
    }

    public void setApplicationName(String applicationName) {
        addPatternProvider("application_name", applicationName);
    }

    public void setApplicationVersion(String applicationVersion) {
        addPatternProvider("application_version", applicationVersion);
    }

    private void addPatternProvider(String key, String value) {
        AccessEventPatternJsonProvider provider = new AccessEventPatternJsonProvider();
        provider.setPattern(String.format("{\"%s\": \"%s\"}", key, value));
        getProviders().addProvider(provider);
    }

}
