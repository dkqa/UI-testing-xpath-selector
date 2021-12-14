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

package com.github.dkqa.selector.predicates;

import com.github.dkqa.selector.Axes;
import com.github.dkqa.selector.base.ISelector;

public class AxisPredicate extends SelectorPredicate<AxisPredicate> {

    protected ISelector selector;
    protected Axes axis;

    public AxisPredicate selector(Axes axis, ISelector selector) {
        this.selector = selector;
        this.axis = axis;
        return this;
    }

    protected String getBody() {
        String res = "";
        if (selector != null) {
            res = selector.base_axis(axis).toXPath()
                    .replaceAll("^/", "")
                    .replaceAll(" /", " ");
        }
        return res;
    }

}
