package com.dkqa.selector;

import com.dkqa.selector.predicates.DotPredicate;
import com.dkqa.selector.predicates.TextPredicate;
import org.junit.Assert;
import org.junit.Test;
import com.dkqa.selector.base.Selector;

public class TextAttributeSelectorTest {

    @Test
    public void testTextAttrDotContainsEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[contains(.,'text')]",
                selector.attribute(new DotPredicate().value("text").contains()).toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[contains(text(),'text')]",
                selector.attribute(new TextPredicate().value("text").contains()).toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[.='text']",
                selector.attribute(new DotPredicate().value("text")).toXPath());
    }

    @Test
    public void testTextAttrDotContainsNotEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(contains(.,'text'))]",
                selector.attribute(new DotPredicate().value("text").contains().not()).toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[text()='text']",
                selector.attribute(new TextPredicate().value("text")).toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsNotEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(contains(text(),'text'))]",
                selector.attribute(new TextPredicate().value("text").contains().not()).toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsNotEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(.='text')]",
                selector.attribute(new DotPredicate().value("text").not()).toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsNotEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(text()='text')]",
                selector.attribute(new TextPredicate().value("text").not()).toXPath());
    }

    @Test
    public void testTextAttrAddTwo() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(text()='text1')][not(text()='text2')]",
                selector.attribute(new TextPredicate().value("text1").not())
                        .attribute(new TextPredicate().value("text2").not()).toXPath());
    }

    @Test
    public void testComposeAddTextAttr() {
        Selector selectorCompose = new Selector(
                new Selector().descendant(new Selector()),
                new Selector().descendant(new Selector()),
                new Selector().descendant(new Selector())
        );
        Assert.assertEquals("/descendant::*/descendant::*[contains(.,'text')]" +
                        " | /descendant::*/descendant::*[contains(.,'text')]" +
                        " | /descendant::*/descendant::*[contains(.,'text')]",
                selectorCompose.attribute(new DotPredicate().value("text").contains()).toXPath());
    }

}
