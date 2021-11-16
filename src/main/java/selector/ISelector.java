package selector;

import selector.predicates.ISelectorPredicate;

public interface ISelector<T extends ISelector> {

    String getName();
    T tag(String tag);
    T attribute(ISelectorPredicate predicate);
    T attribute(String attr, String value, boolean contains, boolean enabled);
    T position(int pos);
    T text(String text, boolean dot, boolean contains, boolean enabled);
    T name(String name);
    T axis_attribute(Axes axis, ISelector selector, boolean enabled);
    String viewForAxisAttribute(Axes axis);
    T axis(Axes axis, T selector);
    T base_axis(Axes axis);
    String toXPath();

    default T following(T selector) {
        return axis(Axes.FOLLOWING, selector);
    }
    default T followingSibling(T selector) {
        return axis(Axes.FOLLOWING_SIBLING, selector);
    }
    default T child(T selector) {
        return axis(Axes.CHILD, selector);
    }
    default T parent(T selector) {
        return axis(Axes.PARENT, selector);
    }
    default T preceding(T selector) {
        return axis(Axes.PRECEDING, selector);
    }
    default T ancestor(T selector) {
        return axis(Axes.ANCESTOR, selector);
    }
    default T descendant(T selector) {
        return axis(Axes.DESCENDANT, selector);
    }
    default T descendantOrSelf(T selector) {
        return axis(Axes.DESCENDANT_OR_SELF, selector);
    }

    default T isFollowing(T selector) {
        return axis_attribute(Axes.FOLLOWING, selector, true);
    }
    default T isFollowingSibling(T selector) {
        return axis_attribute(Axes.FOLLOWING_SIBLING, selector, true);
    }
    default T isParent(T selector) {
        return axis_attribute(Axes.PARENT, selector, true);
    }
    default T isPreceding(T selector) {
        return axis_attribute(Axes.PRECEDING, selector, true);
    }
    default T isAncestor(T selector) {
        return axis_attribute(Axes.ANCESTOR, selector, true);
    }
    default T isDescendant(T selector) {
        return axis_attribute(Axes.DESCENDANT, selector, true);
    }
    default T isDescendantOrSelf(T selector) {
        return axis_attribute(Axes.DESCENDANT_OR_SELF, selector, true);
    }

    default T isNotFollowing(T selector) {
        return axis_attribute(Axes.FOLLOWING, selector, false);
    }
    default T isNotFollowingSibling(T selector) {
        return axis_attribute(Axes.FOLLOWING_SIBLING, selector, false);
    }
    default T isNotParent(T selector) {
        return axis_attribute(Axes.PARENT, selector, false);
    }
    default T isNotPreceding(T selector) {
        return axis_attribute(Axes.PRECEDING, selector, false);
    }
    default T isNotAncestor(T selector) {
        return axis_attribute(Axes.ANCESTOR, selector, false);
    }
    default T isNotDescendant(T selector) {
        return axis_attribute(Axes.DESCENDANT, selector, false);
    }
    default T isNotDescendantOrSelf(T selector) {
        return axis_attribute(Axes.DESCENDANT_OR_SELF, selector, false);
    }
}
