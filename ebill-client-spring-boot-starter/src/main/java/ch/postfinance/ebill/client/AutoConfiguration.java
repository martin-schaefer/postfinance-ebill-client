package ch.postfinance.ebill.client;

import b2bservice.ebill.swisspost.ch.B2BService;
import b2bservice.ebill.swisspost.ch.B2BService_Service;
import ch.postfinance.ebill.schema.processprotocol.ProcessProtocolMarshaller;
import ch.postfinance.ebill.schema.registration.RegistrationMarshaller;
import ch.postfinance.ebill.schema.yellowbill.YellowBillInvoiceMarshaller;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Configuration
public class AutoConfiguration {

    @Value("#{systemEnvironment['postFiUsername']}")
    private String postFiUsername;
    @Value("#{systemEnvironment['postFiPassword']}")
    private String postFiPassword;

    @Bean
    @ConfigurationProperties(prefix = "ebill.client")
    public EBillClientProperties eBillClientProperties() {
        EBillClientProperties properties = new EBillClientProperties();
        properties.setUserName(postFiUsername);
        properties.setPassWord(postFiPassword);
        return properties;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(B2BService.class.getPackageName());
        return marshaller;
    }

    @Bean
    public EBillClient eBillClient(@NonNull EBillClientProperties eBillClientProperties) throws MalformedURLException {

        B2BService_Service service = new B2BService_Service(new URL(eBillClientProperties.getUri()), B2BService_Service.SERVICE);
        B2BService port = service.getUserNamePassword();

        Map<String, Object> requestCtx = ((BindingProvider)port).getRequestContext();
        requestCtx.put("ws-security.username", eBillClientProperties.getUserName());
        requestCtx.put("ws-security.password", eBillClientProperties.getPassWord());
        return new EBillClientImpl(port, new RegistrationMarshaller(), new ProcessProtocolMarshaller(), new YellowBillInvoiceMarshaller());
    }
}
