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
        previousSlides = new ArrayList<>(presentation.getSlides());
        previousSlideNumber = presentation.getSlideNumber();
        presentation.getSlides().clear();
        presentation.setToSlideNumber(-1);
    }

    @Override
    public void undo() {
        // Restore previous state
        presentation.getSlides().addAll(previousSlides);
        presentation.setToSlideNumber(previousSlideNumber);
    }
}
