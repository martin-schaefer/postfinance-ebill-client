package ch.postfinance.ebill.client.IT;

import ch.postfinance.ebill.client.EBillClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = { "ebill.client.uri=https://ebill-ki.postfinance.ch/B2BService/B2BService.svc?singleWsdl" })
public class PingTest {

	private final String billerID = "41010186990657958";

	@Autowired
	private EBillClient eBillClient;

	@Test
	void testExecutePing() throws MalformedURLException {

		final String actualId = eBillClient.executePing(billerID);
		assertEquals(billerID, actualId);
	}
}
