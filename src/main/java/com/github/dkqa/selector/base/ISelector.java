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
import com.github.dkqa.selector.predicates.ISelectorPredicate;

public interface ISelector<T extends ISelector> {

    T base_axis(Axes axis);
    T tag(String tag);
    T attribute(ISelectorPredicate predicate);
    T axis(Axes axis, T selector);
    T name(String name);
    String getName();
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
