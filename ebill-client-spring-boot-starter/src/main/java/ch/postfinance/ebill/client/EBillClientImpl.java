package ch.postfinance.ebill.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import b2bservice.ebill.swisspost.ch.B2BService;
import ch.postfinance.ebill.schema.processprotocol.ProcessProtocolMarshaller;
import ch.postfinance.ebill.schema.registration.RegistrationMarshaller;
import ch.postfinance.ebill.schema.yellowbill.YellowBillInvoiceMarshaller;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EBillClientImpl extends WebServiceGatewaySupport implements EBillClient {

	@NonNull
	private final B2BService b2BService;
	@NonNull
	private final RegistrationMarshaller registrationMarshaller;
	@NonNull
	private final ProcessProtocolMarshaller processProtocoMarshaller;
	@NonNull
	private final YellowBillInvoiceMarshaller yellowBillInvoiceMarshaller;

	@Override
	public String executePing(String billerID) {
		return b2BService.executePing(billerID, null, null, null);
	}
}
