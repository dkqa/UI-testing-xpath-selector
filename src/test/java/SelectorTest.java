import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TagTest.class,
        AttributeTest.class,
        TextAttributeTest.class,
        PositionTest.class,
        NameTest.class,
        HashCodeTest.class,
        AxisAttributeTest.class
})
public class SelectorTest {

}
