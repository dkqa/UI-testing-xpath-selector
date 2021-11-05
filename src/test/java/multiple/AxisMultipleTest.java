package multiple;

import org.junit.Assert;
import org.junit.Test;
import selector.MultipleSelector;
import selector.SimpleSelector;

public class AxisMultipleTest {

    final MultipleSelector BASE = new MultipleSelector(new SimpleSelector().tag("base"));

    final MultipleSelector ITEM = BASE.descendant(new MultipleSelector(new SimpleSelector().tag("item")));
    final MultipleSelector ITEM_TITLE = ITEM.descendant(new MultipleSelector(new SimpleSelector().tag("title")));

    final MultipleSelector ITEM_BLOCKED = ITEM.isDescendant(new MultipleSelector(new SimpleSelector().tag("block_icon")));
    final MultipleSelector ITEM_BLOCKED_TITLE = ITEM_BLOCKED.descendant(ITEM_TITLE);

    final MultipleSelector FIRST = new MultipleSelector(new SimpleSelector().tag("first"));
    final MultipleSelector SECOND = new MultipleSelector(new SimpleSelector().tag("second"));

    @Test
    public void testAxisDescendant() {
        Assert.assertEquals("/descendant::first/descendant::second",
                FIRST.descendant(SECOND).toXPath());
    }

    @Test
    public void testAxisDescendantOrSelf() {
        Assert.assertEquals("/descendant::first/descendant-or-self::second",
                FIRST.descendantOrSelf(SECOND).toXPath());
    }

    @Test
    public void testAxisFollowing() {
        Assert.assertEquals("/descendant::first/following::second",
                FIRST.following(SECOND).toXPath());
    }

    @Test
    public void testAxisFollowingSibling() {
        Assert.assertEquals("/descendant::first/following-sibling::second",
                FIRST.followingSibling(SECOND).toXPath());
    }

    @Test
    public void testAxisParent() {
        Assert.assertEquals("/descendant::first/parent::second",
                FIRST.parent(SECOND).toXPath());
    }

    @Test
    public void testAxisChild() {
        Assert.assertEquals("/descendant::first/child::second",
                FIRST.child(SECOND).toXPath());
    }

    @Test
    public void testAxisAncestor() {
        Assert.assertEquals("/descendant::first/ancestor::second",
                FIRST.ancestor(SECOND).toXPath());
    }

    @Test
    public void testAxisPreceding() {
        Assert.assertEquals("/descendant::first/preceding::second",
                FIRST.preceding(SECOND).toXPath());
    }

    @Test
    public void testAxisAddOnce() {
        Assert.assertEquals("/descendant::base/descendant::item", ITEM.toXPath());
    }

    @Test
    public void testAxisAddTwice() {
        Assert.assertEquals("/descendant::base/descendant::item/descendant::title",
                ITEM_TITLE.toXPath());
    }

    @Test
    public void testAxisMerge() {
        Assert.assertEquals("/descendant::base/descendant::item[descendant::block_icon]/descendant::title",
                ITEM_BLOCKED_TITLE.toXPath());
    }

}
