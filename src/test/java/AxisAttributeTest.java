import org.junit.Assert;
import org.junit.Test;

public class AxisAttributeTest {

    @Test
    public void testAxisAttrEnabled() {
        BaseSelector selector1 = new BaseSelector();
        BaseSelector selector2 = new BaseSelector();
        Assert.assertEquals("/descendant::*[descendant::*]",
                selector1.axis_attribute(Axes.DESCENDANT, selector2, true).toXPath());
    }

    @Test
    public void testAxisAttrNotEnabled() {
        BaseSelector selector1 = new BaseSelector();
        BaseSelector selector2 = new BaseSelector();
        Assert.assertEquals("/descendant::*[not(ancestor::*)]",
                selector1.axis_attribute(Axes.ANCESTOR, selector2, false).toXPath());
    }

    @Test
    public void testAxisAttrTwo() {
        BaseSelector selector1 = new BaseSelector();
        BaseSelector selector2 = new BaseSelector();
        BaseSelector selector3 = new BaseSelector();
        Assert.assertEquals("/descendant::*[descendant::*][following::*]",
                selector1.isDescendant(selector2).isFollowing(selector3).toXPath());
    }

    @Test
    public void testAxisAttrNested() {
        BaseSelector selector1 = new BaseSelector();
        BaseSelector selector2 = new BaseSelector();
        BaseSelector selector3 = new BaseSelector();
        Assert.assertEquals("/descendant::*[descendant::*[following::*]]",
                selector1.isDescendant(selector2.isFollowing(selector3)).toXPath());
    }


}
