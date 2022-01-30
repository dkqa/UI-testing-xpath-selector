//    MIT License
//
//    Copyright (c) 2021 Danil Kopylov
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy
//    of this software and associated documentation files (the "Software"), to deal
//    in the Software without restriction, including without limitation the rights
//    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//    copies of the Software, and to permit persons to whom the Software is
//    furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all
//    copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//    SOFTWARE.

package com.github.dkqa.selector.base;

import com.github.dkqa.selector.Axes;
import com.github.dkqa.selector.predicates.*;

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
        res.selectors = res.selectors.stream()
                .map(s -> s.tag(tag))
                .collect(Collectors.toList());
        return res;
    }

    public Selector attribute(ISelectorPredicate predicate) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.attribute(predicate))
                .collect(Collectors.toList());
        return res;    
    }

    public Selector replaceAttribute(ISelectorPredicate predicate) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.replaceAttribute(predicate))
                .collect(Collectors.toList());
        return res;
    }

    public Selector name(String name) {
        Selector res = new Selector(this);
        res.selectors = res.selectors.stream()
                .map(s -> s.nameHard(name))
                .collect(Collectors.toList());
        return res;
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
        res.selectors = res.selectors.stream()
                .map(s -> s.base_axis(axis))
                .collect(Collectors.toList());
        return res;
    }

    public String getName() {
        return selectors.stream()
                .map(ISelector::getName)
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.joining(") or (", "(", ")"));
    }

    public String toXPath() {
        return selectors.stream()
                .map(ISelector::toXPath)
                .collect(Collectors.joining(" | "));
    }

    public Selector position(int pos) {
        return this.attribute(new PositionPredicate().position(pos));
    }

    public String toString() {
        return toXPath();
    }

    // Shortened methods
    public Selector isSelf(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.SELF, selector));
    }
    public Selector isFollowing(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.FOLLOWING, selector));
    }
    public Selector isFollowingSibling(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.FOLLOWING_SIBLING, selector));
    }
    public Selector isParent(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.PARENT, selector));
    }
    public Selector isPreceding(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.PRECEDING, selector));
    }
    public Selector isPrecedingSibling(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.PRECEDING_SIBLING, selector));
    }
    public Selector isAncestor(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.ANCESTOR, selector));
    }
    public Selector isAncestorOrSelf(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.ANCESTOR_OR_SELF, selector));
    }
    public Selector isDescendant(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.DESCENDANT, selector));
    }
    public Selector isDescendantOrSelf(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.DESCENDANT_OR_SELF, selector));
    }

    public Selector isNotSelf(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.SELF, selector).not());
    }
    public Selector isNotFollowing(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.FOLLOWING, selector).not());
    }
    public Selector isNotFollowingSibling(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.FOLLOWING_SIBLING, selector).not());
    }
    public Selector isNotParent(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.PARENT, selector).not());
    }
    public Selector isNotPreceding(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.PRECEDING, selector).not());
    }
    public Selector isNotPrecedingSibling(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.PRECEDING_SIBLING, selector).not());
    }
    public Selector isNotAncestor(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.ANCESTOR, selector).not());
    }
    public Selector isNotAncestorOrSelf(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.ANCESTOR_OR_SELF, selector).not());
    }
    public Selector isNotDescendant(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.DESCENDANT, selector).not());
    }
    public Selector isNotDescendantOrSelf(Selector selector) {
        return attribute(new AxisPredicate().selector(Axes.DESCENDANT_OR_SELF, selector).not());
    }

    public Selector isDescendantText(String text) {
        return this.isDescendant(new Selector().attribute(new TextPredicate().value(text)));
    }

    public Selector isDescendantTextContains(String text) {
        return this.isDescendant(new Selector().attribute(new TextPredicate().value(text).contains()));
    }

    public Selector isNotDescendantText(String text) {
        return this.isNotDescendant(new Selector().attribute(new TextPredicate().value(text).not()));
    }

    public Selector isNotDescendantTextContains(String text) {
        return this.isNotDescendant(new Selector().attribute(new TextPredicate().value(text).contains().not()));
    }
}
