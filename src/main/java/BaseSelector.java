import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseSelector implements Selector<BaseSelector> {

    private String name = "";
    private Axes axis = Axes.DESCENDANT;
    private String tag = "*";
    private List<String> attributes;
    private int position = 0;
    protected int hashCode;

    public BaseSelector() {
        this.hashCode = new Random().nextInt();
        this.attributes = new ArrayList<>();
    }

    public BaseSelector(BaseSelector baseSelector) {
        this(baseSelector, false);
    }

    public BaseSelector(BaseSelector baseSelector, boolean saveHashCode) {
        this.hashCode = (saveHashCode) ? baseSelector.hashCode : new Random().nextInt();
        this.axis = baseSelector.axis;
        this.tag = baseSelector.tag;
        this.attributes = new ArrayList<String>(baseSelector.attributes);
        this.position = baseSelector.position;
    }

    public BaseSelector tag(String tag) {
        BaseSelector res = new BaseSelector(this);
        res.tag = tag;
        return res;
    }

    public BaseSelector attribute(String attr, String value, boolean contains, boolean enabled) {
        BaseSelector res = new BaseSelector(this);
        String var1 = String.format("@%s", attr);
        String var2 = String.format("'%s'", value);
        String var3 = (value == null) ? var1 : String.format((contains) ? "%s,%s" : "%s=%s", var1, var2);
        String var4 = String.format((contains) ? "contains(%s)" : "%s", var3);
        String resAttr = String.format((enabled) ? "[%s]" : "[not(%s)]", var4);
        res.attributes.add(resAttr);
        return res;
    }

    public BaseSelector position(int pos) {
        BaseSelector res = new BaseSelector(this, true);
        res.position = Math.max(pos, 0);
        return res;
    }

    public BaseSelector text(String text, boolean dot, boolean contains, boolean enabled) {
        BaseSelector res = new BaseSelector(this);
        String var1 = (dot) ? "." : "text()";
        String var2 = String.format((contains) ? "contains(%s,'%s')" : "%s='%s'", var1, text);
        String resAttr = String.format((enabled) ? "[%s]" : "[not(%s)]", var2);
        res.attributes.add(resAttr);
        return res;
    }

    public BaseSelector name(String name) {
        this.name = name;
        return this;
    }

    public BaseSelector axis_attribute(Axes axis, Selector selector, boolean enabled) {
        BaseSelector res = new BaseSelector(this, true);
        String var1 = String.format((enabled) ? "[%s]" : "[not(%s)]", selector.viewForAxisAttribute(axis));
        res.attributes.add(var1);
        return res;
    }

    public LinksSelector axis(Axes axis, Selector selector) {
        return null;
    }

    public String viewForAxisAttribute(Axes axis) {
        BaseSelector selector = new BaseSelector(this);
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

    public boolean equals(BaseSelector selector) {
        return this.hashCode == selector.hashCode;
    }
}
