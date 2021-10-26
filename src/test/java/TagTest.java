import org.junit.Assert;
import org.junit.Test;

public class TagTest {

    @Test
    public void testTagDefault() {
        BaseSelector selector = new BaseSelector();
        Assert.assertEquals("//*", selector.toXPath());
    }

    @Test
    public void testTagSet() {
        BaseSelector selector = new BaseSelector().tag("div");
        Assert.assertEquals("//div", selector.toXPath());
    }

    @Test
    public void testTagChange() {
        BaseSelector selector = new BaseSelector().tag("div");
        Assert.assertEquals("//a", selector.tag("a").toXPath());
    }
}
