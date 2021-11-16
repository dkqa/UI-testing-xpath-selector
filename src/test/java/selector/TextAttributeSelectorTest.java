package selector;

import org.junit.Assert;
import org.junit.Test;

public class TextAttributeSelectorTest {

    @Test
    public void testTextAttrDotContainsEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[contains(.,'text')]",
                selector.textAttribute("text", true, true, true).toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[contains(text(),'text')]",
                selector.textAttribute("text", false, true, true).toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[.='text']",
                selector.textAttribute("text", true, false, true).toXPath());
    }

    @Test
    public void testTextAttrDotContainsNotEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(contains(.,'text'))]",
                selector.textAttribute("text", true, true, false).toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[text()='text']",
                selector.textAttribute("text", false, false, true).toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsNotEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(contains(text(),'text'))]",
                selector.textAttribute("text", false, true, false).toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsNotEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(.='text')]",
                selector.textAttribute("text", true, false, false).toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsNotEnabled() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(text()='text')]",
                selector.textAttribute("text", false, false, false).toXPath());
    }

    @Test
    public void testTextAttrAddTwo() {
        Selector selector = new Selector().descendant(new Selector());
        Assert.assertEquals("/descendant::*/descendant::*[not(text()='text1')][not(text()='text2')]",
                selector.textAttribute("text1", false, false, false)
                        .textAttribute("text2", false, false, false).toXPath());
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
                selectorCompose.textAttribute("text", true, true, true).toXPath());
    }

}
