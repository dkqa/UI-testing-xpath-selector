import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.dkqa.selector.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TagSelectorTest.class,
        TextAttributeSelectorTest.class,
        PositionSelectorTest.class,
        NameSelectorTest.class,
        AxisSelectorTest.class,
        AxisAttributeSelectorTest.class,
        AttributeSelectorTest.class
})
public class SelectorTest {
}
