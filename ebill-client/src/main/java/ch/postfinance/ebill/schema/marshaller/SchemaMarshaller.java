package ch.postfinance.ebill.schema.marshaller;

import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * Generic marshaller and unmarshaller for schema objects
 * 
 * @author Martin Schäfer
 *
 * @param <T> type of schema class to marshal/unmarshal
 */
public abstract class SchemaMarshaller<T> {

	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;

	/**
	 * Creates a SchemaMarshaller
	 * 
	 * @param formattedOutput true if the XML output should be formatted, false
	 *                        otherwise
	 * @param clazz           schema class to marshal/unmarshal. This must be a
	 *                        class with JAXB annotations.
	 */
	@SneakyThrows
	public SchemaMarshaller(boolean formattedOutput, @NonNull Class<T> clazz) {
		JAXBContext context = JAXBContext.newInstance(clazz);
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formattedOutput);
		unmarshaller = context.createUnmarshaller();
	}

	/**
	 * Creates a SchemaMarshaller
	 * 
	 * @param clazz schema class to marshal/unmarshal. This must be a class with
	 *              JAXB annotations.
	 */
	public SchemaMarshaller(@NonNull Class<T> clazz) {
		this(false, clazz);
	}

	/**
	 * Marshal the schema object into an output stream.
	 * 
	 * @param schemaObject required
	 * @param outputStream required
	 */
	@SneakyThrows
	public void marshal(@NonNull T schemaObject, @NonNull OutputStream outputStream) {
		marshaller.marshal(schemaObject, outputStream);
	}

	/**
	 * Marshal the schema object into a writer.
	 * 
	 * @param schemaObject required
	 * @param writer       required
	 */
	@SneakyThrows
	public void marshal(@NonNull T schemaObject, @NonNull Writer writer) {
		marshaller.marshal(schemaObject, writer);
	}

	/**
	 * Marshal the schema object and return it as XML String.
	 * 
	 * @param schemaObject required
	 * @return XML
	 */
	@SneakyThrows
	public String marshal(@NonNull T schemaObject) {
		StringWriter stringWriter = new StringWriter(1024);
		marshaller.marshal(schemaObject, stringWriter);
		return stringWriter.toString();
	}

	/**
	 * Unmarshal from XML read from the given InputStream and return it as schema
	 * object.
	 * 
	 * @param source
	 * @return The schema object
	 */
	@SneakyThrows
	public T unmarshal(@NonNull StreamSource source) {
		return (T) unmarshaller.unmarshal(source);
	}
}
