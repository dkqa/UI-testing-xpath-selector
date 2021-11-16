package selector.predicates;

import selector.Axes;
import selector.ISelector;

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
            res = selector.viewForAxisAttribute(axis);
        }
        return res;
    }

}
