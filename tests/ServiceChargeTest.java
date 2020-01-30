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

public class ServiceChargeTest {

  private int empId;
  private Employee e;

  private AddHourlyEmployee ahe;

  @Before
  public void setUp() {
    ahe = new AddHourlyEmployee(empId, "Berb", "Boston", 15.24);
  }

  @Test
  public void hourlyUnionMemberServiceCharge() {
    ahe.execute();
    int memberId = 7734;
    ChangeMemberTransaction cmt = 
      new ChangeMemberTransaction(empId, memberId, 9.42);
    cmt.execute();
    Date payDate = new Date(2001, 11, 9);
    ServiceChargeTransaction sct = 
      new ServiceChargeTransaction(memberId, payDate, 19.42);
    sct.execute();
    TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId);
    tct.execute();
    PaydayTransaction pt = new PaydayTransaction(payDate);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(pc.getPayDate(), payDate);
    assertEquals(Double.valueOf(8 * 15.24), Double.valueOf(pc.getGrossPay()));
    assertEquals("Hold", pc.getField("Disposition"));
    assertEquals(Double.valueOf(9.42 + 19.42), 
        Double.valueOf(pc.getDeductions()));
    assertEquals(Double.valueOf((8 * 15.24) - (9.42 + 19.42)),
        Double.valueOf(pc.getNetPay()));
  }

  @Test
  public void serviceChargesSpanningMultiplePayPeriods() {
    System.out.println("-------------------ServiceCharge-----------------");
    ahe.execute();
    int memberId = 7734;
    ChangeMemberTransaction cmt = 
      new ChangeMemberTransaction(empId, memberId, 9.42);
    cmt.execute();
    Date earlyDate = new Date(2001, 11, 2);
    Date lateDate = new Date(2001, 11, 16);
    Date payDate = new Date(2001, 11, 9);
    ServiceChargeTransaction sct = 
      new ServiceChargeTransaction(memberId, payDate, 19.42);
    sct.execute();
    sct = new ServiceChargeTransaction(memberId, earlyDate, 100.00);
    sct.execute();
    sct = new ServiceChargeTransaction(memberId, lateDate, 200.00);
    sct.execute();
    TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId);
    tct.execute();
    PaydayTransaction pt = new PaydayTransaction(payDate);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(pc.getPayDate(), payDate);
    assertEquals(Double.valueOf(8 * 15.24), Double.valueOf(pc.getGrossPay()));
    assertEquals("Hold", pc.getField("Disposition"));
    assertEquals(Double.valueOf(9.42 + 19.42), 
        Double.valueOf(pc.getDeductions()));
    assertEquals(Double.valueOf((8 * 15.24) - (9.42 + 19.42)),
        Double.valueOf(pc.getNetPay()));
  }
}
