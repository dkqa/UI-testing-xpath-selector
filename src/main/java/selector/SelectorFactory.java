package selector;

public class SelectorFactory {

    public static Selector selector() {
        return new Selector();
    }

    public static Selector compose(Selector... selectors) {
        return new Selector(selectors);
    }

    public static Selector tag(String tag) {
        return new Selector().tag(tag);
    }

    public static Selector text(String text) {
        return new Selector().text(text, false, false, true);
    }

    public static Selector div(String classValue) {
        return new Selector().tag("div").classAttr(classValue);
    }

    public static Selector div_contains(String classValue) {
        return new Selector().tag("div").attribute("class", classValue, true, true);
    }
}