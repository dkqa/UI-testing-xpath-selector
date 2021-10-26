import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class AttributeTest {
    @Test
    public void testAttrEnabledContainsValue() {
        BaseSelector selector = new BaseSelector().attribute("class", "value", true, true);
        Assert.assertEquals("//*[contains(@class,'value')]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsValue() {
        BaseSelector selector = new BaseSelector().attribute("class", "value", true, false);
        Assert.assertEquals("//*[not(contains(@class,'value'))]", selector.toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsValue() {
        BaseSelector selector = new BaseSelector().attribute("class", "value", false, true);
        Assert.assertEquals("//*[@class='value']", selector.toXPath());
    }

    @Test
    public void testAttrEnabledContainsNoValue() {
        BaseSelector selector = new BaseSelector().attribute("class", null, true, true);
        Assert.assertEquals("//*[contains(@class)]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsNoValue() {
        BaseSelector selector = new BaseSelector().attribute("class", null, true, false);
        Assert.assertEquals("//*[not(contains(@class))]", selector.toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsNoValue() {
        BaseSelector selector = new BaseSelector().attribute("class", null, false, true);
        Assert.assertEquals("//*[@class]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsValue() {
        BaseSelector selector = new BaseSelector().attribute("class", "value", false, false);
        Assert.assertEquals("//*[not(@class='value')]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsNoValue() {
        BaseSelector selector = new BaseSelector().attribute("class", null, false, false);
        Assert.assertEquals("//*[not(@class)]", selector.toXPath());
    }

    @Test
    public void testAttrAddTwo() {
        BaseSelector selector = new BaseSelector()
                .attribute("class1", "value1", false, true)
                .attribute("class2", "value2", false, true);
        Assert.assertEquals("//*[@class1='value1'][@class2='value2']", selector.toXPath());
    }

    @Test
    public void testAttrAddThree() {
        BaseSelector selector = new BaseSelector()
                .attribute("class1", "value1", false, true)
                .attribute("class2", "value2", false, true)
                .attribute("class3", "value3", false, true);
        Assert.assertEquals("//*[@class1='value1'][@class2='value2'][@class3='value3']", selector.toXPath());
    }

    @Test
    public void testAttrAddRandomCount() {
        int random = new Random().nextInt(10);
        StringBuilder expRes = new StringBuilder("//*");
        BaseSelector selector = new BaseSelector();
        for (int i = 0; i < random; i++) {
            expRes.append("[@class='value']");
            selector = selector.attribute("class", "value", false, true);
        }
        Assert.assertEquals(expRes.toString(), selector.toXPath());
    }
}
