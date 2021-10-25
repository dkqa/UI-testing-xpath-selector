import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AttributeTest {
    @Test
    public void testAttrEnabledContainsValue() {
        BaseSelector selector = new BaseSelector().attribute(true, "class", "value", true);
        Assertions.assertEquals("//*[contains(@class,'value')]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsValue() {
        BaseSelector selector = new BaseSelector().attribute(false, "class", "value", true);
        Assertions.assertEquals("//*[not(contains(@class,'value'))]", selector.toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsValue() {
        BaseSelector selector = new BaseSelector().attribute(true, "class", "value", false);
        Assertions.assertEquals("//*[@class='value']", selector.toXPath());
    }

    @Test
    public void testAttrEnabledContainsNoValue() {
        BaseSelector selector = new BaseSelector().attribute(true, "class", null, true);
        Assertions.assertEquals("//*[contains(@class)]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledContainsNoValue() {
        BaseSelector selector = new BaseSelector().attribute(false, "class", null, true);
        Assertions.assertEquals("//*[not(contains(@class))]", selector.toXPath());
    }

    @Test
    public void testAttrEnabledNotContainsNoValue() {
        BaseSelector selector = new BaseSelector().attribute(true, "class", null, false);
        Assertions.assertEquals("//*[@class]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsValue() {
        BaseSelector selector = new BaseSelector().attribute(false, "class", "value", false);
        Assertions.assertEquals("//*[not(@class='value')]", selector.toXPath());
    }

    @Test
    public void testAttrNotEnabledNotContainsNoValue() {
        BaseSelector selector = new BaseSelector().attribute(false, "class", null, false);
        Assertions.assertEquals("//*[not(@class)]", selector.toXPath());
    }

    @Test
    public void testAttrAddTwo() {
        BaseSelector selector = new BaseSelector()
                .attribute(true, "class1", "value1", false)
                .attribute(true, "class2", "value2", false);
        Assertions.assertEquals("//*[@class1='value1'][@class2='value2']", selector.toXPath());
    }

    @Test
    public void testAttrAddThree() {
        BaseSelector selector = new BaseSelector()
                .attribute(true, "class1", "value1", false)
                .attribute(true, "class2", "value2", false)
                .attribute(true, "class3", "value3", false);
        Assertions.assertEquals("//*[@class1='value1'][@class2='value2'][@class3='value3']", selector.toXPath());
    }

    @Test
    public void testAttrAddRandomCount() {
        int random = new Random().nextInt(10);
        StringBuilder expRes = new StringBuilder("//*");
        BaseSelector selector = new BaseSelector();
        for (int i = 0; i < random; i++) {
            expRes.append("[@class='value']");
            selector = selector.attribute(true, "class", "value", false);
        }
        Assertions.assertEquals(expRes.toString(), selector.toXPath());
    }
}
