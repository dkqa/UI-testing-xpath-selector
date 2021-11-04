package simple;

import org.junit.Assert;
import org.junit.Test;
import selector.SimpleSelector;

public class PositionSimpleTest {

    @Test
    public void testPosPositive() {
        SimpleSelector selector = new SimpleSelector().position(12);
        Assert.assertEquals("/descendant::*[12]", selector.toXPath());
    }

    @Test
    public void testPosNegative() {
        SimpleSelector selector = new SimpleSelector().position(-12);
        Assert.assertEquals("/descendant::*", selector.toXPath());
    }

    @Test
    public void testPosOne() {
        SimpleSelector selector = new SimpleSelector().position(1);
        Assert.assertEquals("/descendant::*[1]", selector.toXPath());
    }

    @Test
    public void testPosZero() {
        SimpleSelector selector = new SimpleSelector().position(0);
        Assert.assertEquals("/descendant::*", selector.toXPath());
    }

    @Test
    public void testPosAddSeveral() {
        SimpleSelector selector = new SimpleSelector().position(0).position(1).position(12);
        Assert.assertEquals("/descendant::*[12]", selector.toXPath());
    }

}
