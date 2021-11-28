package com.dkqa.selector;

import org.junit.Assert;
import org.junit.Test;
import com.dkqa.selector.base.Selector;
import com.dkqa.selector.predicates.AttrPredicate;

public class AttributeSelectorTest {


    @Test
    public void testAttrAddOne() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[@class='value']",
                selector.attribute(new AttrPredicate().name("class").value("value")).toXPath());
    }

    @Test
    public void testAttrEnabledContainsValue() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[contains(@class,'value')]",
                selector.attribute(new AttrPredicate().name("class").value("value").contains()).toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsValue() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[not(contains(@class,'value'))]",
                selector.attribute(new AttrPredicate().name("class").value("value").contains().not()).toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsValue() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[@class='value']",
                selector.attribute(new AttrPredicate().name("class").value("value")).toXPath());
    }

    @Test
    public void testAttrEnabledContainsNoValue() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[contains(@class)]",
                selector.attribute(new AttrPredicate().name("class").contains()).toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsNoValue() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[not(contains(@class))]",
                selector.attribute(new AttrPredicate().name("class").contains().not()).toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsNoValue() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[@class]",
                selector.attribute(new AttrPredicate().name("class")).toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsValue() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[not(@class='value')]",
                selector.attribute(new AttrPredicate().name("class").value("value").not()).toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsNoValue() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[not(@class)]",
                selector.attribute(new AttrPredicate().name("class").not()).toXPath());
    }

    @Test
    public void testAttrAddTwo() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[@class='value'][@class2='value2']",
                selector.attribute(new AttrPredicate().name("class").value("value"))
                        .attribute(new AttrPredicate().name("class2").value("value2")).toXPath());
    }

    @Test
    public void testAttrAddThree() {
        Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));
        Assert.assertEquals("/descendant::div1/descendant::div2[@class1='value1'][@class2='value2'][@class3='value3']",
                selector.attribute(new AttrPredicate().name("class1").value("value1"))
                        .attribute(new AttrPredicate().name("class2").value("value2"))
                        .attribute(new AttrPredicate().name("class3").value("value3")).toXPath());
    }

    @Test
    public void testComposeAddAttr() {
        Selector selectorCompose = new Selector(
                new Selector().descendant(new Selector()),
                new Selector().descendant(new Selector()),
                new Selector().descendant(new Selector())
        );
        Assert.assertEquals("/descendant::*/descendant::*[@*='value']" +
                        " | /descendant::*/descendant::*[@*='value']" +
                        " | /descendant::*/descendant::*[@*='value']",
                selectorCompose.attribute(new AttrPredicate().value("value")).toXPath());
    }

}
