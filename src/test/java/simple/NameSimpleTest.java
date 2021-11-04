package simple;

import org.junit.Assert;
import org.junit.Test;
import selector.SimpleSelector;

public class NameSimpleTest {

    @Test
    public void testNameDefault() {
        SimpleSelector selector = new SimpleSelector();
        Assert.assertEquals("/descendant::*", selector.getName());
    }

    @Test
    public void testNameSet() {
        SimpleSelector selector = new SimpleSelector().name("name");
        Assert.assertEquals("name", selector.getName());
    }
}
