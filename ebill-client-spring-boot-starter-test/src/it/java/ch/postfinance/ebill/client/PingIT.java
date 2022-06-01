package ch.postfinance.ebill.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "ebill.client.uri=https://ebill-ki.postfinance.ch/B2BService/B2BService.svc?singleWsdl" })
public class PingIT {

	private final String billerID = "41010186990657958";

	@Autowired
	private EBillClient eBillClient;

	@Test
	void testExecutePing() throws MalformedURLException {

		final String actualId = eBillClient.executePing(billerID);
		assertEquals(billerID, actualId);
	}
}
