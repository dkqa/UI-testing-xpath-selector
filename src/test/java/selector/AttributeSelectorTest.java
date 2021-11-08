package selector;

import org.junit.Assert;
import org.junit.Test;

public class AttributeSelectorTest {

    Selector selector = new Selector().tag("div1").descendant(new Selector().tag("div2"));

    @Test
    public void testAttrAddOne() {
        Assert.assertEquals("/descendant::div1/descendant::div2[@class='value']",
                selector.attribute("class", "value", false, true).toXPath());
    }

    @Test
    public void testAttrEnabledContainsValue() {
        Assert.assertEquals("/descendant::div1/descendant::div2[contains(@class,'value')]",
                selector.attribute("class", "value", true, true).toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsValue() {
        Assert.assertEquals("/descendant::div1/descendant::div2[not(contains(@class,'value'))]",
                selector.attribute("class", "value", true, false).toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsValue() {
        Assert.assertEquals("/descendant::div1/descendant::div2[@class='value']",
                selector.attribute("class", "value", false, true).toXPath());
    }

    @Test
    public void testAttrEnabledContainsNoValue() {
        Assert.assertEquals("/descendant::div1/descendant::div2[contains(@class)]",
                selector.attribute("class", null, true, true).toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsNoValue() {
        Assert.assertEquals("/descendant::div1/descendant::div2[not(contains(@class))]",
                selector.attribute("class", null, true, false).toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsNoValue() {
        Assert.assertEquals("/descendant::div1/descendant::div2[@class]",
                selector.attribute("class", null, false, true).toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsValue() {
        Assert.assertEquals("/descendant::div1/descendant::div2[not(@class='value')]",
                selector.attribute("class", "value", false, false).toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsNoValue() {
        Assert.assertEquals("/descendant::div1/descendant::div2[not(@class)]",
                selector.attribute("class", null, false, false).toXPath());
    }

    @Test
    public void testAttrAddTwo() {
        Assert.assertEquals("/descendant::div1/descendant::div2[@class='value'][@class2='value2']",
                selector.attribute("class", "value", false, true)
                        .attribute("class2", "value2", false, true).toXPath());
    }

    @Test
    public void testAttrAddThree() {
        Assert.assertEquals("/descendant::div1/descendant::div2[@class1='value1'][@class2='value2'][@class3='value3']",
                selector.attribute("class1", "value1", false, true)
                        .attribute("class2", "value2", false, true)
                        .attribute("class3", "value3", false, true).toXPath());
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
                selectorCompose.attribute("*", "value", false, true).toXPath());
    }

}
