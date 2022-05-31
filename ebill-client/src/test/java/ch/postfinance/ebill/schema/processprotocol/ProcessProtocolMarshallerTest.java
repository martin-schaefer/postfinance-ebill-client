package ch.postfinance.ebill.schema.processprotocol;

import com.postfinance.process.Envelope;
import org.junit.jupiter.api.Test;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

class ProcessProtocolMarshallerTest {private final String TEST_REG_FILE = "subsc_BillerId_ddMMyyyyHHmmss.xml";

    private final String TEST_RRO_FILE = "ProcessProtocol_Example.xml";

    @Test
    void testProcessProtocolUnmarshal() {
        ProcessProtocolMarshaller marshaller = new ProcessProtocolMarshaller();
        assertDoesNotThrow(() -> {
            InputStream is = getClass().getClassLoader().getResourceAsStream(TEST_RRO_FILE);
            assert is != null;
            assertTrue(is.available() > 0);

            Envelope env = marshaller.unmarshal(new StreamSource(is));

            assertEquals(1, env.getBody().getDeliveryDate().size());
            assertEquals("20120522", env.getBody().getDeliveryDate().get(0).getDate());
            assertEquals("41101000000111111", env.getBody().getBillerID());
            assertEquals(1, env.getBody().getRejectedBills().getBill().size());

        });
    }

}