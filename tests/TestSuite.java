import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
ServiceChargeTest.class,
    Others.class,
    HourlyPayTest.class,
    SalariedPayTest.class,
    CommissionedPayTest.class,
    ChangeEmployeeInfoTest.class,
    AddDeleteEmployeeTest.class,
})

public class TestSuite {}
