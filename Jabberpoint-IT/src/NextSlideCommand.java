public class NextSlideCommand implements Command {
    private Presentation presentation;
    private int prevSlideNumber; // For undo functionality

    public NextSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        prevSlideNumber = presentation.getSlideNumber();
        if (presentation.getSlideNumber() < presentation.getSize() - 1) {
            presentation.setSlideNumber(presentation.getSlideNumber() + 1);
        }
    }

    @Override
    public void undo() {
        presentation.setSlideNumber(prevSlideNumber);
    }
}