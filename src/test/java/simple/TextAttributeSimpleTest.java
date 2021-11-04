package simple;

import org.junit.Assert;
import org.junit.Test;
import selector.SimpleSelector;

public class TextAttributeSimpleTest {

    @Test
    public void testTextAttrDotContainsEnabled() {
        SimpleSelector selector = new SimpleSelector().text("text", true, true, true);
        Assert.assertEquals("/descendant::*[contains(.,'text')]", selector.toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsEnabled() {
        SimpleSelector selector = new SimpleSelector().text("text", false, true, true);
        Assert.assertEquals("/descendant::*[contains(text(),'text')]", selector.toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsEnabled() {
        SimpleSelector selector = new SimpleSelector().text("text", true, false, true);
        Assert.assertEquals("/descendant::*[.='text']", selector.toXPath());
    }

    @Test
    public void testTextAttrDotContainsNotEnabled() {
        SimpleSelector selector = new SimpleSelector().text("text", true, true, false);
        Assert.assertEquals("/descendant::*[not(contains(.,'text'))]", selector.toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsEnabled() {
        SimpleSelector selector = new SimpleSelector().text("text", false, false, true);
        Assert.assertEquals("/descendant::*[text()='text']", selector.toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsNotEnabled() {
        SimpleSelector selector = new SimpleSelector().text("text", false, true, false);
        Assert.assertEquals("/descendant::*[not(contains(text(),'text'))]", selector.toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsNotEnabled() {
        SimpleSelector selector = new SimpleSelector().text("text", true, false, false);
        Assert.assertEquals("/descendant::*[not(.='text')]", selector.toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsNotEnabled() {
        SimpleSelector selector = new SimpleSelector().text("text", false, false, false);
        Assert.assertEquals("/descendant::*[not(text()='text')]", selector.toXPath());
    }

    @Test
    public void testTextAttrAddTwo() {
        SimpleSelector selector = new SimpleSelector()
                .text("text1", false, false, false)
                .text("text2", false, false, false);
        Assert.assertEquals("/descendant::*[not(text()='text1')][not(text()='text2')]", selector.toXPath());
    }
}
