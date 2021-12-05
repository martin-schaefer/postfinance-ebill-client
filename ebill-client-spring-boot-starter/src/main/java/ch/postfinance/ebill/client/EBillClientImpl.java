package ch.postfinance.ebill.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import ch.postfinance.ebill.webservice.ExecutePing;
import ch.postfinance.ebill.webservice.ExecutePingResponse;

public class EBillClientImpl extends WebServiceGatewaySupport implements EBillClient {

	@Override
	public ExecutePingResponse executePing(ExecutePing executePing) {
		ExecutePingResponse response = (ExecutePingResponse) getWebServiceTemplate().marshalSendAndReceive(executePing);
		return response;
	}
}
