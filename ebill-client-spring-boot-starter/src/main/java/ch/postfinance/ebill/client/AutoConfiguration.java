package ch.postfinance.ebill.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import b2bservice.ebill.swisspost.ch.B2BService;
import b2bservice.ebill.swisspost.ch.B2BService_Service;
import ch.postfinance.ebill.schema.processprotocol.ProcessProtocolMarshaller;
import ch.postfinance.ebill.schema.registration.RegistrationMarshaller;
import ch.postfinance.ebill.schema.yellowbill.YellowBillInvoiceMarshaller;
import lombok.NonNull;

@Configuration
public class AutoConfiguration {

	private static final String PROPERTIES_PREFIX = "ebill.client";

	@Bean
	@ConfigurationProperties(prefix = PROPERTIES_PREFIX)
	public EBillClientProperties eBillClientProperties() {
		return new EBillClientProperties();
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

		Map<String, Object> requestCtx = ((BindingProvider) port).getRequestContext();
		requestCtx.put("ws-security.username", eBillClientProperties.getUsername());
		requestCtx.put("ws-security.password", eBillClientProperties.getPassword());
		return new EBillClientImpl(port, new RegistrationMarshaller(), new ProcessProtocolMarshaller(), new YellowBillInvoiceMarshaller());
	}
}
