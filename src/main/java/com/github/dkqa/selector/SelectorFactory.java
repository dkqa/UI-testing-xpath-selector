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

package com.github.dkqa.selector;

import com.github.dkqa.selector.base.Selector;
import com.github.dkqa.selector.predicates.AttrPredicate;

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

    public static Selector textSelector(String text) {
        return new Selector().attribute(new AttrPredicate().value("text").value(text));
    }

    public static Selector div(String attrValue) {
        return new Selector().tag("div").attribute(new AttrPredicate().value(attrValue));
    }

    public static Selector div_contains(String attrValue) {
        return new Selector().tag("div").attribute(new AttrPredicate().value(attrValue).contains());
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
