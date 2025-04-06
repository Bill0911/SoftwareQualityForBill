import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SetToSlideNumberCommandTest {
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setToSlideNumber(0);
    }

    @Test
    void testExecute_SetsSpecificSlide() {
        SetToSlideNumberCommand command = new SetToSlideNumberCommand(presentation, 1);
        command.execute();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testUndo_GoesBackToOldSlide() {
        SetToSlideNumberCommand command = new SetToSlideNumberCommand(presentation, 1);
        command.execute();
        command.undo();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testExecuteUndoRedo() {
        CommandManager.executeCommand(new SetToSlideNumberCommand(presentation, 1));
        assertEquals(1, presentation.getSlideNumber());

        CommandManager.undo();
        assertEquals(0, presentation.getSlideNumber());

        CommandManager.redo();
        assertEquals(1, presentation.getSlideNumber());
    }
}
