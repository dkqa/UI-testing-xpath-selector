import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

    @Test
    public void testPosPositive() {
        BaseSelector selector = new BaseSelector().position(12);
        Assert.assertEquals("//*[12]", selector.toXPath());
    }

    @Test
    public void testPosNegative() {
        BaseSelector selector = new BaseSelector().position(-12);
        Assert.assertEquals("//*", selector.toXPath());
    }

    @Test
    public void testPosOne() {
        BaseSelector selector = new BaseSelector().position(1);
        Assert.assertEquals("//*[1]", selector.toXPath());
    }

    @Test
    public void testPosZero() {
        BaseSelector selector = new BaseSelector().position(0);
        Assert.assertEquals("//*", selector.toXPath());
    }

    @Test
    public void testPosAddSeveral() {
        BaseSelector selector = new BaseSelector().position(0).position(1).position(12);
        Assert.assertEquals("//*[12]", selector.toXPath());
    }

}
