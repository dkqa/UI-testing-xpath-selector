package multiple;

import org.junit.Assert;
import org.junit.Test;
import selector.MultipleSelector;
import selector.SimpleSelector;

public class PositionMultipleTest {
    
    MultipleSelector selector = new MultipleSelector(new SimpleSelector(), new SimpleSelector());

    @Test
    public void testPosPositive() {
        Assert.assertEquals("/descendant::*/descendant::*[12]", selector.position(12).toXPath());
    }

    @Test
    public void testPosNegative() {
        Assert.assertEquals("/descendant::*/descendant::*", selector.position(-12).toXPath());
    }

    @Test
    public void testPosOne() {
        Assert.assertEquals("/descendant::*/descendant::*[1]", selector.position(1).toXPath());
    }

    @Test
    public void testPosZero() {
        Assert.assertEquals("/descendant::*/descendant::*", selector.position(0).toXPath());
    }

    @Test
    public void testPosAddSeveral() {
        Assert.assertEquals("/descendant::*/descendant::*[12]", selector.position(0).position(1).position(12).toXPath());
    }
}
