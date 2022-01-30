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
import com.github.dkqa.selector.predicates.PositionPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class NodeSelector implements ISelector<NodeSelector> {

    protected String name = "";
    private Axes axis = Axes.DESCENDANT;
    private String tag = "*";
    private List<ISelectorPredicate> attributes;
    protected int hashCode;

    NodeSelector() {
        this.hashCode = new Random().nextInt();
        this.attributes = new ArrayList<>();
    }

    NodeSelector(NodeSelector nodeSelector) {
        this(nodeSelector, false);
    }

    NodeSelector(NodeSelector nodeSelector, boolean saveHashCode) {
        this.name = nodeSelector.name;
        this.hashCode = (saveHashCode) ? nodeSelector.hashCode : new Random().nextInt();
        this.axis = nodeSelector.axis;
        this.tag = nodeSelector.tag;
        this.attributes = new ArrayList<>(nodeSelector.attributes);
    }

    public NodeSelector tag(String tag) {
        NodeSelector res = new NodeSelector(this);
        res.tag = tag;
        return res;
    }

    public NodeSelector attribute(ISelectorPredicate predicate) {
        NodeSelector res = new NodeSelector(this, true);
        res.attributes.add(predicate);
        return res;
    }

    public NodeSelector replaceAttribute(ISelectorPredicate predicate) {
        NodeSelector res = new NodeSelector(this, true);
        res.attributes = res.attributes.stream()
                .filter(a -> !a.getClass().getName().equals(predicate.getClass().getName()))
                .collect(Collectors.toList());
        res.attributes.add(predicate);
        return res;
    }

    public NodeSelector name(String name) {
        NodeSelector res = new NodeSelector(this, true);
        res.name = name;
        return res;
    }

    public NodeSelector axis(Axes axis, NodeSelector selector) {
        return null;
    }

    public NodeSelector base_axis(Axes axis) {
        NodeSelector res = new NodeSelector(this, true);
        res.axis = axis;
        return res;
    }

    public String getName() {
        return (name.equals("")) ? toXPath() : name;
    }

    public String toXPath() {
        String axis = this.axis.toString();
        String tag = this.tag;
        String attributes = this.attributes.stream()
                .map(ISelectorPredicate::toAttr)
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining());

        String xPath = "/" + axis + tag + attributes;
        return xPath;
    }

    public boolean equals(NodeSelector selector) {
        return this.hashCode == selector.hashCode;
    }
}
