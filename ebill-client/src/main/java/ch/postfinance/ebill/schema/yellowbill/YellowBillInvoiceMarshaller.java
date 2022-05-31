package ch.postfinance.ebill.schema.yellowbill;

import ch.postfinance.ebill.schema.marshaller.SchemaMarshaller;
import com.postfinance.bill.DeliveryType;
import lombok.NonNull;

import javax.xml.transform.stream.StreamSource;

public class YellowBillInvoiceMarshaller extends SchemaMarshaller<DeliveryType> {

    /**
     * {@inheritDoc}
     */
    public YellowBillInvoiceMarshaller(boolean formattedOutput) {
        super(formattedOutput, DeliveryType.class);
    }

    /**
     * {@inheritDoc}
     */
    public YellowBillInvoiceMarshaller() {
        super(DeliveryType.class);
    }

    @Override
    public DeliveryType unmarshal(@NonNull StreamSource source) {
        return super.unmarshal(source);
    }
}
