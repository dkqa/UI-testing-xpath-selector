package simple;

import org.junit.Assert;
import org.junit.Test;
import selector.SimpleSelector;

public class HashCodeSimpleTest {

    @Test
    public void testAddAttributeHashCodeChanged() {
        SimpleSelector selector = new SimpleSelector();
        SimpleSelector selector2 = selector.attribute("attr", "value", true, true);
        Assert.assertFalse(selector.equals(selector2));
    }

    @Test
    public void testAddTextHashCodeChanged() {
        SimpleSelector selector = new SimpleSelector();
        SimpleSelector selector2 = selector.text("text", true, true, true);
        Assert.assertFalse(selector.equals(selector2));
    }

    @Test
    public void testAddPositionHashCodeNotChanged() {
        SimpleSelector selector = new SimpleSelector();
        SimpleSelector selector2 = selector.position(12);
        Assert.assertTrue(selector.equals(selector2));
    }
}
