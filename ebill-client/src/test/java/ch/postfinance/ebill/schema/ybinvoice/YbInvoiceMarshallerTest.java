package ch.postfinance.ebill.schema.ybinvoice;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.SneakyThrows;

public class YbInvoiceMarshallerTest {

	@ParameterizedTest
	@ValueSource(strings = { "/41100000000025069_INTERCONNECT.xml",
			"/41101000000115520_ESR.xml",
			"/41101000000115520_IBAN.xml",
			"/41101000000215139_QR-IBAN.xml" })
	@SneakyThrows
	public void unmarshall_inputStream(String resource) {
		// given
		YbInvoiceMarshaller ybInvoiceMarshaller = new YbInvoiceMarshaller();
		try (InputStream inputStream = getClass().getResourceAsStream(resource)) {

			// when
			Envelope envelope = ybInvoiceMarshaller.unmarshall(inputStream);

			// then
			assertThat(envelope).isNotNull();
		}
	}

}
