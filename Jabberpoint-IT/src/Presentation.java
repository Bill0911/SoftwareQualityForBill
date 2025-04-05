import java.util.ArrayList;
import java.util.List;


/**
 * <p>Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String showTitle; // title of the presentation
	private ArrayList<Slide> showList = null; // an ArrayList with Slides
	private int currentSlideNumber = 0; // the slidenummer of the current Slide
	private SlideViewerComponent slideViewComponent = null; // the viewcomponent of the Slides

	private CommandManager commandManager;

	public Presentation() {
		slideViewComponent = null;
		this.commandManager = new CommandManager();
		clear();
	}

	public Presentation(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
		this.commandManager = new CommandManager();
	}

	public int getSize() {
		return showList.size();
	}

	public String getTitle() {
		return showTitle;
	}

	public void setToTitle(String title) {
		System.out.println("pres.setToTitle('"+title+"')");
		commandManager.executeCommand(new SetTitleCommand(this, title));
	}

	public void setTitle(String nt) {
		showTitle = nt;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	// give the number of the current slide
	public int getSlideNumber() {
		return currentSlideNumber;
	}

	// change the current slide number and signal it to the window
	public void setToSlideNumber(int number) {
		System.out.println("presentation.setToSlideNumber("+number+")");
		commandManager.executeCommand(new SetToSlideNumberCommand(this, number));
	}

	public void setSlideNumber(int number) {
		System.out.println("setSlideNumber("+number+")");

		currentSlideNumber = number;
		if (slideViewComponent != null) {
			slideViewComponent.update(this, getCurrentSlide());
		}
	}

	public void prevSlide() {
		System.out.println("prevSlide~");
		commandManager.executeCommand(new PrevSlideCommand(this));
	}

	public void nextSlide() {
		System.out.println("nextSlide~");
		commandManager.executeCommand(new NextSlideCommand(this));
	}

	// Delete the presentation to be ready for the next one.
	public void clear() {
		commandManager.executeCommand(new ClearPresentationCommand(this));
	}

	// Add a slide to the presentation
	public void append(Slide slide) {
		System.out.println("pres.append()");
		commandManager.executeCommand(new AddSlideCommand(this, slide));
	}

	// Get a slide with a certain slidenumber
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()){
			return null;
	    }
			return (Slide)showList.get(number);
	}

	// Give the current slide
	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}

	public void exit(int n) {
		System.exit(n);
	}

	public List<Slide> getSlides() {
		if (showList == null) {
			showList = new ArrayList<>();
		}
		return showList;
	}
}
