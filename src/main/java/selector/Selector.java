package selector;

import selector.predicates.AttrPredicate;
import selector.predicates.AxisPredicate;
import selector.predicates.ISelectorPredicate;
import selector.predicates.PositionPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Selector implements ISelector<Selector> {

    private List<NodesSelector> selectors;

    public Selector() {
        this.selectors = Arrays.asList(new NodesSelector());
    }

    public Selector(Selector... selectors) {
        this.selectors = new ArrayList<>();
        for (Selector selector : selectors) {
            this.selectors.addAll(selector.selectors.stream()
                    .map(s -> new NodesSelector(s))
                    .collect(Collectors.toList()));
        }
    }

    private Selector(List<NodesSelector> selectors) {
        this.selectors = selectors;
    }

    public Selector tag(String tag) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream().map(s -> s.tag(tag)).collect(Collectors.toList());
        return res;
    }

    public Selector attribute(ISelectorPredicate predicate) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.attribute(predicate)).collect(Collectors.toList());
        return res;    
    }

    public Selector attribute(String attr, String value, boolean contains, boolean enabled) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.attribute(new AttrPredicate(attr, value, contains, enabled)))
                .collect(Collectors.toList());
        return res;
    }

    public Selector position(int pos) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.replaceAttribute(new PositionPredicate().position(pos)))
                .collect(Collectors.toList());
        return res;
    }

    public Selector textAttribute(String text, boolean dot, boolean contains, boolean enabled) {
        Selector res = new Selector(this);
        AttrPredicate predicate = (dot) ? new AttrPredicate(".", text, contains, enabled) : new AttrPredicate("text", text, contains, enabled);
        res.selectors = res.selectors.stream()
                .map(s -> s.attribute(predicate))
                .collect(Collectors.toList());
        return res;
    }

    public Selector name(String name) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream().map(s -> s.nameHard(name)).collect(Collectors.toList());
        return res;
    }

    public Selector axisAttribute(Axes axis, ISelector selector, boolean enabled) {
        Selector res = new Selector(this);
        AxisPredicate predicate = (enabled) ? new AxisPredicate().selector(axis, selector) : new AxisPredicate().selector(axis, selector).not();
        res.selectors = res.selectors.stream()
                .map(s -> s.attribute(predicate))
                .collect(Collectors.toList());
        return res;
    }

    public String viewForAxisAttribute(Axes axis) {
        return this.selectors.stream().map(s -> s.viewForAxisAttribute(axis)).collect(Collectors.joining(" | "));
    }

    public Selector axis(Axes axis, Selector selector) {
        Selector var1 = new Selector(this);
        Selector var2 = new Selector(selector);
        List<NodesSelector> newSelectors = new ArrayList<>();
        for (int i = 0; i < var2.selectors.size(); i++) {
            for (int j = 0; j < var1.selectors.size(); j++) {
                newSelectors.add(var1.selectors.get(j).axis(axis, var2.selectors.get(i)));
            }
        }
        return new Selector(newSelectors);
    }

    public Selector base_axis(Axes axis) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream().map(s -> s.base_axis(axis)).collect(Collectors.toList());
        return res;
    }

    public String getName() {
        return selectors.stream().map(ISelector::getName).collect(Collectors.toSet()).stream().collect(Collectors.joining(") or (", "(", ")"));
    }

    public String toXPath() {
        return selectors.stream().map(ISelector::toXPath).collect(Collectors.joining(" | "));
    }

    public String toString() {
        return toXPath();
    }

    // Helpers
    public Selector isFollowing(Selector selector) {
        return axisAttribute(Axes.FOLLOWING, selector, true);
    }
    public Selector isFollowingSibling(Selector selector) {
        return axisAttribute(Axes.FOLLOWING_SIBLING, selector, true);
    }
    public Selector isParent(Selector selector) {
        return axisAttribute(Axes.PARENT, selector, true);
    }
    public Selector isPreceding(Selector selector) {
        return axisAttribute(Axes.PRECEDING, selector, true);
    }
    public Selector isAncestor(Selector selector) {
        return axisAttribute(Axes.ANCESTOR, selector, true);
    }
    public Selector isDescendant(Selector selector) {
        return axisAttribute(Axes.DESCENDANT, selector, true);
    }
    public Selector isDescendantOrSelf(Selector selector) {
        return axisAttribute(Axes.DESCENDANT_OR_SELF, selector, true);
    }

    public Selector isNotFollowing(Selector selector) {
        return axisAttribute(Axes.FOLLOWING, selector, false);
    }
    public Selector isNotFollowingSibling(Selector selector) {
        return axisAttribute(Axes.FOLLOWING_SIBLING, selector, false);
    }
    public Selector isNotParent(Selector selector) {
        return axisAttribute(Axes.PARENT, selector, false);
    }
    public Selector isNotPreceding(Selector selector) {
        return axisAttribute(Axes.PRECEDING, selector, false);
    }
    public Selector isNotAncestor(Selector selector) {
        return axisAttribute(Axes.ANCESTOR, selector, false);
    }
    public Selector isNotDescendant(Selector selector) {
        return axisAttribute(Axes.DESCENDANT, selector, false);
    }
    public Selector isNotDescendantOrSelf(Selector selector) {
        return axisAttribute(Axes.DESCENDANT_OR_SELF, selector, false);
    }

    public Selector isDescendantText(String text) {
        return this.isDescendant(new Selector().attribute(new AttrPredicate().name("text").value(text)));
    }

    public Selector isDescendantTextContains(String text) {
        return this.isDescendant(new Selector().attribute(new AttrPredicate().name("text").value(text).contains()));
    }

    public Selector isNotDescendantText(String text) {
        return this.isNotDescendant(new Selector().attribute(new AttrPredicate().name("text").value(text).not()));
    }

    public Selector isNotDescendantTextContains(String text) {
        return this.isNotDescendant(new Selector().attribute(new AttrPredicate().name("text").value(text).contains().not()));
    }
}
