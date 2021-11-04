package multiple;

import org.junit.Assert;
import org.junit.Test;
import selector.MultipleSelector;
import selector.SimpleSelector;

public class NameMultipleTest {

    MultipleSelector selector = new MultipleSelector(
            new SimpleSelector().name("Element 1"),
            new SimpleSelector().name("Element 2"),
            new SimpleSelector().name("Element 3"));

    @Test
    public void testGetName() {
        Assert.assertEquals("Element 1 - Element 2 - Element 3", selector.getName());
    }

    @Test
    public void testSetHardName() {
        Assert.assertEquals("Hard Name", selector.hardName("Hard Name").getName());
    }

    @Test
    public void testEmptyName() {
        MultipleSelector selector = new MultipleSelector(
                new SimpleSelector(),
                new SimpleSelector(),
                new SimpleSelector());
        Assert.assertEquals("/descendant::*/descendant::*/descendant::*", selector.getName());
    }

    @Test
    public void testOnlyFirstSimpleSelectorHasName() {
        MultipleSelector selector = new MultipleSelector(
                new SimpleSelector().name("Element 1"),
                new SimpleSelector(),
                new SimpleSelector());
        Assert.assertEquals("Element 1", selector.getName());
    }

}
