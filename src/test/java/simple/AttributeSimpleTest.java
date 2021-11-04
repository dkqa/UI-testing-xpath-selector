package simple;

import org.junit.Assert;
import org.junit.Test;
import selector.SimpleSelector;

import java.util.Random;

public class AttributeSimpleTest {
    @Test
    public void testAttrEnabledContainsValue() {
        SimpleSelector selector = new SimpleSelector().attribute("class", "value", true, true);
        Assert.assertEquals("/descendant::*[contains(@class,'value')]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsValue() {
        SimpleSelector selector = new SimpleSelector().attribute("class", "value", true, false);
        Assert.assertEquals("/descendant::*[not(contains(@class,'value'))]", selector.toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsValue() {
        SimpleSelector selector = new SimpleSelector().attribute("class", "value", false, true);
        Assert.assertEquals("/descendant::*[@class='value']", selector.toXPath());
    }

    @Test
    public void testAttrEnabledContainsNoValue() {
        SimpleSelector selector = new SimpleSelector().attribute("class", null, true, true);
        Assert.assertEquals("/descendant::*[contains(@class)]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsNoValue() {
        SimpleSelector selector = new SimpleSelector().attribute("class", null, true, false);
        Assert.assertEquals("/descendant::*[not(contains(@class))]", selector.toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsNoValue() {
        SimpleSelector selector = new SimpleSelector().attribute("class", null, false, true);
        Assert.assertEquals("/descendant::*[@class]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsValue() {
        SimpleSelector selector = new SimpleSelector().attribute("class", "value", false, false);
        Assert.assertEquals("/descendant::*[not(@class='value')]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsNoValue() {
        SimpleSelector selector = new SimpleSelector().attribute("class", null, false, false);
        Assert.assertEquals("/descendant::*[not(@class)]", selector.toXPath());
    }

    @Test
    public void testAttrAddTwo() {
        SimpleSelector selector = new SimpleSelector()
                .attribute("class1", "value1", false, true)
                .attribute("class2", "value2", false, true);
        Assert.assertEquals("/descendant::*[@class1='value1'][@class2='value2']", selector.toXPath());
    }

    @Test
    public void testAttrAddThree() {
        SimpleSelector selector = new SimpleSelector()
                .attribute("class1", "value1", false, true)
                .attribute("class2", "value2", false, true)
                .attribute("class3", "value3", false, true);
        Assert.assertEquals("/descendant::*[@class1='value1'][@class2='value2'][@class3='value3']", selector.toXPath());
    }

    @Test
    public void testAttrAddRandomCount() {
        int random = new Random().nextInt(10);
        StringBuilder expRes = new StringBuilder("/descendant::*");
        SimpleSelector selector = new SimpleSelector();
        for (int i = 0; i < random; i++) {
            expRes.append("[@class='value']");
            selector = selector.attribute("class", "value", false, true);
        }
        Assert.assertEquals(expRes.toString(), selector.toXPath());
    }
}
