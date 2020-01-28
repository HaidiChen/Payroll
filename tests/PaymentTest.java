import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import intfs.*;
import schedules.*;
import transactions.*;
import global.*;
import methods.*;
import classifications.*;
import affiliations.*;

public class PaymentTest {

  private int empId;
  private Employee e;

  private AddSalariedEmployee ase;
  private AddHourlyEmployee ahe;
  private AddCommissionedEmployee ace;

  @Before
  public void setUp() {
    ase = new AddSalariedEmployee(empId, "Alix", "Arlinton", 200.00);
    ahe = new AddHourlyEmployee(empId, "Berb", "Boston", 20.00);
    ace = new AddCommissionedEmployee(empId, "Clerk", "Croker", 2000.00, 20.00);
  }

  @Test
  public void paySalariedEmployee() {
    ase.execute();
    ChangeUnaffiliatedTransaction ct = new ChangeUnaffiliatedTransaction(empId);
    ct.execute();
    Date payDate = new Date(2001, 11, 30);
    PaydayTransaction pt = new PaydayTransaction(payDate);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(pc.getPayDate().toString(), payDate.toString());
  }

  @Test
  public void paySingleSalariedEmployeeOnWrongDate() {
    ase.execute();
    Date payDate = new Date(2001, 11, 29);
    PaydayTransaction pt = new PaydayTransaction(payDate);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
  }

}
