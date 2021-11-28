package com.dkqa.selector.predicates;

public abstract class SelectorPredicate<T extends SelectorPredicate> implements ISelectorPredicate {

    protected boolean enabled = true;

    public T not() {
        this.enabled = false;
        return (T) this;
    }

    protected abstract String getBody();

    public String toAttr() {
        String body = getBody();
        return (!body.equals("")) ? String.format((this.enabled) ? "[%s]" : "[not(%s)]", body) : "";
    }
}
