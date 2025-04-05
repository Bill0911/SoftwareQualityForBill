public class PrevSlideCommand implements Command {
    private Presentation presentation;
    private int prevSlideNumber;

    public PrevSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        prevSlideNumber = presentation.getSlideNumber();
        if (presentation.getSlideNumber() > 0) {
            presentation.setToSlideNumber(presentation.getSlideNumber() - 1);
        }
    }

    @Override
    public void undo() {
        presentation.setToSlideNumber(prevSlideNumber);
    }
}
