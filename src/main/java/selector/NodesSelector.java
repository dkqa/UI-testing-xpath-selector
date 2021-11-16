package selector;

import selector.predicates.ISelectorPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class NodesSelector implements ISelector<NodesSelector> {

    protected List<NodeSelector> nodes;

    NodesSelector() {
        this.nodes = new ArrayList<>();
        this.nodes.add(new NodeSelector());
    }

    NodesSelector(NodesSelector nodesSelector) {
        this.nodes = nodesSelector.nodes.stream()
                .map(s -> new NodeSelector(s, true))
                .collect(Collectors.toList());
    }

    private void replaceLast(NodeSelector selector) {
        this.nodes.remove(this.nodes.size() - 1);
        this.nodes.add(selector);
    }

    private NodeSelector last() {
        return this.nodes.get(this.nodes.size() - 1);
    }

    public NodesSelector tag(String tag) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().tag(tag));
        return res;
    }

    public NodesSelector attribute(ISelectorPredicate predicate) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().attribute(predicate));
        return res;    }

    public NodesSelector attribute(String attr, String value, boolean contains, boolean enabled) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().attribute(attr, value, contains, enabled));
        return res;
    }

    public NodesSelector position(int pos) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().position(pos));
        return res;
    }

    public NodesSelector text(String text, boolean dot, boolean contains, boolean enabled) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().text(text, dot, contains, enabled));
        return res;
    }

    public NodesSelector name(String name) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().name(name));
        return res;
    }

    public NodesSelector nameHard(String name) {
        NodesSelector res = new NodesSelector(this);
        res.nodes.forEach(s -> s.name = "");
        res.last().name = name;
        return res;
    }

    public NodesSelector axis_attribute(Axes axis, ISelector selector, boolean enabled) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().axis_attribute(axis, selector, enabled));
        return res;
    }

    public NodesSelector axis(Axes axis, NodesSelector selector) {
        NodesSelector var1 = new NodesSelector(this);
        NodesSelector var2 = new NodesSelector(selector);

        for (int i = 0; i < var1.nodes.size(); i++) {
            if (var1.nodes.get(i).equals(var2.nodes.get(0))) {
                var2.nodes.remove(0);
            } else {
                break;
            }
        }
        if (var2.nodes.size() > 0) {
            var1.nodes.addAll(var2.base_axis(axis).nodes);
        }
        return var1;
    }

    public NodesSelector base_axis(Axes axis) {
        NodesSelector res = new NodesSelector(this);
        res.nodes.set(0, this.nodes.get(0).base_axis(axis));
        return res;
    }

    public String viewForAxisAttribute(Axes axis) {
        return this.base_axis(axis).toXPath().replaceFirst("/", "");
    }

    public String getName() {
        String name = nodes.stream()
                .map(s -> s.name)
                .filter(n -> !n.equals(""))
                .collect(Collectors.joining(" - "));
        return (name.equals("")) ? toXPath() : name;
    }

    public String toXPath() {
        return nodes.stream().map(ISelector::toXPath).collect(Collectors.joining());
    }
}
