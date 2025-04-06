import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlideItemFactoryTest {

    @Test
    void testTextItemFactoryCreatesTextItem() {
        SlideItemFactory factory = new TextItemFactory();
        SlideItem item = factory.createSlideItem(1, "Hello");

        assertTrue(item instanceof TextItem);
        assertEquals(1, item.getLevel());
        assertEquals("Hello", ((TextItem) item).getText());
    }

    @Test
    void testBitmapItemFactoryCreatesBitmapItem() {
        SlideItemFactory factory = new BitmapItemFactory();
        SlideItem item = factory.createSlideItem(2, "image.png");

        assertTrue(item instanceof BitmapItem);
        assertEquals(2, item.getLevel());
        assertEquals("image.png", ((BitmapItem) item).getName());
    }
}
