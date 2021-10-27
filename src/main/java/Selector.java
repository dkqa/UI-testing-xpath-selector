public interface Selector<T extends Selector> {

    String getName();
    T tag(String tag);
    T attribute(String attr, String value, boolean contains, boolean enabled);
    T position(int pos);
    T text(String text, boolean dot, boolean contains, boolean enabled);
    T name(String name);
    T axis_attribute(Axes axis, Selector selector, boolean enabled);
    <S extends Selector> S axis(Axes axis, Selector selector);
    String viewForAxisAttribute(Axes axis);
    String toXPath();

    default T isFollowing(Selector selector) {
        return axis_attribute(Axes.FOLLOWING, selector, true);
    }
    default T isFollowingSibling(Selector selector) {
        return axis_attribute(Axes.FOLLOWING_SIBLING, selector, true);
    }
    default T isParent(Selector selector) {
        return axis_attribute(Axes.PARENT, selector, true);
    }
    default T isPreceding(Selector selector) {
        return axis_attribute(Axes.PRECEDING, selector, true);
    }
    default T isAncestor(Selector selector) {
        return axis_attribute(Axes.ANCESTOR, selector, true);
    }
    default T isDescendant(Selector selector) {
        return axis_attribute(Axes.DESCENDANT, selector, true);
    }
    default T isDescendantOrSelf(Selector selector) {
        return axis_attribute(Axes.DESCENDANT_OR_SELF, selector, true);
    }

    default T isNotFollowing(Selector selector) {
        return axis_attribute(Axes.FOLLOWING, selector, false);
    }
    default T isNotFollowingSibling(Selector selector) {
        return axis_attribute(Axes.FOLLOWING_SIBLING, selector, false);
    }
    default T isNotParent(Selector selector) {
        return axis_attribute(Axes.PARENT, selector, false);
    }
    default T isNotPreceding(Selector selector) {
        return axis_attribute(Axes.PRECEDING, selector, false);
    }
    default T isNotAncestor(Selector selector) {
        return axis_attribute(Axes.ANCESTOR, selector, false);
    }
    default T isNotDescendant(Selector selector) {
        return axis_attribute(Axes.DESCENDANT, selector, false);
    }
    default T isNotDescendantOrSelf(Selector selector) {
        return axis_attribute(Axes.DESCENDANT_OR_SELF, selector, false);
    }
}
