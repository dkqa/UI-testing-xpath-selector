import org.junit.Assert;
import org.junit.Test;

public class TagTest {

    @Test
    public void testTagDefault() {
        BaseSelector selector = new BaseSelector();
        Assert.assertEquals("/descendant::*", selector.toXPath());
    }

    @Test
    public void testTagSet() {
        BaseSelector selector = new BaseSelector().tag("div");
        Assert.assertEquals("/descendant::div", selector.toXPath());
    }

    @Test
    public void testTagChange() {
        BaseSelector selector = new BaseSelector().tag("div");
        Assert.assertEquals("/descendant::a", selector.tag("a").toXPath());
    }
}
