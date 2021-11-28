package com.dkqa.selector.base;

import com.dkqa.selector.Axes;
import com.dkqa.selector.predicates.ISelectorPredicate;
import com.dkqa.selector.predicates.PositionPredicate;

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
                .filter(s -> !(s instanceof PositionPredicate))
                .map(ISelectorPredicate::toAttr)
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining());

        boolean posPredicate = this.attributes.stream().anyMatch(a -> a instanceof PositionPredicate);
        String position = (posPredicate) ? this.attributes.stream()
                .filter(a -> a instanceof PositionPredicate)
                .findFirst()
                .get()
                .toAttr() : "";

        String xPath = "/" + axis + tag + attributes + position;
        return xPath;
    }

    public boolean equals(NodeSelector selector) {
        return this.hashCode == selector.hashCode;
    }
}
