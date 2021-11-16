package selector;

import selector.predicates.AttrPredicate;

import static selector.predicates.SelectorPredicateFactory.attrAny;

public class SelectorFactory {

    public static Selector selector() {
        return new Selector();
    }

    public static Selector compose(Selector... selectors) {
        return new Selector(selectors);
    }

    public static Selector tag(String tag) {
        return new Selector().tag(tag);
    }

    public static Selector text(String text) {
        return new Selector().textAttribute(text, false, false, true);
    }

    public static Selector div(String attrValue) {
        return new Selector().tag("div").attribute(new AttrPredicate().name("*").value(attrValue));
    }

    public static Selector div_contains(String attrValue) {
        return new Selector().tag("div").attribute(attrAny(attrValue).contains());
    }

    public static Selector table(String attrValue) {
        return new Selector().tag("table").attribute(new AttrPredicate().name("*").value(attrValue));
    }

    public static Selector thead() {
        return new Selector().tag("thead");
    }

    public static Selector tr() {
        return new Selector().tag("tr");
    }

    public static Selector th() {
        return new Selector().tag("th");
    }

    public static Selector tbody() {
        return new Selector().tag("tbody");
    }

    public static Selector td() {
        return new Selector().tag("td");
    }
}
