package simple;

import org.junit.Assert;
import org.junit.Test;
import selector.SimpleSelector;

public class TagSimpleTest {

    @Test
    public void testTagDefault() {
        SimpleSelector selector = new SimpleSelector();
        Assert.assertEquals("/descendant::*", selector.toXPath());
    }

    @Test
    public void testTagSet() {
        SimpleSelector selector = new SimpleSelector().tag("div");
        Assert.assertEquals("/descendant::div", selector.toXPath());
    }

    @Test
    public void testTagChange() {
        SimpleSelector selector = new SimpleSelector().tag("div");
        Assert.assertEquals("/descendant::a", selector.tag("a").toXPath());
    }
}
