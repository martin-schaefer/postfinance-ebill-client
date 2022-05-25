package ch.postfinance.ebill.schema.registration;

import b2bservice.ebill.ebs.swisspost_ch.DownloadFile;
import b2bservice.ebill.ebs.swisspost_ch.ObjectFactory;
import com.postfinance.customer.registration.CustomerRegistrationMessage;
import org.junit.jupiter.api.Test;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationMarshallerTest {
    private final String TEST_REG_FILE = "subsc_BillerId_ddMMyyyyHHmmss.xml";

    @Test
    void testRegistrationUnmarshal() {
        RegistrationMarshaller marshaller = new RegistrationMarshaller();
        assertDoesNotThrow(() -> {
            InputStream is = getClass().getClassLoader().getResourceAsStream(TEST_REG_FILE);
            assertNotNull(is);
            assertTrue(is.available() > 0);
            ObjectFactory factory = new ObjectFactory();
            DownloadFile file = factory.createDownloadFile();
            file.setData(factory.createDownloadFileData(is.readAllBytes()));
            file.setFilename(factory.createDownloadFileFilename("testRegistrations.xml"));
            System.out.printf("regs: %s%n", new String(file.getData().getValue(), StandardCharsets.UTF_8));
            CustomerRegistrationMessage regMsgs = marshaller.
                    unmarshal(new StreamSource(new ByteArrayInputStream(file.getData().getValue())));

            assertEquals(6, regMsgs.getCustomerRegistration().size());

        });
    }

}