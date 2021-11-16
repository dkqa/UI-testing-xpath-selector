package selector.predicates;

public class AttrPredicate extends SelectorPredicate<AttrPredicate> {

    protected boolean contains = false;
    protected String attrName;
    protected String attrValue;
    protected boolean normalizeSpace = false;

    public AttrPredicate() {}

    public AttrPredicate(String name, String value, boolean contains, boolean enabled) {
        this.attrName = name;
        this.attrValue = value;
        this.contains = contains;
        this.enabled = enabled;
    }

    public AttrPredicate name(String name) {
        this.attrName = name;
        return this;
    }

    public AttrPredicate value(String value) {
        this.attrValue = value;
        return this;
    }

    public AttrPredicate normalize_space() {
        this.normalizeSpace = true;
        return this;
    }

    public AttrPredicate contains() {
        this.contains = true;
        return this;
    }

    protected String getBody() {
        String res = "";
        if (attrName != null) {
            String var1 = (attrName.equals("text")) ? "text()" : (attrName.equals(".")) ? "." : "@" + attrName;
            String var2 = String.format((normalizeSpace) ? "normalize-space(%s)" : "%s", var1);
            String var3 = (attrValue == null) ? var2 : String.format((contains) ? "%s,'%s'" : "%s='%s'", var2, attrValue);
            res = String.format((contains) ? "contains(%s)" : "%s", var3);
        }

        return res;
    }

    @Override
    public String predicateName() {
        return "Attribute";
    }
}
