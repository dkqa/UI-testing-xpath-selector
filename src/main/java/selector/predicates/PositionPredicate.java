package selector.predicates;

public class PositionPredicate extends SelectorPredicate<PositionPredicate> {

    protected int position = 0;
    protected Operator operator;
    protected boolean last = false;
    protected int endPosition = 0;

    public PositionPredicate position(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.EQUAL;
        return this;
    }

    public PositionPredicate positionMore(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.MORE;
        return this;
    }

    public PositionPredicate positionLess(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.LESS;
        return this;
    }

    public PositionPredicate positionMoreOrEqual(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.MORE_OR_EQUAL;
        return this;
    }

    public PositionPredicate positionLessOrEqual(int pos) {
        position = Math.max(pos, 0);
        operator = Operator.LESS_OR_EQUAL;
        return this;
    }

    public PositionPredicate last() {
        last = true;
        return this;
    }

    public PositionPredicate fromEnd(int pos) {
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

    @Override
    public String predicateName() {
        return "Position";
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
