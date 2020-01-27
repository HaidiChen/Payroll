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

public class Others {

  private int empId;
  private Employee e;

  private AddSalariedEmployee ase;
  private AddHourlyEmployee ahe;
  private AddCommissionedEmployee ace;

  private CommissionedClassification cc;
  private HourlyClassification hc;
  private SalariedClassification sc;

  private TimeCardTransaction tct;
  private TimeCard tc;

  private SalesReceiptTransaction srt;
  private SalesReceipt sr;

  private ServiceChargeTransaction sct;
  private ServiceCharge svc;
  private UnionAffiliation ua;

  @Before
  public void setUp() {
    ase = new AddSalariedEmployee(empId, "Alix", "Arlinton", 200.00);
    ahe = new AddHourlyEmployee(empId, "Berb", "Boston", 20.00);
    ace = new AddCommissionedEmployee(empId, "Clerk", "Croker", 2000.00, 20.00);
  }

  @Test
  public void addServiceCharge() {
    ahe.execute();
    
    e = GpayrollDatabase.getEmployee(empId);

    int memberId = 86;
    ua = new UnionAffiliation(memberId, 12.5);
    e.setAffiliation(ua);
    GpayrollDatabase.addUnionMember(memberId, e);
    
    sct = new ServiceChargeTransaction(memberId, 20200119, 12.95);
    sct.execute();

    svc = ua.getServiceCharge(20200119);
    assertEquals(Double.valueOf(12.95), Double.valueOf(svc.getAmount()));
  }

  @Test
  public void testSalesReceiptTransaction() {
    ace.execute();

    srt = new SalesReceiptTransaction(20200119, 10, empId);
    srt.execute();

    e = GpayrollDatabase.getEmployee(empId);
    cc = (CommissionedClassification) e.getClassification();
    sr = cc.getSalesReceipt(20200119);

    assertNotNull(sr);
    assertEquals(Double.valueOf(10), Double.valueOf(sr.getAmount()));

  }

  @Test 
  public void addTimeCardToNonHourlyEmployee() {
    ase.execute();

    tct = new TimeCardTransaction(20200119, 8.0, empId);
    tct.execute();

    tct = new TimeCardTransaction(20200119, 8.0, 10);
    tct.execute();
  }

  @Test
  public void testTimeCardTransaction() {
    ahe.execute();

    TimeCardTransaction tct = new TimeCardTransaction(20200119, 8.0, empId);
    tct.execute();

    e = GpayrollDatabase.getEmployee(empId);
    hc = (HourlyClassification) e.getClassification();
    tc = hc.getTimeCard(20200119);

    assertNotNull(tc);
    assertEquals(Double.valueOf(8.0), Double.valueOf(tc.getHours()));
  }

}
