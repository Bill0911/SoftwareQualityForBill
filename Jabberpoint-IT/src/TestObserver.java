public class TestObserver implements PresentationObserver {
    private boolean updated = false;

    @Override
    public void update(Presentation presentation) {
        updated = true; // When the presentation is updated, set the flag
    }

    public boolean isUpdated() {
        return updated; // Check if the observer was updated
    }

    public void reset() {
        updated = false; // Reset the flag for future tests
    }
}
