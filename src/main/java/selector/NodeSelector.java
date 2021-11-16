package selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class NodeSelector implements ISelector<NodeSelector> {

    protected String name = "";
    private Axes axis = Axes.DESCENDANT;
    private String tag = "*";
    private List<String> attributes;
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

    public NodeSelector attribute(String attr, String value, boolean contains, boolean enabled) {
        NodeSelector res = new NodeSelector(this);
        String var1 = String.format("@%s", attr);
        String var2 = String.format("'%s'", value);
        String var3 = (value == null) ? var1 : String.format((contains) ? "%s,%s" : "%s=%s", var1, var2);
        String var4 = String.format((contains) ? "contains(%s)" : "%s", var3);
        String resAttr = String.format((enabled) ? "[%s]" : "[not(%s)]", var4);
        res.attributes.add(resAttr);
        return res;
    }

    public NodeSelector position(int pos) {
        NodeSelector res = new NodeSelector(this, true);
        res.position = Math.max(pos, 0);
        return res;
    }

    public NodeSelector text(String text, boolean dot, boolean contains, boolean enabled) {
        NodeSelector res = new NodeSelector(this);
        String var1 = (dot) ? "." : "text()";
        String var2 = String.format((contains) ? "contains(%s,'%s')" : "%s='%s'", var1, text);
        String resAttr = String.format((enabled) ? "[%s]" : "[not(%s)]", var2);
        res.attributes.add(resAttr);
        return res;
    }

    public NodeSelector name(String name) {
        NodeSelector res = new NodeSelector(this, true);
        res.name = name;
        return res;
    }

    public NodeSelector axis_attribute(Axes axis, ISelector selector, boolean enabled) {
        NodeSelector res = new NodeSelector(this, true);
        String var1 = String.format((enabled) ? "[%s]" : "[not(%s)]", selector.viewForAxisAttribute(axis));
        res.attributes.add(var1);
        return res;
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
        String attributes = String.join("", this.attributes);
        String position = (this.position == 0) ? "" : String.format("[%d]", this.position);
        String xPath = "/" + axis + tag + attributes + position;
        return xPath;
    }

    public boolean equals(NodeSelector selector) {
        return this.hashCode == selector.hashCode;
    }
}
