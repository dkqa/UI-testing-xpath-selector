package com.github.dkqa.selector;

import com.github.dkqa.selector.predicates.TextPredicate;
import org.junit.Assert;
import org.junit.Test;
import com.github.dkqa.selector.base.Selector;
import com.github.dkqa.selector.predicates.AttrPredicate;

public class AxisSelectorTest {

    @Test
    public void testSelectorHasCommonRootsAddPosition() {
        Selector BASE = new Selector().tag("base");
        Selector ITEM = BASE.descendant(new Selector().tag("item"));
        Selector ITEM_NAME = ITEM.descendant(new Selector().tag("item_name"));
        Assert.assertEquals("/descendant::base[3]/descendant::item/descendant::item_name",
                BASE.position(3).descendant(ITEM_NAME).toXPath());
    }

    @Test
    public void testSelectorHasCommonRootsAddAxisAttribute() {
        Selector BASE = new Selector().tag("base");
        Selector ITEM = BASE.descendant(new Selector().tag("item"));
        Selector ITEM_NAME = ITEM.descendant(new Selector().tag("item_name"));
        Selector BLOCK_ICON = new Selector().tag("block_icon");
        Assert.assertEquals("/descendant::base/descendant::item[descendant::block_icon]/descendant::item_name",
                ITEM.isDescendant(BLOCK_ICON).descendant(ITEM_NAME).toXPath());
    }

    @Test
    public void testSelectorHasCommonRootsAddAttribute() {
        Selector BASE = new Selector().tag("base");
        Selector ITEM = BASE.descendant(new Selector().tag("item"));
        Selector ITEM_NAME = ITEM.descendant(new Selector().tag("item_name"));
        Assert.assertEquals("/descendant::base/descendant::item[@class='value']/descendant::item_name",
                ITEM.attribute(new AttrPredicate().name("class").value("value")).descendant(ITEM_NAME).toXPath());
    }

    @Test
    public void testSelectorHasCommonRootsAddTextAttribute() {
        Selector BASE = new Selector().tag("base");
        Selector ITEM = BASE.descendant(new Selector().tag("item"));
        Selector ITEM_NAME = ITEM.descendant(new Selector().tag("item_name"));
        Assert.assertEquals("/descendant::base/descendant::item[text()='text']/descendant::item_name",
                ITEM.attribute(new TextPredicate().value("text")).descendant(ITEM_NAME).toXPath());
    }

    @Test
    public void testSelectorAddAxisSelectorCompose() {
        Selector LIST = new Selector().tag("list");
        Selector ITEM_1 = new Selector().tag("item_1");
        Selector ITEM_2 = new Selector().tag("item_2");
        Selector ITEM_3 = new Selector().tag("item_3");
        Selector ITEMS = SelectorFactory.compose(ITEM_1, ITEM_2, ITEM_3);
        Assert.assertEquals("/descendant::list/descendant::item_1 | /descendant::list/descendant::item_2 | /descendant::list/descendant::item_3",
                LIST.descendant(ITEMS).toXPath());
    }

    @Test
    public void testSelectorComposeAddAxisSelectorCompose() {
        Selector LIST_1 = new Selector().tag("list_1");
        Selector LIST_2 = new Selector().tag("list_2");
        Selector LIST_3 = new Selector().tag("list_3");
        Selector LISTS = new Selector(LIST_1, LIST_2, LIST_3);

        Selector ITEM_1 = new Selector().tag("item_1");
        Selector ITEM_2 = new Selector().tag("item_2");
        Selector ITEM_3 = new Selector().tag("item_3");
        Selector ITEMS = new Selector(ITEM_1, ITEM_2, ITEM_3);

        Assert.assertEquals("/descendant::list_1/descendant::item_1 | /descendant::list_2/descendant::item_1 | /descendant::list_3/descendant::item_1" +
                        " | /descendant::list_1/descendant::item_2 | /descendant::list_2/descendant::item_2 | /descendant::list_3/descendant::item_2" +
                        " | /descendant::list_1/descendant::item_3 | /descendant::list_2/descendant::item_3 | /descendant::list_3/descendant::item_3",
                LISTS.descendant(ITEMS).toXPath());
    }

    @Test
    public void testSelectorHasCommonRootsInMiddleAddAttribute() {
        Selector BASE = new Selector().tag("base");
        Selector ITEM = BASE.descendant(new Selector().tag("item"));
        Selector ITEM_NAME = ITEM.descendant(new Selector().tag("item_name"));

        Selector BASE_OF_THE_BASE = new Selector().tag("base-base");
        Selector NEW_BASE = BASE_OF_THE_BASE.descendant(BASE);

        Assert.assertEquals("/descendant::base-base/descendant::base/descendant::item[@class='value']/descendant::item_name",
                NEW_BASE.descendant(ITEM.attribute(new AttrPredicate().name("class").value("value"))).descendant(ITEM_NAME).toXPath());

    }
}
