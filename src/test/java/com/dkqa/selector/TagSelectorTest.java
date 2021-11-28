package com.dkqa.selector;

import org.junit.Assert;
import org.junit.Test;
import com.dkqa.selector.base.Selector;

public class TagSelectorTest {

    @Test
    public void testTagDefault() {
        Selector selector = new Selector();
        Assert.assertEquals("/descendant::*", selector.toXPath());
    }

    @Test
    public void testTagSet() {
        Selector selector = new Selector().tag("div");
        Assert.assertEquals("/descendant::div", selector.toXPath());
    }

    @Test
    public void testTagChange() {
        Selector selector = new Selector().tag("div");
        Assert.assertEquals("/descendant::div1", selector.tag("div1").toXPath());
    }

    @Test
    public void testSelectorComposeTagChange() {
        Selector selector1 = new Selector().tag("s1");
        Selector selector2 = new Selector().tag("s2");
        Selector selector = new Selector(selector1, selector2);
        Assert.assertEquals("/descendant::s | /descendant::s", selector.tag("s").toXPath());
    }

    @Test
    public void testSelectorComposeAxesTagChange() {
        Selector selector1 = new Selector().tag("s1");
        Selector selector2 = new Selector().tag("s2");
        Selector selector3 = new Selector().tag("s3");
        Selector selector4 = new Selector().tag("s4");
        Selector selector5 = selector1.descendant(selector2);
        Selector selector6 = selector3.descendant(selector4);
        Selector selector = new Selector(selector5, selector6);
        Assert.assertEquals("/descendant::s1/descendant::s | /descendant::s3/descendant::s", selector.tag("s").toXPath());
    }

}
