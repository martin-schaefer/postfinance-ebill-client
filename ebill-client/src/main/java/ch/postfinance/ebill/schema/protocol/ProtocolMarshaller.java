package ch.postfinance.ebill.schema.protocol;

import ch.postfinance.ebill.schema.marshaller.SchemaMarshaller;

/**
 * Marshaller and unmarshaller for {@link Envelope}
 * 
 * @author Martin Schäfer
 *
 * @param <T> type of schema class to marshal/unmarshal
 */
public class ProtocolMarshaller extends SchemaMarshaller<Envelope> {

	/**
	 * {@inheritDoc}
	 */
	public ProtocolMarshaller(boolean formattedOutput) {
		super(formattedOutput, Envelope.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public ProtocolMarshaller() {
		super(Envelope.class);
	}

}
