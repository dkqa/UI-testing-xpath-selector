import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseSelector implements Selector<BaseSelector> {

    private String name;
    private Axes axis = Axes.DEFAULT;
    private String tag = "*";
    private List<String> attributes;
    private int position;
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
        this.name = baseSelector.name;
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

    public BaseSelector attribute(boolean enabled, String attr, String value, boolean contains) {
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
        res.position = (pos > 0) ? pos : null;
        return res;
    }

    public BaseSelector text(boolean dot, boolean enabled, String text, boolean contains) {
        BaseSelector res = new BaseSelector(this);
        String var1 = (dot) ? "." : "text()";
        String var2 = String.format((contains) ? "contains(%s, '%s')" : "%s='%s'", var1, text);
        String resAttr = String.format((enabled) ? "[%s]" : "[not(%s)]", var2);
        res.attributes.add(resAttr);
        return res;
    }

    public BaseSelector name(String name) {
        return null;
    }

    public String getName() {
        return name;
    }

    public String toXPath() {
        String xPath = axis.toString() + tag + String.join("", attributes);
        return xPath;
    }
}
