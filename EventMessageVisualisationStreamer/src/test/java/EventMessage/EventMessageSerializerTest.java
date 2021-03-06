package EventMessage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for EventMessagePOJO to EventMessage converter.
 * Created by ISIS, STFC on 07/06/2017.
 */
@SuppressWarnings("checkstyle:javadocmethod")
public class EventMessageSerializerTest {
    private static final int DEFAULT_MESSAGE_ID = 0;
    private static final int DEFAULT_PULSE_TIME = 0;
    private static final String DEFAULT_TOPIC = "Detection Events";
    private EventMessagePOJO eventMessagePOJO;
    private EventMessageSerializer eventMessageSerializer;

    @Before
    public void setUp() throws Exception {
        eventMessagePOJO = new EventMessagePOJO(DEFAULT_MESSAGE_ID, DEFAULT_PULSE_TIME);
        eventMessageSerializer = new EventMessageSerializer();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void runtimeExceptionWhenMessageIdNegative() {
        exception.expect(RuntimeException.class);
        eventMessagePOJO.setMessageId(-1);
        eventMessageSerializer.serialize(DEFAULT_TOPIC, eventMessagePOJO);
    }

    @Test
    public void runtimeExceptionWhenPulseTimeNegative() {
        exception.expect(RuntimeException.class);
        eventMessagePOJO.setPulseTime(-1);
        eventMessageSerializer.serialize(DEFAULT_TOPIC, eventMessagePOJO);
    }

    @Test
    public void convertPOJOWithNoDetectorsReturnsByteArray() {
        byte[] result = eventMessageSerializer.serialize(DEFAULT_TOPIC, eventMessagePOJO);
        assertNotNull("Should not be null", result);
    }

    @Test
    public void convertPOJOWithDetectorsReturnsByteArray() {
        eventMessagePOJO.addDetector(1);
        eventMessagePOJO.addDetector(2);
        eventMessagePOJO.addDetector(3);
        byte[] result = eventMessageSerializer.serialize(DEFAULT_TOPIC, eventMessagePOJO);
        assertNotNull("Should not be null", result);
    }

    @Test
    public void getMessageIDCorrectWhenConvertingDefaultPOJO() {
        byte[] eventMessage = eventMessageSerializer.serialize(DEFAULT_TOPIC, eventMessagePOJO);
        EventMessageDeserializer eventMessageDeserializer = new EventMessageDeserializer();
        EventMessagePOJO result = eventMessageDeserializer.deserialize(DEFAULT_TOPIC, eventMessage);

        assertEquals(DEFAULT_MESSAGE_ID, result.getMessageId());
    }

    @Test
    public void getPulseTimeCorrectWhenConvertingDefaultPOJO() {
        byte[] eventMessage = eventMessageSerializer.serialize(DEFAULT_TOPIC, eventMessagePOJO);
        EventMessageDeserializer eventMessageDeserializer = new EventMessageDeserializer();
        EventMessagePOJO result = eventMessageDeserializer.deserialize(DEFAULT_TOPIC, eventMessage);

        assertEquals(DEFAULT_PULSE_TIME, result.getPulseTime());
    }

    @Test
    public void getDetectorsReturnsNullWhenConvertingDefaultPOJO() {
        byte[] eventMessage = eventMessageSerializer.serialize(DEFAULT_TOPIC, eventMessagePOJO);
        EventMessageDeserializer eventMessageDeserializer = new EventMessageDeserializer();
        EventMessagePOJO result = eventMessageDeserializer.deserialize(DEFAULT_TOPIC, eventMessage);

        assertEquals(new ArrayList<Integer>(), result.getDetectors());
    }

    @Test
    public void getDetectorsReturnsCorrectWhenConvertingPOJOWithTwoDetectors() {
        eventMessagePOJO.addDetector(1);
        eventMessagePOJO.addDetector(2);
        byte[] eventMessage = eventMessageSerializer.serialize(DEFAULT_TOPIC, eventMessagePOJO);
        EventMessageDeserializer eventMessageDeserializer = new EventMessageDeserializer();
        EventMessagePOJO result = eventMessageDeserializer.deserialize(DEFAULT_TOPIC, eventMessage);

        assertEquals(2, result.getDetectors().size());
        assertEquals(1,result.getDetector(0));
        assertEquals(2,result.getDetector(1));
    }

}
