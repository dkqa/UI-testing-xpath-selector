[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.dkqa/ui-testing-xpath/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.dkqa/ui-testing-xpath)



# UI-testing-xpath
***
At the moment, when testing the UI, a very cumbersome approach to storing locators is used. So was created this library.

This library implements the functionality of the XPath language.
xPath is built as a Selector object. These objects can interact with each other, which gives flexibility and allows more structured storage of xPaths elements. Using this library, the code has fewer duplicates and is easier to debug.
***
***
## 1. Selector

The main object of this library is the `Selector` implements the `ISelector` interface.

***

### 1.1. ISelector _interface_

The `ISelector` interface describes the behavior of addressing and has methods:

- `base_axis(Axes axis);`
- `tag(String tag);` 
- `attribute(ISelectorPredicate predicate);`
- `axis(Axes axis, ISelector com.github.dkqa.selector);`
- `name(String name);`
- `String getName();`
- `String toXPath();`
- Also 'Shortest Methods' for axes such as `descendant(...)`, `following(...)`,... which contain a `axis(...)` method.

***

### 1.2. Selector _class_

A `Selector` can consist of either one node - `/axis::tag[predicate]` or several 
nodes - `/axis::tag[predicate]/axis::tag[predicate].../axis::tag[predicate]`,
also possible to create composite nodes - `/axis::tag[predicate]` ` | ` `/axis::tag[predicate]/axis::tag[predicate]` `| ` `...` ` |` `/axis::tag[predicate]`

1) Method `base_axis(Axes axis)` sets the axis to node:
- When one node - **_`/`axis::`tag[predicate]`_**
- When several nodes, changes axis for root node - **_`/`axis::`tag[predicate]/axis::tag[predicate]`_**
- When composite, changes axis for each part in root node - **_`/`axis::`tag[pred]/axis::tag[pred] | ... | /`axis::`tag[pred]/axis::tag[pred]`_**

2) Method `tag(String tag)` sets the tag to node:
- When one node - **_`/axis::`tag`[predicate]`_**
- When several nodes, changes tag for last node - **_`/axis::tag[predicate]/axis::`tag`[predicate]`_**
- When composite, changes tag for each part in last node - **_`/axis::tag[pred]/axis::`tag`[pred] | ... | /axis::tag[pred]/axis::`tag`[pred]`_**

3) Method `attribute(ISelectorPredicate predicate)` sets the predicates to node:
- When one node - **_`/axis::tag`[predicate]_**
- When several nodes, adds predicates for last node - **_`/axis::tag[predicate]/axis::tag`[predicate]_**
- When composite, adds predicates for each part in last node - **_`/axis::tag[pred]/axis::tag`[pred]` | ... | /axis::tag[pred]/axis::tag`[pred]_**
- You can add as many predicates as you like - `/axis::tag[pred][pred]...[pred]`

4) Also was added Method `replaceAttribute(ISelectorPredicate predicate)` to `Selector`.
   You can use this method to control incoming predicates, for example, if you want to change position predicate. 
Example: you have a com.github.dkqa.selector `VAR_SEL` - `/desendant::div[2]`, you can call `VAR_SEL.replaceAttribute(positionPredicate(5))` then XPath will be `/descendant::div[5]`
 
5) Method `axis(Axes axis, ISelector com.github.dkqa.selector)` sets next node. Example: 
`selector0` => `/axis0::tag0[pred]` then if call
`selector0.axis(axis1, selector1).axis(axis2, selector2)` => `/axis0::tag0[pred]/axis1::tag1[pred]/axis2::tag2[pred]`.
Consider in more detail below in the examples of use...
6) Method `name(String name)` sets com.github.dkqa.selector name, you may need it for logs.
7) Method `String getName()` returns the name of the com.github.dkqa.selector.
8) Method `String toXPath()` returns the XPath

***
***

## 2. Predicates

The predicate is set by the `attribute(ISelectorPredicate predicate)` method. 
A Predicate object implements `ISelectorPredicate` interface. 
At the moment there are such objects predicates:

- `AttrPredicate`
- `AxisPredicate`
- `PositionPredicate`

You can create your own predicates by implementing `ISelectorPredicate` interface 
or inheriting `SelectorPredicate` abstract class.

***

### 2.1. ISelectorPredicate _interface_

The interface has only one method:
- `String toAttr();` - returns string (example `[@class='Value']` depending on your implementation)

***

### 2.2. SelectorPredicate _abstract class_

The class implements `ISelectorPredicate` interface 
and describes the logic `not` (for example `[not(...)]`).
- Method `not()`

***

### 2.3. AttrPredicate _class_

The class extends `SelectorPredicate` abstract class. 
Has the form `[@name='value']` or `[not(@name='value')]` or `[contains(@name,'value')]` or `[not(contains(@name,'value'))]` ...

- Method `name(String name)` sets the name attribute
- Method `value(String value)` sets the value of parameter name
- Method `contains()` sets the contains() function
- Method `not()` sets the not() function
- Method `normalize_space()` set the normalize_space() function

Examples of using:

- `new AttrPredicate().name("class")` toAttr() => `[@class]` 
- `new AttrPredicate().name("class").value("myValue")` toAttr() => `[@class='myValue']` 
- `new AttrPredicate().value("myValue")` toAttr() => `[@*='myValue']` 
- `new AttrPredicate().name("class").value("myValue").not()` toAttr() => `[not(@class='myValue')]` 
- `new AttrPredicate().name("class").value("myValue").contains()` toAttr() => `[contains(@class='myValue')]` 
- `new AttrPredicate().name("class").value("myValue").contains().not()` toAttr() => `[not(contains(@class='myValue'))]` 
- `new AttrPredicate().name("class").value("myValue").normalize_space()` toAttr() => `[normalize_space(@class)='myValue']` 


***

### 2.4. PositionPredicate _class_

The class extends `SelectorPredicate` abstract class. Has the form `[2]` or `[position()>2]` or `[not(position()=3)]` or `[position()=last()]` etc...

- Method `position(int pos)` sets position
- Method `positionMore(int pos)` sets position and condition `>`
- Method `positionLess(int pos)` sets position and condition `<`
- Method `positionMoreOrEqual(int pos)` sets position and condition `>=`
- Method `positionLessOrEqual(int pos)` sets position and condition `<=`
- Method `last()` sets position `last()`
- Method `last(int pos)` sets position `last()-pos`
- Method `not()` sets the not() function

Examples of using:

- `new PositionPredicate().position(3)` toAttr() => `[3]`
- `new PositionPredicate().positionMore(3)` toAttr() => `[position()>3]`
- `new PositionPredicate().positionLess(3)` toAttr() => `[position()<3]`
- `new PositionPredicate().positionMoreOrEqual(3)` toAttr() => `[position()>=3]`
- `new PositionPredicate().positionLessOrEqual(3)` toAttr() => `[position()<=3]`
- `new PositionPredicate().last()` toAttr() => `[position()=last()]`
- `new PositionPredicate().last(2)` toAttr() => `[position()=last()-2]`
- `new PositionPredicate().last().not()` toAttr() => `[not(position()=last())]`

Note:

- if you try to use several methods, only the last one will apply - `new PositionPredicate().last(2).positionMore(5).position(12)` toAttr() => `[12]`
- if you try to use incorrect position `new PositionPredicate().position(-1)` or `new PositionPredicate().position(0)` then toAttr() returns empty string

***

### 2.5. AxisPredicate _class_

The class extends `SelectorPredicate` abstract class. Has the form `[axis::tag[pred]]` or `[not(axis::tag[pred])]` or `[axis::tag[pred] | axis::tag[pred]]` 

- Method `com.github.dkqa.selector(Axes axis, ISelector com.github.dkqa.selector)` sets axis and com.github.dkqa.selector
- Method `not()` sets the not() function

Examples of using:

- `new AxisPredicate().com.github.dkqa.selector(Axes.FOLLOWING, anySelector)` toAttr() => `[following::tag[pred]]` depending on which com.github.dkqa.selector entered
- `new AxisPredicate().com.github.dkqa.selector(Axes.FOLLOWING, anySelector).not()` toAttr() => `[not(following::tag[pred])]`

***

### 2.6. And others predicate

- `TextPredicate` class (example: `//*[text()='value']`, `//*[contains(text()'value')]`, `//*[not(text()='value')]` ...)
- `DotPredicate` class (example: `//*[.='value']` ...)

***
***

## 3. _Examples of using Selector_

***

### 3.1. _Creating Selector object_:
    
    new Selector();

Selector has method `String toXPath();` which return XPath as a String value

By default, the com.github.dkqa.selector has `/descendant::*` XPath
***

### 3.2. _Set tag:_

Method: `tag(String tag)`

    new Selector().tag("div");

XPath - `/descendant::div`
***

### 3.3. _Set attributes_:

 1 ) Method: `attribute(ISelectorPredicate predicate)`


    new Selector().attribute(new AttrPredicate().name("class").value("value"));


XPath - `/descendant::*[@class='value']`

2 ) We have the ability to add several attributes:


    new Selector()
            .attribute(new AttrPredicate().name("class").value("cValue"))
            .attribute(new AttrPredicate().name("text").value("tValue").contains());


XPath - `/descendant::*[@class='cValue'][contains(text()='tValue')]`

3 ) Selector has simplified methods for `AxisPredicate`

- `isDescendant(com.github.dkqa.selector)` equivalent `attribute(new AxisPredicate().com.github.dkqa.selector(Axes.DESCENDANT, com.github.dkqa.selector))`
- `isNotDescendant(com.github.dkqa.selector)` equivalent `attribute(new AxisPredicate().com.github.dkqa.selector(Axes.DESCENDANT, com.github.dkqa.selector)).not()`
- `isFollowing(com.github.dkqa.selector)` equivalent `attribute(new AxisPredicate().com.github.dkqa.selector(Axes.FOLLOWING, com.github.dkqa.selector))`
- `...`

~

    Selector S1 = new Selector().tag("t1"); // /descendant::t1
    
    new Selector().tag("t2").isFollowing(S1); // /descendant::t2[following::t1]  


XPath - `/descendant::t2[following::t1]`

4 ) Method `position(int pos)` equivalent `attribute(new PositionPredicate().position(pos))`


    new Selector().position(3);


XPath - `/descendant::*[3]`


***

### 3.4. Set Axis with other selectors

Method: `axis(Axes axis, Selector com.github.dkqa.selector)`

- method `descendant(com.github.dkqa.selector)` equivalent `axis(Axes.DESCENDANT, com.github.dkqa.selector)`
- method `following(com.github.dkqa.selector)` equivalent `axis(Axes.FOLLOWING, com.github.dkqa.selector)`
- `...`

~


    Selector S1 = new Selector().tag("t1");
    Selector S2 = new Selector().tag("t2");

    S1.following(S2);


XPath - `/descendant::t1/following::t2`

***

### 3.5. _Set name (this can come in handy for logs):_

Method: `name(String name)`


    new Selector().name("My name");


Selector has method `String getName();` which return the name

Name - `(My name)`

If name wasn't initialized then the method `getName()` return XPath of the com.github.dkqa.selector

P.S. selectors names add up, example:


    Selector LIST = new Selector().name("List");
    Selector ITEM = new Selector().name("Item");
    Selector NAME = new Selector().name("Name");

    Selector RESULT = LIST.descendant(ITEM).descendant(NAME);


Name - `(List - Item - Name)`

But if you set name for `RESULT`, `RESULT.name("New Name")` then name - `(New Name)`
***
### 3.6. _Set composing com.github.dkqa.selector:_

1 ) Creating


    Selector S1 = new Selector().tag("t1");
    Selector S2 = new Selector().tag("t2");
    Selector S3 = new Selector().tag("t3");

    Selector COMPOSING = new Selector(S1, S2, S3);


XPath - `/descendant::t1 | /descendant::t2 | /descendant::t3`

2 ) Add Axis


    Selector S1 = new Selector().tag("t1");
    Selector S2 = new Selector().tag("t2");
    Selector S3 = new Selector().tag("t3");

    Selector COMPOSING = new Selector(S1, S2, S3);

    Selector S4 = new Selector().tag("t4);

    COMPOSING.descendant(S4);


XPath - `/descendant::t1/descendant::t4 | /descendant::t2/descendant::t4 | /descendant::t3/descendant::t4`

3 ) Add Attribute


    Selector S1 = new Selector().tag("t1");
    Selector S2 = new Selector().tag("t2");
    Selector S3 = new Selector().tag("t3");

    Selector COMPOSING = new Selector(S1, S2, S3);

    Selector S4 = new Selector().tag("t4);

    COMPOSING.isNotDescendant(S4);


XPath - `/descendant::t1[descendant::t4] | /descendant::t2[descendant::t4] | /descendant::t3[descendant::t4]`

**...or...**


    Selector S1 = new Selector().tag("t1");
    Selector S2 = new Selector().tag("t2");
    Selector S3 = new Selector().tag("t3");

    Selector COMPOSING = new Selector(S1, S2, S3);

    Selector S4 = new Selector().tag("t4);

    COMPOSING.position(3);


XPath - `/descendant::t1[3] | /descendant::t2[3] | /descendant::t3[3]`

4 ) Axis with composing com.github.dkqa.selector


    Selector S1 = new Selector().tag("t1");
    Selector S2 = new Selector().tag("t2");
    Selector S3 = new Selector().tag("t3");

    Selector COMPOSING = new Selector(S1, S2, S3);

    Selector S4 = new Selector().tag("t4);

    S4.descendant(COMPOSING);


XPath - `/descendant::t4/descendant::t1 | /descendant::t4/descendant::t2 | /descendant::t4/descendant::t3`


5 ) Attribute with composing com.github.dkqa.selector


    Selector S1 = new Selector().tag("t1");
    Selector S2 = new Selector().tag("t2");
    Selector S3 = new Selector().tag("t3");

    Selector COMPOSING = new Selector(S1, S2, S3);

    Selector S4 = new Selector().tag("t4);

    S4.isDescendant(COMPOSING);


XPath - `/descendant::t4[descendant::t1 | /descendant::t2 | /descendant::t3]`

6 ) Composing com.github.dkqa.selector Axis with Composing com.github.dkqa.selector 


    Selector S1 = new Selector().tag("t1");
    Selector S2 = new Selector().tag("t2");
    Selector COMPOSING_1 = new Selector(S1, S2); // /descendant::t1 | /descendant::t2

    Selector S3 = new Selector().tag("t3");
    Selector S4 = new Selector().tag("t4);
    Selector COMPOSING_2 = new Selector(S3, S4); // /descendant::t3 | /descendant::t4

    COMPOSING_1.descendant(COMPOSING_2);


XPath - `/descendant::t1/descendant::t3 | /descendant::t1/descendant::t4 | /descendant::t2/descendant::t3 | /descendant::t2/descendant::t4`

***
***

## 4. For simplified using was created `SelectorFactory` and `SelectorPredicateFactory` classes

***

### 4.1. SelectorFactory _class_

For example, the following methods have been added to `SelectorFactory`:

    public static Selector com.github.dkqa.selector() {
        return new Selector();
    }

    public static Selector compose(Selector... selectors) {
        return new Selector(selectors);
    }

    public static Selector tag(String tag) {
        return new Selector().tag(tag);
    }

    public static Selector textSelector(String text) {
        return new Selector().attribute(new AttrPredicate().value("text").value(text));
    }

    public static Selector div(String attrValue) {
        return new Selector().tag("div").attribute(new AttrPredicate().value(attrValue));
    }

    public static Selector div_contains(String attrValue) {
        return new Selector().tag("div").attribute(new AttrPredicate().value(attrValue).contains());
    }

I suggest you create your own factory methods for your project

***

### 4.2. SelectorPredicateFactory _class_

    public static AttrPredicate attr() {
        return new AttrPredicate();
    }

    public static AttrPredicate any(String value) {
        return new AttrPredicate().value(value);
    }

    public static AttrPredicate clazz(String value) {
        return new AttrPredicate().name("class").value(value);
    }

    public static AttrPredicate id(String value) {
        return new AttrPredicate().name("id").value(value);
    }

    public static AttrPredicate text(String value) {
        return new AttrPredicate().name("text").value(value);
    }

***
***

## 5. Also, to reduce code duplication, the `hashCode` logic was added
Example

We have any list which contains cards and blocked cards

Let's denote the list and card selectors

    import com.github.dkqa.selector.base.Selector;

    import static com.github.dkqa.selector.SelectorFactory.tag;

    public class ExamplePageClass {

        Selector LIST = tag("list");
        Selector CARD = LIST.descendant(tag("card"));
        Selector CARD_NAME = CARD.descendant(tag("name"));
XPath for `CARD_NAME` will be `/descendant::list/descendant::card/descendant::name` 

And to denote the blocked card com.github.dkqa.selector, we can use already existing selectors

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

***
***

## 6. Live example

We have page https://www.bookvoed.ru/books?genre=2

![Screenshot_10](https://user-images.githubusercontent.com/38009604/142425662-3994620f-4f03-402e-abb1-ae55e4200d2a.jpg)

Create class for with.

![Screenshot_12](https://user-images.githubusercontent.com/38009604/142425922-837a9650-acb8-4aeb-b5a5-84904cbbb18f.jpg)

In the `BookvoedPage.BookHit` class we have described all the elements related to books with the Hit mark.

![Screenshot_101](https://user-images.githubusercontent.com/38009604/142426244-d225741f-9b4f-4318-bfb9-281b06ddd731.jpg)


Thanks to the hash-code logic, we got rid of unnecessary duplicates and our page looks neatly described. 
And if the value of the class attribute changes somewhere, then we just need to change it in one place.

Using in test code:

![Screenshot_14](https://user-images.githubusercontent.com/38009604/142426078-9f5fbc18-f5f3-4108-a1de-59bde33a10aa.jpg)

![Screenshot_15](https://user-images.githubusercontent.com/38009604/142426218-fee8139f-1bdd-4158-8fd7-fd0572d62bca.jpg)

We also have the ability to generate selectors at runtime.
![Screenshot_103](https://user-images.githubusercontent.com/38009604/142426723-57c433b1-d3b4-46b3-8edd-caf2cc27d57f.jpg)
