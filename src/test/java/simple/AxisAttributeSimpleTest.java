package simple;

import org.junit.Assert;
import org.junit.Test;
import selector.Axes;
import selector.SimpleSelector;

public class AxisAttributeSimpleTest {

    @Test
    public void testAxisAttrEnabled() {
        SimpleSelector selector1 = new SimpleSelector();
        SimpleSelector selector2 = new SimpleSelector();
        Assert.assertEquals("/descendant::*[descendant::*]",
                selector1.axis_attribute(Axes.DESCENDANT, selector2, true).toXPath());
    }

    @Test
    public void testAxisAttrNotEnabled() {
        SimpleSelector selector1 = new SimpleSelector();
        SimpleSelector selector2 = new SimpleSelector();
        Assert.assertEquals("/descendant::*[not(ancestor::*)]",
                selector1.axis_attribute(Axes.ANCESTOR, selector2, false).toXPath());
    }

    @Test
    public void testAxisAttrTwo() {
        SimpleSelector selector1 = new SimpleSelector();
        SimpleSelector selector2 = new SimpleSelector();
        SimpleSelector selector3 = new SimpleSelector();
        Assert.assertEquals("/descendant::*[descendant::*][following::*]",
                selector1.isDescendant(selector2).isFollowing(selector3).toXPath());
    }

    @Test
    public void testAxisAttrNested() {
        SimpleSelector selector1 = new SimpleSelector();
        SimpleSelector selector2 = new SimpleSelector();
        SimpleSelector selector3 = new SimpleSelector();
        Assert.assertEquals("/descendant::*[descendant::*[following::*]]",
                selector1.isDescendant(selector2.isFollowing(selector3)).toXPath());
    }


}
