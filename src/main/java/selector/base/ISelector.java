package selector.base;

import selector.Axes;
import selector.predicates.ISelectorPredicate;

public interface ISelector<T extends ISelector> {

    String getName();
    T tag(String tag);
    T attribute(ISelectorPredicate predicate);
    T name(String name);
    T axis(Axes axis, T selector);
    T base_axis(Axes axis);
    String toXPath();

    default T self(T selector) {
        return axis(Axes.SELF, selector);
    }
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
    default T precedingSibling(T selector) {
        return axis(Axes.PRECEDING_SIBLING, selector);
    }
    default T ancestor(T selector) {
        return axis(Axes.ANCESTOR, selector);
    }
    default T ancestorOrSelf(T selector) {
        return axis(Axes.ANCESTOR_OR_SELF, selector);
    }
    default T descendant(T selector) {
        return axis(Axes.DESCENDANT, selector);
    }
    default T descendantOrSelf(T selector) {
        return axis(Axes.DESCENDANT_OR_SELF, selector);
    }

}
