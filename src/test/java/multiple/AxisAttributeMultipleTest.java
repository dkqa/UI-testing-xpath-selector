package multiple;

import org.junit.Assert;
import org.junit.Test;
import selector.Axes;
import selector.MultipleSelector;
import selector.SimpleSelector;

public class AxisAttributeMultipleTest {

    // /descendant::one1/descendant::one2
    MultipleSelector selector1 = new MultipleSelector(new SimpleSelector().tag("one1"), new SimpleSelector().tag("one2"));
    // /descendant::two1/descendant::two2
    MultipleSelector selector2 = new MultipleSelector(new SimpleSelector().tag("two1"), new SimpleSelector().tag("two2"));
    // /descendant::three1/descendant::three2
    MultipleSelector selector3 = new MultipleSelector(new SimpleSelector().tag("three1"), new SimpleSelector().tag("three2"));

    @Test
    public void testAxisAttrEnabled() {
        Assert.assertEquals("/descendant::one1/descendant::one2[descendant::two1/descendant::two2]",
                selector1.axis_attribute(Axes.DESCENDANT, selector2, true).toXPath());
    }

    @Test
    public void testAxisAttrNotEnabled() {
        Assert.assertEquals("/descendant::one1/descendant::one2[not(ancestor::two1/descendant::two2)]",
                selector1.axis_attribute(Axes.ANCESTOR, selector2, false).toXPath());
    }

    @Test
    public void testAxisAttrTwo() {
        Assert.assertEquals("/descendant::one1/descendant::one2[descendant::two1/descendant::two2][following::three1/descendant::three2]",
                selector1.isDescendant(selector2).isFollowing(selector3).toXPath());
    }

    @Test
    public void testAxisAttrNested() {
        Assert.assertEquals("/descendant::one1/descendant::one2[descendant::two1/descendant::two2[following::three1/descendant::three2]]",
                selector1.isDescendant(selector2.isFollowing(selector3)).toXPath());
    }
}
