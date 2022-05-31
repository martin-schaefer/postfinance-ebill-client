package ch.postfinance.ebill.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "ebill.client.uri=https://ebill-ki.postfinance.ch/B2BService/B2BService.svc?singleWsdl", "ebill.client.username=someuser", "ebill.client.password=somepw" })
public class BootStartTest {

	@Autowired
	private EBillClient eBillClient;

	@Test
	public void eBillClient_notNull() {
		Assertions.assertThat(eBillClient).isNotNull();
	}
}
