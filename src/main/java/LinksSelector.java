import java.util.List;

public class LinksSelector implements Selector<LinksSelector> {

    private BaseSelector lastLink;
    private List<BaseSelector> links;

    public LinksSelector tag(String tag) {
        return null;
    }

    public LinksSelector attribute(String attr, String value, boolean contains, boolean enabled) {
        return null;
    }

    public LinksSelector position(int pos) {
        return null;
    }

    public LinksSelector text(String text, boolean dot, boolean enabled, boolean contains) {
        return null;
    }

    public LinksSelector name(String name) {
        return null;
    }

    public String getName() {
        return null;
    }

    public String toXPath() {
        return null;
    }
}
