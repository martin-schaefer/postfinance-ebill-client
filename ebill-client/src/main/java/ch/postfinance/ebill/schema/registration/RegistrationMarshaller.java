package ch.postfinance.ebill.schema.registration;

import ch.postfinance.ebill.schema.marshaller.SchemaMarshaller;
import com.postfinance.customer.registration.CustomerRegistrationMessage;
import lombok.NonNull;

import javax.xml.transform.stream.StreamSource;

public class RegistrationMarshaller extends SchemaMarshaller<CustomerRegistrationMessage>{

    /**
     * {@inheritDoc}
     */
    public RegistrationMarshaller(boolean formattedOutput) {
        super(formattedOutput, CustomerRegistrationMessage.class);
    }

    /**
     * {@inheritDoc}
     */
    public RegistrationMarshaller() {
        super(CustomerRegistrationMessage.class);
    }

    @Override
    public CustomerRegistrationMessage unmarshal(@NonNull StreamSource source) {
        return super.unmarshal(source);
    }
}

