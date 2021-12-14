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

public class PositionPredicate extends SelectorPredicate<PositionPredicate> {

    protected int position = 0;
    protected Operator operator;
    protected boolean last = false;
    protected int endPosition = 0;

    public PositionPredicate position(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.EQUAL;
        last = false;
        return this;
    }

    public PositionPredicate positionMore(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.MORE;
        last = false;
        return this;
    }

    public PositionPredicate positionLess(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.LESS;
        last = false;
        return this;
    }

    public PositionPredicate positionMoreOrEqual(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.MORE_OR_EQUAL;
        last = false;
        return this;
    }

    public PositionPredicate positionLessOrEqual(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.LESS_OR_EQUAL;
        last = false;
        return this;
    }

    public PositionPredicate last() {
        endPosition = 0;
        last = true;
        return this;
    }

    public PositionPredicate last(int pos) {
        endPosition = Math.max(pos, 0);
        last = true;
        return this;
    }

    protected String getBody() {
        String res = "";
        if (!last) {
            if (position > 0) {
                if (enabled && operator == Operator.EQUAL) {
                    res = String.valueOf(position);
                } else {
                    res = "position()" + operator.value() + position;
                }
            }
        } else {
            res = (endPosition > 0) ? "position()=last()-" + endPosition : "position()=last()";
        }
        return res;
    }

    private enum Operator {

        EQUAL("="),
        MORE(">"),
        LESS("<"),
        MORE_OR_EQUAL(">="),
        LESS_OR_EQUAL("<=");

        private String value;
        Operator(String value) {
            this.value = value;
        }
        public String value() {
            return value;
        }
    }
}
