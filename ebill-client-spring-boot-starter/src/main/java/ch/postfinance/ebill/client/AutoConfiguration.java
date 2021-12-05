package ch.postfinance.ebill.client;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import ch.postfinance.ebill.webservice.B2BService;
import lombok.NonNull;
import lombok.SneakyThrows;

@Configuration
public class AutoConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "ebill.client")
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
	@SneakyThrows
	public EBillClient eBillClient(@NonNull EBillClientProperties eBillClientProperties, @NonNull Jaxb2Marshaller marshaller) {
		EBillClientImpl eBillClient = new EBillClientImpl();
		eBillClient.setDefaultUri(eBillClientProperties.getUri());
		eBillClient.setMarshaller(marshaller);
		eBillClient.setUnmarshaller(marshaller);
		MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
		SaajSoapMessageFactory newSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
		eBillClient.setMessageFactory(newSoapMessageFactory);
//		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
//		httpComponentsMessageSender.setCredentials(new UsernamePasswordCredentials("user", "pw"));
//		eBillClient.setMessageSender(httpComponentsMessageSender);
		Wss4jSecurityInterceptor w = new Wss4jSecurityInterceptor();
		w.setSecurementActions("UsernameToken");
		w.setSecurementUsername("someuser");
		w.setSecurementPassword("somepw");
		ClientInterceptor[] newInterceptors = new ClientInterceptor[1];
		newInterceptors[0] = w;
		eBillClient.setInterceptors(newInterceptors);
		return eBillClient;
	}

}
