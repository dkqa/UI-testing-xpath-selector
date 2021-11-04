package multiple;

import org.junit.Assert;
import org.junit.Test;
import selector.MultipleSelector;
import selector.SimpleSelector;

import java.util.Arrays;
import java.util.List;

public class ConstructorMultipleTest {

    @Test
    public void testConstructorDefault() {
        Assert.assertEquals("/descendant::*", new MultipleSelector().toXPath());
    }

    @Test
    public void testConstructorList() {
        List<SimpleSelector> list = Arrays.asList(
                new SimpleSelector().tag("div1"),
                new SimpleSelector().tag("div2"),
                new SimpleSelector().tag("div3"));
        MultipleSelector selector = new MultipleSelector(list);
        Assert.assertEquals("/descendant::div1/descendant::div2/descendant::div3", selector.toXPath());
    }
}
