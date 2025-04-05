public class SetTitleCommand implements Command {
    private Presentation presentation;
    private String newTitle;
    private String oldTitle;

    public SetTitleCommand(Presentation presentation, String newTitle) {
        this.presentation = presentation;
        this.newTitle = newTitle;
    }

    @Override
    public void execute() {
        oldTitle = presentation.getTitle();
        presentation.setTitle(newTitle);
    }

    @Override
    public void undo() {
        presentation.setTitle(oldTitle);
    }
}