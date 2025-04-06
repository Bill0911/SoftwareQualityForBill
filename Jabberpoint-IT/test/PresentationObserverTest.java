import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PresentationObserverTest {
    private Presentation presentation;
    private TestObserver testObserver;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        testObserver = new TestObserver(); // Create a TestObserver
        presentation.addObserver(testObserver); // Register the observer
    }

    @Test
    void testObserverNotificationWhenTitleChanged() {
        testObserver.reset(); // Reset the observer's state before each test

        presentation.setToTitle("New Presentation Title");

        // Assert that the observer was notified
        assertTrue(testObserver.isUpdated(), "Observer should be notified when the title changes.");
    }

    @Test
    void testObserverNotificationWhenSlideNumberChanged() {
        testObserver.reset(); // Reset the observer's state before each test

        presentation.setToSlideNumber(1);

        // Assert that the observer was notified
        assertTrue(testObserver.isUpdated(), "Observer should be notified when the slide number changes.");
    }

    @Test
    void testObserverNotificationWhenSlideAdded() {
        testObserver.reset(); // Reset the observer's state before each test

        Slide newSlide = new Slide();
        newSlide.setTitle("New Slide");
        presentation.append(newSlide);

        // Assert that the observer was notified
        assertTrue(testObserver.isUpdated(), "Observer should be notified when a new slide is added.");
    }
}
