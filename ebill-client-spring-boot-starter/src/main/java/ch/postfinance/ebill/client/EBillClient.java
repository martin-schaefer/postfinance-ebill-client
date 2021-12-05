package ch.postfinance.ebill.client;

import ch.postfinance.ebill.webservice.ExecutePing;
import ch.postfinance.ebill.webservice.ExecutePingResponse;

public interface EBillClient {

	ExecutePingResponse executePing(ExecutePing executePing);

}