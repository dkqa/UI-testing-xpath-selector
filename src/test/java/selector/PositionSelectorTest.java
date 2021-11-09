package selector;

import org.junit.Assert;
import org.junit.Test;

public class PositionSelectorTest {

    @Test
    public void testPosPositive() {
        Selector SEL = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[12]", SEL.position(12).toXPath());
    }

    @Test
    public void testPosNegative() {
        Selector SEL = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*", SEL.position(-12).toXPath());
    }

    @Test
    public void testPosOne() {
        Selector SEL = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[1]", SEL.position(1).toXPath());
    }

    @Test
    public void testPosZero() {
        Selector SEL = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*", SEL.position(0).toXPath());
    }

    @Test
    public void testPosAddSeveral() {
        Selector SEL = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[12]", SEL.position(0).position(1).position(12).toXPath());
    }

    @Test
    public void testComposePosAdd() {
        Selector SEL_1 = new Selector().tag("S1");
        Selector SEL_2 = new Selector().tag("S2");
        Selector SEL_3 = new Selector().tag("S3");
        Selector SEL_4 = new Selector(SEL_1, SEL_2, SEL_3);
        Assert.assertEquals("/descendant::S1[12] | /descendant::S2[12] | /descendant::S3[12]",
                SEL_4.position(12).toXPath());
    }

}
