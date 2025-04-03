import java.util.ArrayList;
import java.util.List;

/**
 * <p>Presentation maintains the slides in the presentation.</p>
 * <p>There is only one instance of this class.</p>
 */
public class Presentation {
	private String showTitle; // Title of the presentation
	private List<Slide> showList; // A list of slides
	private int currentSlideNumber = 0; // Index of the current slide
	private SlideViewerComponent slideViewComponent; // Viewer component for slides
	private CommandManager commandManager; // Manages command execution

	public Presentation() {
		this.slideViewComponent = null;
		this.commandManager = new CommandManager();
		clear();
	}

	public Presentation(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
		this.commandManager = new CommandManager();
		clear();
	}

	public int getSize() {
		return showList.size();
	}

	public String getTitle() {
		return showTitle;
	}

	public void setTitle(String title) {
		this.showTitle = title;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	public int getSlideNumber() {
		return currentSlideNumber;
	}

	public void setSlideNumber(int number) {
		commandManager.executeCommand(new SetSlideNumberCommand(this, number));
	}

	public void prevSlide() {
		commandManager.executeCommand(new PrevSlideCommand(this));
	}

	public void nextSlide() {
		commandManager.executeCommand(new NextSlideCommand(this));
	}

	public void clear() {
		commandManager.executeCommand(new ClearPresentationCommand(this));
	}

	public void append(Slide slide) {
		commandManager.executeCommand(new AddSlideCommand(this, slide));
	}

	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()) {
			return null;
		}
		return showList.get(number);
	}

	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}

	public void undoLastAction() {
		commandManager.undo();
	}

	public List<Slide> getSlides() {
		return showList;
	}

	public void exit(int code) {
		System.exit(code);
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}
}
