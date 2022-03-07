package ch.postfinance.ebill.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ch.postfinance.ebill.webservice.ExecutePing;
import ch.postfinance.ebill.webservice.ExecutePingResponse;
import ch.postfinance.ebill.webservice.ObjectFactory;

@SpringBootTest(properties = { "ebill.client.uri=https://ebill-ki.postfinance.ch/B2BService/B2BService.svc", "logging.level.org.springframework.ws.client=TRACE" })
public class EBillClientIT {

	@Autowired
	private EBillClient eBillClient;

	@Test
	public void executePing() {
		// given
		ExecutePing executePing = new ExecutePing();
		executePing.setBillerID("41101000011707505");
		ObjectFactory factory = new ObjectFactory();
		executePing.setExceptionTest(
				factory.createExecutePingExceptionTest(false));
		// when
		ExecutePingResponse executeResponsePing = eBillClient.executePing(executePing);

		// then
		assertThat(executeResponsePing).isNotNull();
	}

}
