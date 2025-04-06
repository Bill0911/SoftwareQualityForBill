import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrevSlideCommandTest {
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setToSlideNumber(1);
    }

    @Test
    void testExecute_MovesBack() {
        PrevSlideCommand command = new PrevSlideCommand(presentation);
        command.execute();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testUndo_ReturnsToNext() {
        PrevSlideCommand command = new PrevSlideCommand(presentation);
        command.execute();
        command.undo();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testExecuteUndoRedo() {
        CommandManager.executeCommand(new PrevSlideCommand(presentation));
        assertEquals(0, presentation.getSlideNumber());

        CommandManager.undo();
        assertEquals(1, presentation.getSlideNumber());

        CommandManager.redo();
        assertEquals(0, presentation.getSlideNumber());
    }
}
