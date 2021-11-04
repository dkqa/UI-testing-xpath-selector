package multiple;

import org.junit.Assert;
import org.junit.Test;
import selector.MultipleSelector;
import selector.SimpleSelector;

import java.util.Arrays;

public class TagMultipleTest {

    MultipleSelector selector = new MultipleSelector(Arrays.asList(
            new SimpleSelector().tag("div1"),
            new SimpleSelector().tag("div2"),
            new SimpleSelector().tag("div3")));

    @Test
    public void testTagChange() {
        Assert.assertEquals("/descendant::div1/descendant::div2/descendant::newTag", selector.tag("newTag").toXPath());
    }
}
