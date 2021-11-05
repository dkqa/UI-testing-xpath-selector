package selector;

import org.junit.Assert;
import org.junit.Test;

public class TagSelectorTest {

    @Test
    public void testTagSet() {
        Selector selector = new Selector().tag("div");
        Assert.assertEquals("/descendant::div", selector.toXPath());
    }

}
