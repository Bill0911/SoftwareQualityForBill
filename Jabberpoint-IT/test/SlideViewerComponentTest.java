import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;

public class SlideViewerComponentTest {
    private Presentation presentation;
    private SlideViewerComponent slideViewerComponent;
    private JFrame frame;

    @BeforeEach
    void setUp() {
        frame = new JFrame();
        presentation = new Presentation();
        presentation.setTitle("New Title");
        slideViewerComponent = new SlideViewerComponent(frame);
        slideViewerComponent.setPresentation(presentation);  // Link the component to the presentation
    }

    @Test
    void testSlideViewerComponentUpdatesWhenSlideAdded() {
        // Create the slide and append it to the presentation
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.append(1, "Test content");
        presentation.append(slide);

        // Ensure the frame title updates correctly after adding the slide
        String expectedTitle = "New Title - Slide 1 of 1";  // Expecting Slide 1 of 1 for the first slide
        assertEquals(expectedTitle, frame.getTitle());
    }

    @Test
    void testSlideViewerComponentUpdatesWhenSlideNumberChanges() {
        // Create and append slides to the presentation
        Slide slide1 = new Slide();
        slide1.setTitle("Slide 1");
        slide1.append(1, "Slide 1 content");
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        slide2.append(1, "Slide 2 content");

        presentation.append(slide1);
        presentation.append(slide2);

        // Set the slide number to 1 (second slide)
        presentation.setSlideNumber(1);

        // Check that the frame title is updated correctly after the slide number changes
        String expectedTitle = "New Title - Slide 2 of 2";
        assertEquals(expectedTitle, frame.getTitle());
    }
}
