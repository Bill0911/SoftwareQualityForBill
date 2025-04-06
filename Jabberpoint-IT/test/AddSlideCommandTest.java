import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddSlideCommandTest {
    @Test
    public void testExecuteAndUndo() {
        Presentation presentation = new Presentation();
        Slide slide = new Slide();
        AddSlideCommand command = new AddSlideCommand(presentation, slide);

        command.execute();
        assertTrue(presentation.getSlides().contains(slide));

        command.undo();
        assertFalse(presentation.getSlides().contains(slide));
    }
}