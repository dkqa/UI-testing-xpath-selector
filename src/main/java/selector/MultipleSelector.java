package selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultipleSelector implements SelectorBehavior<MultipleSelector> {

    protected List<SimpleSelector> links;

    public MultipleSelector() {
        this.links = new ArrayList<>();
        this.links.add(new SimpleSelector());
    }

    public MultipleSelector(MultipleSelector multipleSelector) {
        this.links = multipleSelector.links.stream()
                .map(s -> new SimpleSelector(s, true))
                .collect(Collectors.toList());
    }

    public MultipleSelector(List<SimpleSelector> links) {
        this.links = links.stream()
                .map(s -> new SimpleSelector(s, true))
                .collect(Collectors.toList());
    }

    public MultipleSelector(SimpleSelector... selectors) {
        this(Arrays.asList(selectors));
    }

    private void replaceLast(SimpleSelector selector) {
        this.links.remove(this.links.size() - 1);
        this.links.add(selector);
    }

    private SimpleSelector last() {
        return this.links.get(this.links.size() - 1);
    }

    public MultipleSelector tag(String tag) {
        MultipleSelector res = new MultipleSelector(this);
        res.replaceLast(res.last().tag(tag));
        return res;
    }

    public MultipleSelector attribute(String attr, String value, boolean contains, boolean enabled) {
        MultipleSelector res = new MultipleSelector(this);
        res.replaceLast(res.last().attribute(attr, value, contains, enabled));
        return res;
    }

    public MultipleSelector position(int pos) {
        MultipleSelector res = new MultipleSelector(this);
        res.replaceLast(res.last().position(pos));
        return res;
    }

    public MultipleSelector text(String text, boolean dot, boolean contains, boolean enabled) {
        MultipleSelector res = new MultipleSelector(this);
        res.replaceLast(res.last().text(text, dot, contains, enabled));
        return res;
    }

    public MultipleSelector name(String name) {
        MultipleSelector res = new MultipleSelector(this);
        res.replaceLast(res.last().name(name));
        return res;
    }

    public MultipleSelector hardName(String name) {
        MultipleSelector res = new MultipleSelector(this);
        res.links.forEach(s -> s.name = "");
        res.last().name = name;
        return res;
    }

    public MultipleSelector axis_attribute(Axes axis, SelectorBehavior selector, boolean enabled) {
        MultipleSelector res = new MultipleSelector(this);
        res.replaceLast(res.last().axis_attribute(axis, selector, enabled));
        return res;
    }

    public MultipleSelector axis(Axes axis, MultipleSelector selector) {
        MultipleSelector var1 = new MultipleSelector(this);
        MultipleSelector var2 = new MultipleSelector(selector);

        for (int i = 0; i < var1.links.size(); i++) {
            if (var1.links.get(i).equals(var2.links.get(0))) {
                var2.links.remove(0);
            } else {
                break;
            }
        }
        if (var2.links.size() > 0) {
            var1.links.addAll(var2.base_axis(axis).links);
        }
        return var1;
    }

    public MultipleSelector base_axis(Axes axis) {
        MultipleSelector res = new MultipleSelector(this);
        res.links.set(0, this.links.get(0).base_axis(axis));
        return res;
    }

    public String viewForAxisAttribute(Axes axis) {
        return this.base_axis(axis).toXPath().replaceFirst("/", "");
    }

    public String getName() {
        String name = links.stream()
                .map(s -> s.name)
                .filter(n -> !n.equals(""))
                .collect(Collectors.joining(" - "));
        return (name.equals("")) ? toXPath() : name;
    }

    public String toXPath() {
        return links.stream().map(SelectorBehavior::toXPath).collect(Collectors.joining());
    }
}
