import org.junit.Assert;
import org.junit.Test;

public class HashCodeTest {

    @Test
    public void testAddAttributeHashCodeChanged() {
        BaseSelector selector = new BaseSelector();
        BaseSelector selector2 = selector.attribute("attr", "value", true, true);
        Assert.assertFalse(selector.equals(selector2));
    }

    @Test
    public void testAddTextHashCodeChanged() {
        BaseSelector selector = new BaseSelector();
        BaseSelector selector2 = selector.text("text", true, true, true);
        Assert.assertFalse(selector.equals(selector2));
    }

    @Test
    public void testAddPositionHashCodeNotChanged() {
        BaseSelector selector = new BaseSelector();
        BaseSelector selector2 = selector.position(12);
        Assert.assertTrue(selector.equals(selector2));
    }
}
