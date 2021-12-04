package ch.postfinance.ebill.schema.ybinvoice;

import ch.postfinance.ebill.schema.marshaller.SchemaMarshaller;

public class YbInvoiceMarshaller extends SchemaMarshaller<Envelope> {

	/**
	 * {@inheritDoc}
	 */
	public YbInvoiceMarshaller(boolean formattedOutput) {
		super(formattedOutput, Envelope.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public YbInvoiceMarshaller() {
		super(Envelope.class);
	}

}
