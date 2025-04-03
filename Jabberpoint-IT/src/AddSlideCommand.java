public class AddSlideCommand implements Command {
    private Presentation presentation;
    private Slide slide;

    public AddSlideCommand(Presentation presentation, Slide slide) {
        this.presentation = presentation;
        this.slide = slide;
    }

    @Override
    public void execute() {
        presentation.getSlides().add(slide);
    }

    @Override
    public void undo() {
        presentation.getSlides().remove(slide);
    }
}
