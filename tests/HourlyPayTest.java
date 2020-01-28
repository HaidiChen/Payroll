import org.junit.Test;
import org.junit.Before;
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

public class HourlyPayTest {

  private int empId;
  private Employee e;

  private AddHourlyEmployee ahe;

  @Before
  public void setUp() {
    ahe = new AddHourlyEmployee(empId, "Berb", "Boston", 15.25);
  }

  @Test
  public void paySingleHourlyEmployeeOvertimeOneTimeCard() {
    ahe.execute();
    Date payDate = new Date(2001, 11, 9);

    TimeCardTransaction tct = new TimeCardTransaction(payDate, 9.0, empId);
    tct.execute();
    PaydayTransaction pt = new PaydayTransaction(payDate);
    pt.execute();
    validateHourlyPaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
  }

  @Test
  public void paySingleHourlyEmployeeOneTimeCard() {
    ahe.execute();
    Date payDate = new Date(2001, 11, 9);

    TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId);
    tc.execute();
    PaydayTransaction pt = new PaydayTransaction(payDate);
    pt.execute();
    validateHourlyPaycheck(pt, empId, payDate, 30.5);
  }

  @Test
  public void paySingleHourlyEmployeeWithNoTimeCards() {
    ahe.execute();
    Date payDate = new Date(2001, 11, 9);
    PaydayTransaction pt = new PaydayTransaction(payDate);
    pt.execute();
    validateHourlyPaycheck(pt, empId, payDate, 0.0);
  }

  private void validateHourlyPaycheck(
      PaydayTransaction pt, int empId, Date payDate, double pay) {
    Paycheck pc = pt.getPaycheck(empId);
    assertEquals(pc.getPayDate(), payDate);
    assertEquals(Double.valueOf(pay), Double.valueOf(pc.getGrossPay()));
    assertEquals("Hold", pc.getField("Disposition"));
    assertEquals(Double.valueOf(pc.getDeductions()), Double.valueOf(0.0));
    assertEquals(Double.valueOf(pc.getNetPay()), Double.valueOf(pay));
  }

}
