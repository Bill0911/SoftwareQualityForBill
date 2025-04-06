import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlideTest {
    private Slide slide;

    @BeforeEach
    void setUp() {
        slide = new Slide();
    }

    @Test
    void testSetAndGetTitle() {
        slide.setTitle("My Slide");
        assertEquals("My Slide", slide.getTitle());
    }

    @Test
    void testAppend_AddsTextItem() {
        slide.append(1, "Hello World");
        assertEquals(1, slide.getSlideItems().size());
        assertTrue(slide.getSlideItems().get(0) instanceof TextItem);
    }

    @Test
    void testAppend_AddsBitmapItem() {
        slide.append(new BitmapItem(2, "image.png"));
        assertEquals(1, slide.getSlideItems().size());
        assertTrue(slide.getSlideItems().get(0) instanceof BitmapItem);
    }

    @Test
    void testGetSlideItems_ReturnsCorrectItems() {
        slide.append(1, "Text A");
        slide.append(2, "Text B");
        assertEquals(2, slide.getSlideItems().size());
    }
}
