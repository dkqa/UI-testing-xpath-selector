package selector;

import org.junit.Assert;
import org.junit.Test;

public class TextAttributeSelectorTest {

    Selector selector = new Selector().descendant(new Selector());

    @Test
    public void testTextAttrDotContainsEnabled() {
        Assert.assertEquals("/descendant::*/descendant::*[contains(.,'text')]",
                selector.text("text", true, true, true).toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsEnabled() {
        Assert.assertEquals("/descendant::*/descendant::*[contains(text(),'text')]",
                selector.text("text", false, true, true).toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsEnabled() {
        Assert.assertEquals("/descendant::*/descendant::*[.='text']",
                selector.text("text", true, false, true).toXPath());
    }

    @Test
    public void testTextAttrDotContainsNotEnabled() {
        Assert.assertEquals("/descendant::*/descendant::*[not(contains(.,'text'))]",
                selector.text("text", true, true, false).toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsEnabled() {
        Assert.assertEquals("/descendant::*/descendant::*[text()='text']",
                selector.text("text", false, false, true).toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsNotEnabled() {
        Assert.assertEquals("/descendant::*/descendant::*[not(contains(text(),'text'))]",
                selector.text("text", false, true, false).toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsNotEnabled() {
        Assert.assertEquals("/descendant::*/descendant::*[not(.='text')]",
                selector.text("text", true, false, false).toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsNotEnabled() {
        Assert.assertEquals("/descendant::*/descendant::*[not(text()='text')]",
                selector.text("text", false, false, false).toXPath());
    }

    @Test
    public void testTextAttrAddTwo() {
        Assert.assertEquals("/descendant::*/descendant::*[not(text()='text1')][not(text()='text2')]",
                selector.text("text1", false, false, false)
                        .text("text2", false, false, false).toXPath());
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
                selectorCompose.text("text", true, true, true).toXPath());
    }

}
