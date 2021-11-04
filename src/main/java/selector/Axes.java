package selector;

public enum Axes {

    FOLLOWING("following::"),
    FOLLOWING_SIBLING("following-sibling::"),
    PARENT("parent::"),
    PRECEDING("preceding::"),
    ANCESTOR("ancestor::"),
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
