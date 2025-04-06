import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NextSlideCommandTest {
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setToSlideNumber(0);
    }

    @Test
    void testExecute_MoveToNext() {
        NextSlideCommand command = new NextSlideCommand(presentation);
        command.execute();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testUndo_BackToPrevious() {
        NextSlideCommand command = new NextSlideCommand(presentation);
        command.execute();
        command.undo();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testExecuteUndoRedo() {
        CommandManager.executeCommand(new NextSlideCommand(presentation));
        assertEquals(1, presentation.getSlideNumber());

        CommandManager.undo();
        assertEquals(0, presentation.getSlideNumber());

        CommandManager.redo();
        assertEquals(1, presentation.getSlideNumber());
    }
}
