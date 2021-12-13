package com.dkqa.selector.predicates;

public class TextPredicate extends SelectorPredicate<TextPredicate> {

    protected boolean contains = false;
    protected String attrValue;
    protected boolean normalizeSpace = false;

    public TextPredicate value(String value) {
        this.attrValue = value;
        return this;
    }

    public TextPredicate normalize_space() {
        this.normalizeSpace = true;
        return this;
    }

    public TextPredicate contains() {
        this.contains = true;
        return this;
    }

    @Override
    protected String getBody() {
        String res;
        String var1 = "text()";
        String var2 = String.format((normalizeSpace) ? "normalize-space(%s)" : "%s", var1);
        String var3 = (attrValue == null) ? var2 : String.format((contains) ? "%s,'%s'" : "%s='%s'", var2, attrValue);
        res = String.format((contains) ? "contains(%s)" : "%s", var3);
        return res;
    }
}
