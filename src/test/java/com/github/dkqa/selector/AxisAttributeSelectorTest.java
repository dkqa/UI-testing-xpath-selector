package com.github.dkqa.selector;

import org.junit.Assert;
import org.junit.Test;
import com.github.dkqa.selector.base.Selector;

public class AxisAttributeSelectorTest {

    @Test
    public void testAxisAttrEnabled() {
        Selector SEL_1 = new Selector().tag("one");
        Selector SEL_2 = new Selector().tag("two");
        Selector SEL_3 = new Selector().tag("three");
        Selector SEL_4 = new Selector().tag("four");
        Selector SEL_5 = SEL_1.descendant(SEL_2);
        Selector SEL_6 = SEL_3.descendant(SEL_4);
        Assert.assertEquals("/descendant::one/descendant::two[descendant::three/descendant::four]",
                SEL_5.isDescendant(SEL_6).toXPath());
    }

    @Test
    public void testAxisAttrNotEnabled() {
        Selector SEL_1 = new Selector().tag("one");
        Selector SEL_2 = new Selector().tag("two");
        Selector SEL_3 = new Selector().tag("three");
        Selector SEL_4 = new Selector().tag("four");
        Selector SEL_5 = SEL_1.descendant(SEL_2);
        Selector SEL_6 = SEL_3.descendant(SEL_4);
        Assert.assertEquals("/descendant::one/descendant::two[not(ancestor::three/descendant::four)]",
                SEL_5.isNotAncestor(SEL_6).toXPath());
    }

    @Test
    public void testAxisAttrTwo() {
        Selector SEL_1 = new Selector().tag("one");
        Selector SEL_2 = new Selector().tag("two");
        Selector SEL_3 = new Selector().tag("three");
        Selector SEL_4 = new Selector().tag("four");
        Selector SEL_5 = SEL_1.descendant(SEL_2);
        Assert.assertEquals("/descendant::one/descendant::two[descendant::three][following::four]",
                SEL_5.isDescendant(SEL_3).isFollowing(SEL_4).toXPath());
    }

    @Test
    public void testAxisAttrNested() {
        Selector SEL_1 = new Selector().tag("one");
        Selector SEL_2 = new Selector().tag("two");
        Selector SEL_3 = new Selector().tag("three");
        Selector SEL_4 = new Selector().tag("four");
        Selector SEL_5 = SEL_1.descendant(SEL_2);
        Assert.assertEquals("/descendant::one/descendant::two[descendant::three[following::four]]",
                SEL_5.isDescendant(SEL_3.isFollowing(SEL_4)).toXPath());
    }

    @Test
    public void testComposeAxisAttrAdd() {
        Selector SEL_1 = new Selector().tag("one");
        Selector SEL_2 = new Selector().tag("two");
        Selector SEL_3 = new Selector().tag("three");
        Selector SEL_4 = new Selector().tag("four");
        Selector SEL_5 = new Selector(SEL_1, SEL_2, SEL_3);
        Assert.assertEquals("/descendant::one[descendant::four]" +
                        " | /descendant::two[descendant::four]" +
                        " | /descendant::three[descendant::four]",
                SEL_5.isDescendant(SEL_4).toXPath());
    }

    @Test
    public void testAxisAttrAddComposed() {
        Selector SEL_1 = new Selector().tag("one");
        Selector SEL_2 = new Selector().tag("two");
        Selector SEL_3 = new Selector().tag("three");
        Selector SEL_4 = new Selector().tag("four");
        Selector SEL_5 = new Selector(SEL_1, SEL_2, SEL_3);
        Assert.assertEquals("/descendant::four[descendant::one | descendant::two | descendant::three]",
                SEL_4.isDescendant(SEL_5).toXPath());
    }
}
