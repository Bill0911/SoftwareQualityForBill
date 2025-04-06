import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PresentationTest {
    private Presentation presentation;
    private Slide slide;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        slide = new Slide();
    }

    @Test
    void testAppend_AddsSlideToList() {
        presentation.append(slide);
        assertEquals(1, presentation.getSlides().size());
        assertEquals(slide, presentation.getSlides().get(0));
    }

    @Test
    void testSetAndGetTitle() {
        presentation.setTitle("Test Title");
        assertEquals("Test Title", presentation.getTitle());
    }

    @Test
    void testSetAndGetSlideNumber() {
        presentation.setSlideNumber(2);
        assertEquals(2, presentation.getSlideNumber());
    }

    @Test
    void testClear_RemovesAllSlides() {
        presentation.append(slide);
        presentation.clear();
        assertEquals(0, presentation.getSlides().size());
    }

    @Test
    void testGetSlide_ValidIndex() {
        presentation.append(slide);
        assertEquals(slide, presentation.getSlide(0));
    }

    @Test
    void testGetSlide_InvalidIndex() {
        assertNull(presentation.getSlide(-1));
        assertNull(presentation.getSlide(100));
    }
}
