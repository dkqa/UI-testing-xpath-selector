import java.util.List;

public class LinksSelector implements Selector<LinksSelector> {

    private String name;
    private BaseSelector lastLink;
    private List<BaseSelector> links;

    public LinksSelector tag(String tag) {
        return null;
    }

    public LinksSelector attribute(boolean enabled, String attr, String value, boolean contains) {
        return null;
    }

    public LinksSelector position(int pos) {
        return null;
    }

    public LinksSelector text(boolean dot, boolean enabled, String text, boolean contains) {
        return null;
    }

    public LinksSelector name(String name) {
        return null;
    }

    public String getName() {
        return name;
    }

    public String toXPath() {
        return null;
    }
}
