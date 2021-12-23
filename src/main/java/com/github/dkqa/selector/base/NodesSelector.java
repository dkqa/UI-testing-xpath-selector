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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class NodesSelector implements ISelector<NodesSelector> {

    protected List<NodeSelector> nodes;

    NodesSelector() {
        this.nodes = new ArrayList<>();
        this.nodes.add(new NodeSelector());
    }

    NodesSelector(NodesSelector nodesSelector) {
        this.nodes = nodesSelector.nodes.stream()
                .map(s -> new NodeSelector(s, true))
                .collect(Collectors.toList());
    }

    private void replaceLast(NodeSelector selector) {
        this.nodes.remove(this.nodes.size() - 1);
        this.nodes.add(selector);
    }

    private NodeSelector last() {
        return this.nodes.get(this.nodes.size() - 1);
    }

    public NodesSelector tag(String tag) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().tag(tag));
        return res;
    }

    public NodesSelector attribute(ISelectorPredicate predicate) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().attribute(predicate));
        return res;
    }

    public NodesSelector replaceAttribute(ISelectorPredicate predicate) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().replaceAttribute(predicate));
        return res;
    }

    public NodesSelector name(String name) {
        NodesSelector res = new NodesSelector(this);
        res.replaceLast(res.last().name(name));
        return res;
    }

    public NodesSelector nameHard(String name) {
        NodesSelector res = new NodesSelector(this);
        res.nodes.forEach(s -> s.name = "");
        res.last().name = name;
        return res;
    }

    public NodesSelector axis(Axes axis, NodesSelector selector) {
        NodesSelector var1 = new NodesSelector(this);
        NodesSelector var2 = new NodesSelector(selector);

        boolean wasFined = false;

        for (int i = 0; i < var1.nodes.size(); i++) {
            if (var1.nodes.get(i).equals(var2.nodes.get(0))) {
                var2.nodes.remove(0);
                wasFined = true;
            } else if (wasFined) {
                break;
            }
        }
        if (var2.nodes.size() > 0) {
            var1.nodes.addAll(var2.base_axis(axis).nodes);
        }
        return var1;
    }

    public NodesSelector base_axis(Axes axis) {
        NodesSelector res = new NodesSelector(this);
        res.nodes.set(0, this.nodes.get(0).base_axis(axis));
        return res;
    }

    public String getName() {
        String name = nodes.stream()
                .map(s -> s.name)
                .filter(n -> !n.equals(""))
                .collect(Collectors.joining(" - "));
        return (name.equals("")) ? toXPath() : name;
    }

    public String toXPath() {
        return nodes.stream().map(ISelector::toXPath).collect(Collectors.joining());
    }
}
