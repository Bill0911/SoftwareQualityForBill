public class SetSlideNumberCommand implements Command {
    private Presentation presentation;
    private int newSlideNumber;
    private int prevSlideNumber;

    public SetSlideNumberCommand(Presentation presentation, int newSlideNumber) {
        this.presentation = presentation;
        this.newSlideNumber = newSlideNumber;
    }

    @Override
    public void execute() {
        prevSlideNumber = presentation.getSlideNumber();
        presentation.setSlideNumber(newSlideNumber);
    }

    @Override
    public void undo() {
        presentation.setSlideNumber(prevSlideNumber);
    }
}
