import multiple.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConstructorMultipleTest.class,
        TagMultipleTest.class,
        AttributeMultipleTest.class,
        AxisAttributeMultipleTest.class,
        PositionMultipleTest.class,
        NameMultipleTest.class,
        TextAttributeMultipleTest.class,
        AxisMultipleTest.class
})
public class MultipleTest {
}
