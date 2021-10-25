import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void testTagDefault() {
        BaseSelector selector = new BaseSelector();
        Assertions.assertEquals("//*", selector.toXPath());
    }

    @Test
    public void testTagSet() {
        BaseSelector selector = new BaseSelector().tag("div");
        Assertions.assertEquals("//div", selector.toXPath());
    }

    @Test
    public void testTagChange() {
        BaseSelector selector = new BaseSelector().tag("div");
        Assertions.assertEquals("//a", selector.tag("a").toXPath());
    }
}
