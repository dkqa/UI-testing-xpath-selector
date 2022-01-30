package com.github.dkqa.selector;

import com.github.dkqa.selector.predicates.PositionPredicate;
import com.github.dkqa.selector.predicates.TextPredicate;
import org.junit.Assert;
import org.junit.Test;
import com.github.dkqa.selector.base.Selector;

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
        Assert.assertEquals("/descendant::*/descendant::*[10][1][12]", SEL.position(10).position(1).position(12).toXPath());
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

    @Test
    public void test() {
        Selector sel = new Selector().tag("div")
                .attribute(new PositionPredicate().position(3))
                .attribute(new TextPredicate().value("ds"))
                .attribute(new PositionPredicate().position(6)).position(33);
        System.out.println(sel);
    }

}
