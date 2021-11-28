package com.dkqa.selector;

import com.dkqa.selector.predicates.AttrPredicate;

public class SelectorPredicateFactory {

    public static AttrPredicate attr() {
        return new AttrPredicate();
    }

    public static AttrPredicate any(String value) {
        return new AttrPredicate().value(value);
    }

    public static AttrPredicate clazz(String value) {
        return new AttrPredicate().name("class").value(value);
    }

    public static AttrPredicate id(String value) {
        return new AttrPredicate().name("id").value(value);
    }

    public static AttrPredicate text(String value) {
        return new AttrPredicate().name("text").value(value);
    }

}
