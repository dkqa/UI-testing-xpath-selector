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

import com.github.dkqa.selector.predicates.AttrPredicate;

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
