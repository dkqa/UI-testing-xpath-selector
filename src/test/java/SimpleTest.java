import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import simple.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TagSimpleTest.class,
        AttributeSimpleTest.class,
        TextAttributeSimpleTest.class,
        PositionSimpleTest.class,
        NameSimpleTest.class,
        HashCodeSimpleTest.class,
        AxisAttributeSimpleTest.class
})
public class SimpleTest {
}
