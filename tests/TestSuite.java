import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  Others.class,
  PaymentTest.class,
  ChangeEmployeeInfoTest.class,
  AddDeleteEmployeeTest.class,
})

public class TestSuite {}
