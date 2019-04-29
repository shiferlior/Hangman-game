public class WordMockChooser implements IWordChooser {
    @Override
    public String[] getWord() {
        String[] mock = {"a", "blue", "banana"};

        return mock;
    }
}
