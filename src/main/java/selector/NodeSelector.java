package selector;

import selector.predicates.AttrPredicate;
import selector.predicates.AxisPredicate;
import selector.predicates.ISelectorPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class NodeSelector implements ISelector<NodeSelector> {

    protected String name = "";
    private Axes axis = Axes.DESCENDANT;
    private String tag = "*";
    private List<ISelectorPredicate> attributes;
    private int position = 0;
    protected int hashCode;

    NodeSelector() {
        this.hashCode = new Random().nextInt();
        this.attributes = new ArrayList<>();
    }

    NodeSelector(NodeSelector nodeSelector) {
        this(nodeSelector, false);
    }

    NodeSelector(NodeSelector nodeSelector, boolean saveHashCode) {
        this.name = nodeSelector.name;
        this.hashCode = (saveHashCode) ? nodeSelector.hashCode : new Random().nextInt();
        this.axis = nodeSelector.axis;
        this.tag = nodeSelector.tag;
        this.attributes = new ArrayList<>(nodeSelector.attributes);
        this.position = nodeSelector.position;
    }

    public NodeSelector tag(String tag) {
        NodeSelector res = new NodeSelector(this);
        res.tag = tag;
        return res;
    }

    public NodeSelector attribute(ISelectorPredicate predicate) {
        NodeSelector res = new NodeSelector(this);
        res.attributes.add(predicate);
        return res;
    }

    public NodeSelector soft_attribute(ISelectorPredicate predicate) {
        NodeSelector res = new NodeSelector(this, true);
        res.attributes.add(predicate);
        return res;
    }

    public NodeSelector attribute(String attr, String value, boolean contains, boolean enabled) {
        AttrPredicate predicate = new AttrPredicate();
        predicate = predicate.attr(attr, value);
        if (contains) {
            predicate = predicate.contains();
        }
        if (!enabled) {
            predicate = predicate.not();
        }
        return attribute(predicate);
    }

    public NodeSelector position(int pos) {
        NodeSelector res = new NodeSelector(this, true);
        res.position = Math.max(pos, 0);
        return res;
    }

    public NodeSelector text(String text, boolean dot, boolean contains, boolean enabled) {
        AttrPredicate predicate = new AttrPredicate();
        predicate = (dot) ? predicate.attr(".", text) : predicate.attr("text", text);
        if (contains) {
            predicate = predicate.contains();
        }
        if (!enabled) {
            predicate = predicate.not();
        }
        return attribute(predicate);
    }

    public NodeSelector name(String name) {
        NodeSelector res = new NodeSelector(this, true);
        res.name = name;
        return res;
    }

    public NodeSelector axis_attribute(Axes axis, ISelector selector, boolean enabled) {
        AxisPredicate predicate = new AxisPredicate();
        predicate = (enabled) ? predicate.selector(axis, selector) : predicate.selector(axis, selector).not();
        return soft_attribute(predicate);
    }

    public NodeSelector axis(Axes axis, NodeSelector selector) {
        return null;
    }

    public NodeSelector base_axis(Axes axis) {
        NodeSelector res = new NodeSelector(this, true);
        res.axis = axis;
        return res;
    }

    public String viewForAxisAttribute(Axes axis) {
        NodeSelector selector = new NodeSelector(this);
        selector.axis = axis;
        return selector.toXPath().replaceFirst("/", "");
    }

    public String getName() {
        return (name.equals("")) ? toXPath() : name;
    }

    public String toXPath() {
        String axis = this.axis.toString();
        String tag = this.tag;
        String attributes = this.attributes.stream()
                .map(ISelectorPredicate::toAttr)
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining());
        String position = (this.position == 0) ? "" : String.format("[%d]", this.position);
        String xPath = "/" + axis + tag + attributes + position;
        return xPath;
    }

    public boolean equals(NodeSelector selector) {
        return this.hashCode == selector.hashCode;
    }
}
