package selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Selector implements SelectorBehavior<Selector> {

    private List<MultipleSelector> selectors;

    public Selector() {
        this.selectors = Arrays.asList(new MultipleSelector());
    }

    public Selector(MultipleSelector... selectors) {
        this.selectors = Arrays.asList(selectors);
    }

    private Selector(List<MultipleSelector> selectors) {
        this.selectors = selectors;
    }

    public Selector(Selector... selectors) {
        this.selectors = new ArrayList<>();
        for (Selector selector : selectors) {
            this.selectors.addAll(selector.selectors.stream()
                    .map(s -> new MultipleSelector(s))
                    .collect(Collectors.toList()));
        }
    }

    public Selector tag(String tag) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream().map(s -> s.tag(tag)).collect(Collectors.toList());
        return res;
    }

    public Selector attribute(String attr, String value, boolean contains, boolean enabled) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.attribute(attr, value, contains, enabled)).collect(Collectors.toList());
        return res;
    }

    public Selector position(int pos) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream().map(s -> s.position(pos)).collect(Collectors.toList());
        return res;
    }

    public Selector text(String text, boolean dot, boolean contains, boolean enabled) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.text(text, dot, contains, enabled)).collect(Collectors.toList());
        return res;
    }

    public Selector name(String name) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream().map(s -> s.hardName(name)).collect(Collectors.toList());
        return res;
    }

    public Selector axis_attribute(Axes axis, SelectorBehavior selector, boolean enabled) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.axis_attribute(axis, selector, enabled)).collect(Collectors.toList());
        return res;
    }

    public String viewForAxisAttribute(Axes axis) {
        return this.selectors.stream().map(s -> s.viewForAxisAttribute(axis)).collect(Collectors.joining(" | "));
    }

    public Selector axis(Axes axis, Selector selector) {
        Selector var1 = new Selector(this);
        Selector var2 = new Selector(selector);
        List<MultipleSelector> newSelectors = new ArrayList<>();
        for (int i = 0; i < var2.selectors.size(); i++) {
            for (int j = 0; j < var1.selectors.size(); j++) {
                newSelectors.add(var1.selectors.get(j).axis(axis, var2.selectors.get(i)));
            }
        }
        return new Selector(newSelectors);
    }

    public Selector base_axis(Axes axis) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream().map(s -> s.base_axis(axis)).collect(Collectors.toList());
        return res;
    }

    public String getName() {
        return selectors.stream().map(SelectorBehavior::getName).collect(Collectors.toSet()).stream().collect(Collectors.joining(") or (", "(", ")"));
    }

    public String toXPath() {
        return selectors.stream().map(SelectorBehavior::toXPath).collect(Collectors.joining(" | "));
    }

    public String toString() {
        return toXPath();
    }

    // Helpers
    public Selector text(String text) {
        return this.text(text, false, false, true);
    }

    public Selector textContains(String text) {
        return this.text(text, false, true, true);
    }

    public Selector descendantText(String text) {
        return this.descendant(new Selector().text(text, false, false, true));
    }

    public Selector descendantTextContains(String text) {
        return this.descendant(new Selector().text(text, false, true, true));
    }

    public Selector isDescendantText(String text) {
        return this.isDescendant(new Selector().text(text, false, false, true));
    }

    public Selector isDescendantTextContains(String text) {
        return this.isDescendant(new Selector().text(text, false, true, true));
    }

    public Selector isNotDescendantText(String text) {
        return this.isNotDescendant(new Selector().text(text, false, false, true));
    }

    public Selector isNotDescendantTextContains(String text) {
        return this.isNotDescendant(new Selector().text(text, false, true, true));
    }
}
