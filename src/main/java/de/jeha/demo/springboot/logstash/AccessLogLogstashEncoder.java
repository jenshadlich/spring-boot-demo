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
        return new AccessLogAccessFormatter(this);
    }

    @Override
    public void start() {
        super.start();
    }

    public void setApplicationName(String applicationName) {
        addInfo("set application_name to '" + applicationName + "'");
        addPatternProvider("application_name", applicationName);
    }

    public void setApplicationVersion(String applicationVersion) {
        addInfo("set application_version to '" + applicationVersion + "'");
        addPatternProvider("application_version", applicationVersion);
    }

    private void addPatternProvider(String key, String value) {
        AccessEventPatternJsonProvider provider = new AccessEventPatternJsonProvider();
        provider.setPattern(String.format("{\"%s\": \"%s\"}", key, value));
        addInfo("set pattern '" + provider.getPattern() + "'");
        getProviders().addProvider(provider);
    }

}
