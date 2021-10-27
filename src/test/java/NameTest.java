import org.junit.Assert;
import org.junit.Test;

public class NameTest {

    @Test
    public void testNameDefault() {
        BaseSelector selector = new BaseSelector();
        Assert.assertEquals("/descendant::*", selector.getName());
    }

    @Test
    public void testNameSet() {
        BaseSelector selector = new BaseSelector().name("name");
        Assert.assertEquals("name", selector.getName());
    }
}
