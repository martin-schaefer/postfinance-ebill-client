package ch.postfinance.ebill.schema.processprotocol;

import ch.postfinance.ebill.schema.marshaller.SchemaMarshaller;
import com.postfinance.process.Envelope;
import lombok.NonNull;

import javax.xml.transform.stream.StreamSource;

public class ProcessProtocolMarshaller extends SchemaMarshaller<Envelope> {

    /**
     * {@inheritDoc}
     */
    public ProcessProtocolMarshaller(boolean formattedOutput) {
        super(formattedOutput, Envelope.class);
    }

    /**
     * {@inheritDoc}
     */
    public ProcessProtocolMarshaller() {
        super(Envelope.class);
    }

    @Override
    public Envelope unmarshal(@NonNull StreamSource source) {
        return super.unmarshal(source);
    }
}
