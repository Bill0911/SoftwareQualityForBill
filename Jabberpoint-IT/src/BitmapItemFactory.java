public class BitmapItemFactory extends SlideItemFactory {
    @Override
    public SlideItem createSlideItem(int level, String content) {
        return new BitmapItem(level, content);
    }
}
