package selector;

import selector.predicates.AttrPredicate;

public class SelectorPredicateFactory {

    public static AttrPredicate attr() {
        return new AttrPredicate();
    }

    public static AttrPredicate attrAny(String value) {
        return new AttrPredicate().name("*").value(value);
    }

    public static AttrPredicate attrClass(String value) {
        return new AttrPredicate().name("class").value(value);
    }

    public static AttrPredicate attrText(String value) {
        return new AttrPredicate().name("text").value(value);
    }

    public static AttrPredicate attrDot(String value) {
        return new AttrPredicate().name(".").value(value);
    }

}
