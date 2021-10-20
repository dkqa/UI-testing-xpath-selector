import java.util.ArrayList;
import java.util.List;

public class BaseSelector implements Selector<BaseSelector> {

    private String name;
    private Axes axis = Axes.DEFAULT;
    private String tag = "*";
    private List<String> attributes;
    private int position;

    public BaseSelector() {
    }

    public BaseSelector(BaseSelector baseSelector) {
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
        return null;
    }

    public BaseSelector position(int pos) {
        return null;
    }

    public BaseSelector text(boolean enabled, String text, boolean contains) {
        return null;
    }

    public String getName() {
        return name;
    }

    public String toXPath() {
        return null;
    }
}
