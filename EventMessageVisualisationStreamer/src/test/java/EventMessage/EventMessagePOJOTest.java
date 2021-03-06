package EventMessage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for EventMessagePOJO.
 * Created by ISIS, STFC on 02/06/2017.
 */
@SuppressWarnings("checkstyle:javadocmethod")
public class EventMessagePOJOTest {
    private static final int DEFAULT_MESSAGE_ID = 0;
    private static final int DEFAULT_PULSE_TIME = 0;
    private EventMessagePOJO eventMessagePOJO;

    @Before
    public void createDefaultEventMessagePOJO() {
        eventMessagePOJO = new EventMessagePOJO(DEFAULT_MESSAGE_ID, DEFAULT_PULSE_TIME);
    }


    @Test
    public void setMessageIdReturnsCorrectWhenInitialised() {
        assertEquals(DEFAULT_MESSAGE_ID, eventMessagePOJO.getMessageId());
        eventMessagePOJO.setMessageId(1);
        assertEquals(1, eventMessagePOJO.getMessageId());
    }

    @Test
    public void getMessageIdReturnsCorrect() {
        assertEquals(DEFAULT_MESSAGE_ID, eventMessagePOJO.getMessageId());
    }

    @Test
    public void setPulseTimeToFiveThousandReturnsCorrect() {
        long newPulseTime = Integer.toUnsignedLong(5000);
        eventMessagePOJO.setPulseTime(newPulseTime);
        assertEquals(newPulseTime, eventMessagePOJO.getPulseTime());
    }

    @Test
    public void getPulseTimeReturnsCorrectWhenInitialised() {
        assertEquals(Integer.toUnsignedLong(DEFAULT_PULSE_TIME), eventMessagePOJO.getPulseTime());
    }

    @Test
    public void detectorsSizeZeroWhenInitialised() {
        assertEquals(0, eventMessagePOJO.getDetectors().size());
    }

    @Test
    public void getDetectorWhenAddedReturnsCorrect() {
        int newDetectorId = 2;
        eventMessagePOJO.addDetector(newDetectorId);
        assertEquals(1, eventMessagePOJO.getDetectors().size());
        assertEquals(newDetectorId, eventMessagePOJO.getDetector(0));
    }

    @Test
    public void getDetectorsWhenTwoDetectorsAddedReturnsCorrect() {
        Integer detector1 = 3;
        Integer detector2 = 4;
        eventMessagePOJO.addDetector(detector1);
        eventMessagePOJO.addDetector(detector2);
        List<Integer> detectors = eventMessagePOJO.getDetectors();
        assertEquals(2, detectors.size());
        assertEquals(detector1, detectors.get(0));
        assertEquals(detector2, detectors.get(1));
    }

    @Test
    public void setDetectorsToArrayOfTwoReturnsCorrect() {
        int detector1 = 3;
        int detector2 = 4;
        ArrayList<Integer> detectors = new ArrayList();
        detectors.add(detector1);
        detectors.add(detector2);

        eventMessagePOJO.setDetectors(detectors);

        assertEquals(2, eventMessagePOJO.getDetectors().size());
        assertEquals(detector1, eventMessagePOJO.getDetector(0));
        assertEquals(detector2, eventMessagePOJO.getDetector(1));
    }

}
