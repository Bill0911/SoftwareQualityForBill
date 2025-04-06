import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClearPresentationCommandTest {
    private Presentation presentation;
    private Slide slide;
    private ClearPresentationCommand command;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        slide = new Slide();
        slide.setTitle("Slide 1");
        presentation.append(slide);
        command = new ClearPresentationCommand(presentation);
    }

    @Test
    void testExecute_ClearsSlides() {
        command.execute();
        assertTrue(presentation.getSlides().isEmpty());
    }

    @Test
    void testUndo_RestoresSlides() {
        command.execute();
        command.undo();
        assertEquals(1, presentation.getSlides().size());
        assertEquals(slide, presentation.getSlides().get(0));
    }

    @Test
    void testExecuteUndoRedo() {
        CommandManager.executeCommand(new ClearPresentationCommand(presentation));
        assertEquals(0, presentation.getSlides().size());

        CommandManager.undo();
        assertEquals(1, presentation.getSlides().size());

        CommandManager.redo();
        assertEquals(0, presentation.getSlides().size());
    }
}
