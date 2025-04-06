import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextItemTest {
    @Test
    void testGetters() {
        TextItem textItem = new TextItem(2, "Some text");
        assertEquals(2, textItem.getLevel());
        assertEquals("Some text", textItem.getText());
    }
}
