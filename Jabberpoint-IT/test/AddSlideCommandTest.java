import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddSlideCommandTest {
    private Presentation presentation;
    private Slide slide;
    private AddSlideCommand command;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        slide = new Slide();
        slide.setTitle("Test Slide");
        command = new AddSlideCommand(presentation, slide);
    }

    @Test
    void testExecute_AddsSlide() {
        command.execute();
        assertEquals(1, presentation.getSlides().size());
        assertEquals(slide, presentation.getSlides().get(0));
    }

    @Test
    void testUndo_RemovesSlide() {
        command.execute();
        command.undo();
        assertTrue(presentation.getSlides().isEmpty());
    }

    @Test
    void testExecuteUndoRedo_AddsSlideThenRemovesBack() {
        // Execute
        CommandManager.executeCommand(new AddSlideCommand(presentation, slide));
        assertEquals(1, presentation.getSlides().size());
        assertEquals(slide, presentation.getSlides().get(0));

        // Undo
        CommandManager.undo();
        assertEquals(0, presentation.getSlides().size());

        // Redo
        CommandManager.redo();
        assertEquals(1, presentation.getSlides().size());
        assertEquals(slide, presentation.getSlides().get(0));
    }
}