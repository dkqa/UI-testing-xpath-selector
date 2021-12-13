package com.dkqa.selector.predicates;

public class DotPredicate extends TextPredicate {

    @Override
    protected String getBody() {
        String res;
        String var1 = ".";
        String var2 = String.format((normalizeSpace) ? "normalize-space(%s)" : "%s", var1);
        String var3 = (attrValue == null) ? var2 : String.format((contains) ? "%s,'%s'" : "%s='%s'", var2, attrValue);
        res = String.format((contains) ? "contains(%s)" : "%s", var3);
        return res;
    }

}
