public class SetTitleCommand implements Command {
    private Presentation presentation;
    private String newTitle;
    private String previousTitle;

    public SetTitleCommand(Presentation presentation, String newTitle) {
        this.presentation = presentation;
        this.newTitle = newTitle;
    }

    @Override
    public void execute() {
        previousTitle = presentation.getTitle();
        presentation.setTitle(newTitle);
    }

    @Override
    public void undo() {
        presentation.setTitle(previousTitle);
    }
}