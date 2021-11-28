package com.dkqa.selector;

import org.junit.Assert;
import org.junit.Test;
import com.dkqa.selector.base.Selector;

public class NameSelectorTest {

    @Test
    public void testNameDefault() {
        Selector LIST = new Selector().tag("list");
        Assert.assertEquals("(/descendant::list)", LIST.getName());
    }

    @Test
    public void testNameSet() {
        Selector LIST = new Selector().tag("list").name("List");
        Assert.assertEquals("(List)", LIST.getName());
    }

    @Test
    public void testNameInherited() {
        Selector LIST = new Selector().tag("list").name("List");
        Selector ITEM = LIST.descendant(new Selector().tag("item").name("Item"));
        Selector ITEM_NAME = ITEM.descendant(new Selector().tag("name").name("Name"));
        Assert.assertEquals("(List - Item - Name)", ITEM_NAME.getName());
    }

    @Test
    public void testNameReset() {
        Selector LIST = new Selector().tag("list").name("List");
        Selector ITEM = LIST.descendant(new Selector().tag("item").name("Item"));
        Selector ITEM_NAME = ITEM.descendant(new Selector().tag("name").name("Name"));
        Assert.assertEquals("(New Name)", ITEM_NAME.name("New Name").getName());
    }

    @Test
    public void testNameResetAndInherited() {
        Selector LIST = new Selector().tag("list").name("List");
        Selector ITEM = LIST.descendant(new Selector().tag("item").name("Item"));
        Selector ITEM_NAME = ITEM.descendant(new Selector().tag("name").name("Name"));
        Selector TEXT = new Selector().tag("text").name("Text");
        Assert.assertEquals("(New Name - Text)", ITEM_NAME.name("New Name").descendant(TEXT).getName());
    }

    @Test
    public void testSelectorComposeDefault() {
        Selector LIST_1 = new Selector().tag("list_1");
        Selector LIST_2 = new Selector().tag("list_2");
        Selector LISTS = new Selector(LIST_1, LIST_2);
        Assert.assertEquals("(/descendant::list_1) or (/descendant::list_2)", LISTS.getName());
    }

    @Test
    public void testSelectorComposeSet() {
        Selector LIST_1 = new Selector().tag("list_1");
        Selector LIST_2 = new Selector().tag("list_2");
        Selector LISTS = new Selector(LIST_1, LIST_2).name("Lists");
        Assert.assertEquals("(Lists)", LISTS.getName());
    }

    @Test
    public void testSelectorComposeReset() {
        Selector LIST_1 = new Selector().tag("list_1");
        Selector LIST_2 = new Selector().tag("list_2");
        Selector LISTS = new Selector(LIST_1, LIST_2).name("Lists");
        Assert.assertEquals("(NewLists)", LISTS.name("NewLists").getName());
    }

    @Test
    public void testSelectorComposeGet() {
        Selector LIST_1 = new Selector().tag("list_1").name("List1");
        Selector LIST_2 = new Selector().tag("list_2").name("List2");
        Selector LISTS = new Selector(LIST_1, LIST_2);
        Assert.assertEquals("(List1) or (List2)", LISTS.getName());
    }

    @Test
    public void testSelectorComposeInherited() {
        Selector LIST_1 = new Selector().tag("list_1").name("List1");
        Selector LIST_2 = new Selector().tag("list_2").name("List2");
        Selector LISTS = new Selector(LIST_1, LIST_2);
        Selector ITEM = LISTS.descendant(new Selector().tag("item").name("Item"));
        Assert.assertEquals("(List1 - Item) or (List2 - Item)", ITEM.getName());
    }

    @Test
    public void testSelectorComposeResetAndInherited() {
        Selector LIST_1 = new Selector().tag("list_1").name("List1");
        Selector LIST_2 = new Selector().tag("list_2").name("List2");
        Selector LISTS = new Selector(LIST_1, LIST_2).name("Lists");
        Selector ITEM = LISTS.descendant(new Selector().tag("item").name("Item"));
        Assert.assertEquals("(Lists - Item)", ITEM.getName());
    }
}
