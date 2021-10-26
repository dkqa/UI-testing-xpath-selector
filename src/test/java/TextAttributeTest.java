import org.junit.Assert;
import org.junit.Test;

public class TextAttributeTest {

    @Test
    public void testTextAttrDotContainsEnabled() {
        BaseSelector selector = new BaseSelector().text("text", true, true, true);
        Assert.assertEquals("//*[contains(.,'text')]", selector.toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsEnabled() {
        BaseSelector selector = new BaseSelector().text("text", false, true, true);
        Assert.assertEquals("//*[contains(text(),'text')]", selector.toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsEnabled() {
        BaseSelector selector = new BaseSelector().text("text", true, false, true);
        Assert.assertEquals("//*[.='text']", selector.toXPath());
    }

    @Test
    public void testTextAttrDotContainsNotEnabled() {
        BaseSelector selector = new BaseSelector().text("text", true, true, false);
        Assert.assertEquals("//*[not(contains(.,'text'))]", selector.toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsEnabled() {
        BaseSelector selector = new BaseSelector().text("text", false, false, true);
        Assert.assertEquals("//*[text()='text']", selector.toXPath());
    }

    @Test
    public void testTextAttrNotDotContainsNotEnabled() {
        BaseSelector selector = new BaseSelector().text("text", false, true, false);
        Assert.assertEquals("//*[not(contains(text(),'text'))]", selector.toXPath());
    }

    @Test
    public void testTextAttrDotNotContainsNotEnabled() {
        BaseSelector selector = new BaseSelector().text("text", true, false, false);
        Assert.assertEquals("//*[not(.='text')]", selector.toXPath());
    }

    @Test
    public void testTextAttrNotDotNotContainsNotEnabled() {
        BaseSelector selector = new BaseSelector().text("text", false, false, false);
        Assert.assertEquals("//*[not(text()='text')]", selector.toXPath());
    }

    @Test
    public void testTextAttrAddTwo() {
        BaseSelector selector = new BaseSelector()
                .text("text1", false, false, false)
                .text("text2", false, false, false);
        Assert.assertEquals("//*[not(text()='text1')][not(text()='text2')]", selector.toXPath());
    }
}
