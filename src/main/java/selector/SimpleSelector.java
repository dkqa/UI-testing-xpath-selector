package selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SimpleSelector implements SelectorBehavior<SimpleSelector> {

    protected String name = "";
    private Axes axis = Axes.DESCENDANT;
    private String tag = "*";
    private List<String> attributes;
    private int position = 0;
    protected int hashCode;

    SimpleSelector() {
        this.hashCode = new Random().nextInt();
        this.attributes = new ArrayList<>();
    }

    SimpleSelector(SimpleSelector simpleSelector) {
        this(simpleSelector, false);
    }

    SimpleSelector(SimpleSelector simpleSelector, boolean saveHashCode) {
        this.name = simpleSelector.name;
        this.hashCode = (saveHashCode) ? simpleSelector.hashCode : new Random().nextInt();
        this.axis = simpleSelector.axis;
        this.tag = simpleSelector.tag;
        this.attributes = new ArrayList<>(simpleSelector.attributes);
        this.position = simpleSelector.position;
    }

    public SimpleSelector tag(String tag) {
        SimpleSelector res = new SimpleSelector(this);
        res.tag = tag;
        return res;
    }

    public SimpleSelector attribute(String attr, String value, boolean contains, boolean enabled) {
        SimpleSelector res = new SimpleSelector(this);
        String var1 = String.format("@%s", attr);
        String var2 = String.format("'%s'", value);
        String var3 = (value == null) ? var1 : String.format((contains) ? "%s,%s" : "%s=%s", var1, var2);
        String var4 = String.format((contains) ? "contains(%s)" : "%s", var3);
        String resAttr = String.format((enabled) ? "[%s]" : "[not(%s)]", var4);
        res.attributes.add(resAttr);
        return res;
    }

    public SimpleSelector position(int pos) {
        SimpleSelector res = new SimpleSelector(this, true);
        res.position = Math.max(pos, 0);
        return res;
    }

    public SimpleSelector text(String text, boolean dot, boolean contains, boolean enabled) {
        SimpleSelector res = new SimpleSelector(this);
        String var1 = (dot) ? "." : "text()";
        String var2 = String.format((contains) ? "contains(%s,'%s')" : "%s='%s'", var1, text);
        String resAttr = String.format((enabled) ? "[%s]" : "[not(%s)]", var2);
        res.attributes.add(resAttr);
        return res;
    }

    public SimpleSelector name(String name) {
        SimpleSelector res = new SimpleSelector(this, true);
        res.name = name;
        return res;
    }

    public SimpleSelector axis_attribute(Axes axis, SelectorBehavior selector, boolean enabled) {
        SimpleSelector res = new SimpleSelector(this, true);
        String var1 = String.format((enabled) ? "[%s]" : "[not(%s)]", selector.viewForAxisAttribute(axis));
        res.attributes.add(var1);
        return res;
    }

    public SimpleSelector axis(Axes axis, SimpleSelector selector) {
        return null;
    }

    public SimpleSelector base_axis(Axes axis) {
        SimpleSelector res = new SimpleSelector(this, true);
        res.axis = axis;
        return res;
    }

    public String viewForAxisAttribute(Axes axis) {
        SimpleSelector selector = new SimpleSelector(this);
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

    public boolean equals(SimpleSelector selector) {
        return this.hashCode == selector.hashCode;
    }
}
