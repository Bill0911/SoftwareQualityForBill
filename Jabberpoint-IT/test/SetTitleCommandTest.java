import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SetTitleCommandTest {
    private Presentation presentation;
    private SetTitleCommand command;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        presentation.setTitle("Old Title");
        command = new SetTitleCommand(presentation, "New Title");
    }

    @Test
    void testExecute_ChangesTitle() {
        command.execute();
        assertEquals("New Title", presentation.getTitle());
    }

    @Test
    void testUndo_RevertsTitle() {
        command.execute();
        command.undo();
        assertEquals("Old Title", presentation.getTitle());
    }

    @Test
    void testExecuteUndoRedo() {
        CommandManager.executeCommand(new SetTitleCommand(presentation, "Updated Title"));
        assertEquals("Updated Title", presentation.getTitle());

        CommandManager.undo();
        assertEquals("Old Title", presentation.getTitle());

        CommandManager.redo();
        assertEquals("Updated Title", presentation.getTitle());
    }
}