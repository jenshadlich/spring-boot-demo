package de.jeha.demo.springboot.logstash;

import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.core.spi.ContextAware;
import net.logstash.logback.composite.ContextJsonProvider;
import net.logstash.logback.composite.FieldNamesAware;
import net.logstash.logback.composite.JsonProvider;
import net.logstash.logback.composite.LogstashVersionJsonProvider;
import net.logstash.logback.composite.accessevent.*;
import net.logstash.logback.fieldnames.LogstashAccessFieldNames;

/**
 * @author jenshadlich@googlemail.com
 */
public class AccessLogAccessFormatter extends AccessEventCompositeJsonFormatter {

    private final LogstashAccessFieldNames fieldNames;

    {
        fieldNames = new LogstashAccessFieldNames();
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
    }

    private final AccessEventFormattedTimestampJsonProvider timestampProvider = new AccessEventFormattedTimestampJsonProvider();
    private final LogstashVersionJsonProvider<IAccessEvent> versionProvider = new LogstashVersionJsonProvider<IAccessEvent>();
    private final AccessMessageJsonProvider messageProvider = new AccessMessageJsonProvider();
    private final MethodJsonProvider methodProvider = new MethodJsonProvider();
    private final ProtocolJsonProvider protocolProvider = new ProtocolJsonProvider();
    private final StatusCodeJsonProvider statusCodeProvider = new StatusCodeJsonProvider();
    private final RequestedUrlJsonProvider requestedUrlProvider = new RequestedUrlJsonProvider();
    private final RequestedUriJsonProvider requestedUriProvider = new RequestedUriJsonProvider();
    private final RemoteHostJsonProvider remoteHostProvider = new RemoteHostJsonProvider();
    private final HostnameJsonProvider hostnameProvider = new HostnameJsonProvider();
    private final RemoteUserJsonProvider remoteUserProvider = new RemoteUserJsonProvider();
    private final ContentLengthJsonProvider contentLengthProvider = new ContentLengthJsonProvider();
    private final ElapsedTimeJsonProvider elapsedTimeProvider = new ElapsedTimeJsonProvider();
    private final RequestHeadersJsonProvider requestHeadersProvider = new RequestHeadersJsonProvider();
    private final ResponseHeadersJsonProvider responseHeadersProvider = new ResponseHeadersJsonProvider();
    private final ContextJsonProvider<IAccessEvent> contextProvider = new ContextJsonProvider<IAccessEvent>();


    public AccessLogAccessFormatter(ContextAware declaredOrigin) {
        super(declaredOrigin);

        getProviders().addTimestamp(this.timestampProvider);
        getProviders().addVersion(this.versionProvider);
        getProviders().addAccessMessage(this.messageProvider);
        getProviders().addMethod(this.methodProvider);
        getProviders().addProtocol(this.protocolProvider);
        getProviders().addStatusCode(this.statusCodeProvider);
        getProviders().addRequestedUrl(this.requestedUrlProvider);
        getProviders().addRequestedUri(this.requestedUriProvider);
        getProviders().addRemoteHost(this.remoteHostProvider);
        getProviders().addHostname(this.hostnameProvider);
        //getProviders().addRemoteUser(this.remoteUserProvider);
        getProviders().addContentLength(this.contentLengthProvider);
        getProviders().addElapsedTime(this.elapsedTimeProvider);
        //getProviders().addRequestHeaders(this.requestHeadersProvider);
        //getProviders().addResponseHeaders(this.responseHeadersProvider);
        getProviders().addContext(this.contextProvider);

        timestampProvider.setTimeZone("UTC");
        messageProvider.setTimeZone("UTC");

        addPattern("tags", "access_log");
    }

    private void addPattern(String key, String value) {
        AccessEventPatternJsonProvider provider = new AccessEventPatternJsonProvider();
        provider.setPattern(String.format("{\"%s\": \"%s\"}", key, value));
        addInfo("set pattern '" + provider.getPattern() + "'");
        getProviders().addPattern(provider);
    }

    @Override
    public void start() {
        configureProviderFieldNames();
        super.start();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void configureProviderFieldNames() {
        for (JsonProvider<IAccessEvent> provider : getProviders().getProviders()) {
            if (provider instanceof FieldNamesAware) {
                ((FieldNamesAware) provider).setFieldNames(fieldNames);
            }
        }
    }

    public void addProvider(JsonProvider<IAccessEvent> provider) {
        getProviders().addProvider(provider);
    }

    @Override
    public AccessEventJsonProviders getProviders() {
        return (AccessEventJsonProviders) super.getProviders();
    }

}
