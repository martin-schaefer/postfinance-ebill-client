package ch.postfinance.ebill.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "ebill.client.uri=http://localhost" })
public class EBillAutoStartTest {

	@Autowired
	private EBillClient eBillClient;

	@Test
	public void eBillClient_notNull() {
		Assertions.assertThat(eBillClient).isNotNull();
	}
}
