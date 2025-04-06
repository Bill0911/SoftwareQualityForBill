import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BitmapItemTest {
    @Test
    void testGetters() {
        BitmapItem bitmapItem = new BitmapItem(3, "image.jpg");
        assertEquals(3, bitmapItem.getLevel());
        assertEquals("image.jpg", bitmapItem.getName());
    }
}
