package com.dkqa.selector;

public enum Axes {

    SELF("self::"),
    FOLLOWING("following::"),
    FOLLOWING_SIBLING("following-sibling::"),
    PARENT("parent::"),
    PRECEDING("preceding::"),
    PRECEDING_SIBLING("preceding-sibling::"),
    ANCESTOR("ancestor::"),
    ANCESTOR_OR_SELF("ancestor-or-self::"),
    DESCENDANT("descendant::"),
    DESCENDANT_OR_SELF("descendant-or-self::"),
    CHILD("child::");

    private String axis;
    Axes(String axis) {
        this.axis = axis;
    }
    @Override
    public String toString() {
        return axis;
    }
}
