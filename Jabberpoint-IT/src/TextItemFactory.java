public class TextItemFactory extends SlideItemFactory {
    @Override
    public SlideItem createSlideItem(int level, String content) {
        return new TextItem(level, content);
    }
}
