package selector;

import org.junit.Assert;
import org.junit.Test;

public class TagSelectorTest {

    @Test
    public void testTagDefault() {
        Selector selector = new Selector();
        Assert.assertEquals("/descendant::*", selector.toXPath());
    }

    @Test
    public void testTagSet() {
        Selector selector = new Selector().tag("div");
        Assert.assertEquals("/descendant::div", selector.toXPath());
    }

    @Test
    public void testTagChange() {
        Selector selector = new Selector().tag("div");
        Assert.assertEquals("/descendant::div1", selector.tag("div1").toXPath());
    }

}
