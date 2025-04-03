import java.util.ArrayList;
import java.util.List;

public class ClearPresentationCommand implements Command {
    private Presentation presentation;
    private List<Slide> previousSlides;
    private int previousSlideNumber;

    public ClearPresentationCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        // Save current state for undo
        previousSlides = new ArrayList<>(presentation.getSlides());
        previousSlideNumber = presentation.getSlideNumber();

        // Clear the presentation
        presentation.getSlides().clear();
        presentation.setSlideNumber(-1);
    }

    @Override
    public void undo() {
        // Restore previous state
        presentation.getSlides().addAll(previousSlides);
        presentation.setSlideNumber(previousSlideNumber);
    }
}
