package ch.postfinance.ebill.client;

import b2bservice.ebill.swisspost.ch.B2BService;
import ch.postfinance.ebill.schema.marshaller.SchemaMarshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


public class EBillClientImpl extends WebServiceGatewaySupport implements EBillClient {

    private final B2BService b2BService;
    private final SchemaMarshaller registrationMarshaller;
    private final SchemaMarshaller processProtocoMarshaller;
    private final SchemaMarshaller yellowBillInvoiceMarshaller;

    public EBillClientImpl(B2BService b2BService, SchemaMarshaller registrationMarshaller,
                           SchemaMarshaller processProtocoMarshaller, SchemaMarshaller yellowBillInvoiceMarshaller) {
        this.b2BService = b2BService;
        this.registrationMarshaller = registrationMarshaller;
        this.processProtocoMarshaller = processProtocoMarshaller;
        this.yellowBillInvoiceMarshaller = yellowBillInvoiceMarshaller;
    }

    @Override
    public String executePing(String billerID) {
        return b2BService.executePing(billerID, null, null, null);
    }
}
