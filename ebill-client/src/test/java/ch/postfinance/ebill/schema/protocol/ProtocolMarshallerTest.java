package ch.postfinance.ebill.schema.protocol;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

public class ProtocolMarshallerTest {

	@Test
	@SneakyThrows
	public void unmarshall_inputStream() {
		// given
		ProtocolMarshaller protocolMarshaller = new ProtocolMarshaller();
		try (InputStream inputStream = getClass().getResourceAsStream("/ProcessProtocol_Example.xml")) {

			// when
			Envelope envelope = protocolMarshaller.unmarshall(inputStream);

			// then
			assertThat(envelope).isNotNull();
			assertThat(envelope.getBody().getBillerID()).isEqualTo("41101000000111111");
		}
	}
}