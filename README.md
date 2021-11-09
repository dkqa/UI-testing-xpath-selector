# xpath-builder
***
This library implements the functionality of the XPath language.
xPath is built as a Selector object. These objects can interact with each other, which gives flexibility and allows more structured storage of xPaths elements. Using this library, the code has fewer duplicates and is easier to debug.
***
***
### Examples
***
### _Creating Selector object_:
    
    new Selector();

Selector has method `String toXPath();` which return XPath as a String value

By default, the selector has `/descendant::*` XPath
***

### _Set tag:_

Method: `tag(String tag)`

    new Selector().tag("div");

XPath - `/descendant::div`
***

### _Set attribute_:

Method: `attribute(String attr, String value, boolean contains, boolean enabled)`

    new Selector().attribute("class", "value", false, true);

XPath - `/descendant::*[@class='value']`

If `contains` is `true` -  `/descendant::*[contains(@class,'value')]`

If `enabled` is `false` - `/descendant::*[not(@class='value')]`
***

### _Set text attribute:_

Method: `text(String text, boolean dot, boolean contains, boolean enabled)`

    new Selector().text("myText", false, false, true);

XPath - `/descendant::*[text()='myText']`

If `dot` is `true` - `/descendant::*[.='myText']`

If `contains` is `true` -  `/descendant::*[contains(text(),'myText')]`

If `enabled` is `false` - `/descendant::*[not(text()='myText')]`
***

### _Set axial attribute:_

Method: `axis_attribute(Axes axis, Selector selector, boolean enabled)`

    Selector selector1 = new Selector().tag("myTag"); 

    new Selector().axis_attribute(Axes.CHILD, selector1, true);

XPath - `/descendant::*[child::myTag]`

If `enabled` is `false` - `/descendant::*[not(child::myTag)]`

Also, Selector has simplified methods:

`isDesendant(Selector selector)` equivalent `axis_attribute(Axes.DESCENDANT, selector, true)`

`isNotDesendant(Selector selector)` equivalent `axis_attribute(Axes.DESCENDANT, selector, false)`

...etc for each axis
***

### _Set position:_

Method: `position(int pos)`

    new Selector().position(12);

XPath - `/descendant::*[12]`

If `position` <= `0` - `/descendant::*`
***

### _Set axis with other selector:_

Method: `axis(Axes axis, Selector selector)`

    Selector S1 = new Selector().tag("s1");
    Selector S2 = new Selector().tag("s2");
    
    S1.axis(Axes.FOLLOWING, S2);

XPath - `/descendant::s1/following::s2`

Also, Selector has simplified methods:

`desendant(Selector selector)` equivalent `axis(Axes.DESCENDANT, selector)`

`following(Selector selector)` equivalent `axis(Axes.FOLLOWING, selector)`

...etc for each axis
***

### _Set name (this can come in handy for logs):_

Method: `name(String name)`

    new Selector().name("My name");

Selector has method `String getName();` which return the name

Name - `(My name)`

If name wasn't initialized then the method `getName()` return XPath of the selector

P.S. selectors names add up, example:

    Selector LIST = new Selector().name("List");
    Selector ITEM = new Selector().name("Item");
    Selector NAME = new Selector().name("Name");

    Selector RESULT = LIST.descendant(ITEM).descendant(NAME);

Name - `(List - Item - Name)`

But if you set name for `RESULT`, `RESULT.name("New Name")` then name - `(New Name)`
***
### _Set composing selector:_

    Selector S1 = new Selector().tag("s1");
    Selector S2 = new Selector().tag("s2");
    Selector S3 = new Selector().tag("s3");

    Selector COMPOSING = new Selector(S1, S2, S3);

XPath - `/descendant::s1 | /descendant::s2 | /descendant::s3`

If you set any attribute (`axis(...), attribute(...), text(...), tag(...), position(...), etc...`) for the composing selector, example `COMPOSING.position(3)` for each selector position will be added - `/descendant::s1[3] | /descendant::s2[3] | /descendant::s3[3]`
***
***
# For simplified using was created class `SelectorFactory`

For example, the following methods have been added to `SelectorFactory`:

    public static Selector selector() {
        return new Selector();
    }

    public static Selector compose(Selector... selectors) {
        return new Selector(selectors);
    }

    public static Selector tag(String tag) {
        return new Selector().tag(tag);
    }

    public static Selector text(String text) {
        return new Selector().text(text, false, false, true);
    }

    public static Selector div(String attrValue) {
        return new Selector().tag("div").attribute("*", attrValue, false, true);
    }

    public static Selector div_contains(String attrValue) {
        return new Selector().tag("div").attribute("*", attrValue, true, true);
    }

I suggest you create your own factory methods for your project
***
***
# Also, to reduce code duplication, the `hashCode` logic was added
Example

We have any list which contains cards and blocked cards

Let's denote the list and card selectors

    import selector.Selector;

    import static selector.SelectorFactory.tag;

    public class ExamplePageClass {

        Selector LIST = tag("list");
        Selector CARD = LIST.descendant(tag("card"));
        Selector CARD_NAME = CARD.descendant(tag("name"));
XPath for `CARD_NAME` will be `/descendant::list/descendant::card/descendant::name` 

And to denote the blocked card selector, we can use already existing selectors

        Selector BLOCKED_CARD = CARD.isDescendant(tag("block_icon"));
        Selector BLOCKED_CARD_NAME = BLOCKED_CARD.descendant(CARD_NAME);

XPath for `BLOCKED_CARD_NAME` will be `/descendant::list/descendant::card[descendant::block_icon]/descendant::name` 

It can also be used directly in the test, where there is some dynamic value.

    @Test
    public void test() {
        ...
        String dynamicValue = ...;
        anyAssert(CARD.isDescendantText(dynamicValue).descendant(CARD_NAME));
    }

P.S. The `hashCode` is saved only when using methods `axis_attribute(...)` or `isDescendant(...), etc...`, `position(...)` and `name(...)`