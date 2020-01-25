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

public class PayrollTest {

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
  public void changeMailTransaction() {
    ase.execute();
    ChangeMailTransaction ct = new ChangeMailTransaction(empId, "BankA");
    ct.execute();
    e = GpayrollDatabase.getEmployee(empId);
    MailMethod m = (MailMethod) e.getMethod();
    assertEquals("BankA", m.getAddress());
  }

  @Test
  public void changeHoldTransaction() {
    ace.execute();
    ChangeHoldTransaction ct = new ChangeHoldTransaction(empId, "BankA");
    ct.execute();
    e = GpayrollDatabase.getEmployee(empId);
    HoldMethod dm = (HoldMethod) e.getMethod();
    assertEquals("BankA", dm.getAddress());
  }

  @Test
  public void changeDirectTransaction() {
    ace.execute();
    ChangeDirectTransaction ct = 
      new ChangeDirectTransaction(empId, "BankA", 1234);
    ct.execute();
    e = GpayrollDatabase.getEmployee(empId);
    DirectMethod dm = (DirectMethod) e.getMethod();
    assertEquals("BankA", dm.getBank());
    assertEquals(Long.valueOf(1234), Long.valueOf(dm.getAccount()));
  }

  @Test
  public void changeCommissionedTransaction() {
    ahe.execute();
    ChangeCommissionedTransaction ct = 
      new ChangeCommissionedTransaction(empId, 1275.0, 23.2);
    ct.execute();
    e = GpayrollDatabase.getEmployee(empId);
    cc = (CommissionedClassification) e.getClassification();
    assertEquals(Double.valueOf(1275.0), Double.valueOf(cc.getSalary()));
    assertEquals(Double.valueOf(23.2), Double.valueOf(cc.getRate()));
    BiweeklySchedule bs = (BiweeklySchedule) e.getSchedule();
  }

  @Test
  public void changeSalariedTransaction() {
    ace.execute();
    ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, 1275.0);
    cst.execute();
    e = GpayrollDatabase.getEmployee(empId);
    sc = (SalariedClassification) e.getClassification();
    assertEquals(Double.valueOf(1275.0), Double.valueOf(sc.getSalary()));
    MonthlySchedule ws = (MonthlySchedule) e.getSchedule();
  }

  @Test
  public void changeHourlyTransaction() {
    ace.execute();
    ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.52);
    cht.execute();
    e = GpayrollDatabase.getEmployee(empId);
    hc = (HourlyClassification) e.getClassification();
    assertEquals(Double.valueOf(27.52), Double.valueOf(hc.getRate()));
    WeeklySchedule ws = (WeeklySchedule) e.getSchedule();
  }

  @Test
  public void changeAddress() {
    ahe.execute();
    ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, "Bel");
    cat.execute();
    e = GpayrollDatabase.getEmployee(empId);
    assertEquals("Bel", e.getAddress());
  }

  @Test
  public void changeName() {
    ahe.execute();
    ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bob");
    cnt.execute();
    e = GpayrollDatabase.getEmployee(empId);
    assertEquals("Bob", e.getName());
  }

  @Test
  public void addServiceCharge() {
    ahe.execute();
    
    e = GpayrollDatabase.getEmployee(empId);

    ua = new UnionAffiliation(12.5);
    e.setAffiliation(ua);
    int memberId = 86;
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

  @Test
  public void deleteEmployee() {
    ace.execute();

    e = GpayrollDatabase.getEmployee(empId);
    assertEquals("Clerk", e.getName());

    DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
    dt.execute();

    e = GpayrollDatabase.getEmployee(empId);
    assertNull(e);
  }

  @Test
  public void addCommissionedEmployee() {
    ace.execute();

    e = GpayrollDatabase.getEmployee(empId);
    assertEquals("Clerk", e.getName());
    assertEquals("Croker", e.getAddress());

    PaymentClassification pc = e.getClassification();
    cc = (CommissionedClassification) pc;
    assertEquals(Double.valueOf(cc.getRate()), Double.valueOf(20.00));
    assertEquals(Double.valueOf(cc.getSalary()), Double.valueOf(2000.00));

    PaymentSchedule ps = e.getSchedule();
    BiweeklySchedule ws = new BiweeklySchedule();
    assertEquals(ps.getClass().getName(), ws.getClass().getName());

    PaymentMethod pm = e.getMethod();
    HoldMethod hm = new HoldMethod();
    assertEquals(pm.getClass().getName(), hm.getClass().getName());
    
  }

  @Test
  public void addHourlyEmployee() {
    ahe.execute();

    e = GpayrollDatabase.getEmployee(empId);
    assertEquals("Berb", e.getName());
    assertEquals("Boston", e.getAddress());

    PaymentClassification pc = e.getClassification();
    hc = (HourlyClassification) pc;
    assertEquals(Double.valueOf(hc.getRate()), Double.valueOf(20.00));

    PaymentSchedule ps = e.getSchedule();
    WeeklySchedule ws = new WeeklySchedule();
    assertEquals(ps.getClass().getName(), ws.getClass().getName());

    PaymentMethod pm = e.getMethod();
    HoldMethod hm = new HoldMethod();
    assertEquals(pm.getClass().getName(), hm.getClass().getName());
    
  }

  @Test
  public void addSalariedEmployee() {
    ase.execute();

    e = GpayrollDatabase.getEmployee(empId);
    assertEquals("Alix", e.getName());
    assertEquals("Arlinton", e.getAddress());

    PaymentClassification pc = e.getClassification();
    sc = (SalariedClassification) pc;
    assertEquals(Double.valueOf(200.00), Double.valueOf(sc.getSalary()));

    PaymentSchedule ps = e.getSchedule();
    MonthlySchedule ms = new MonthlySchedule();
    assertEquals(ps.getClass().getName(), ms.getClass().getName());

    PaymentMethod pm = e.getMethod();
    HoldMethod hm = new HoldMethod();
    assertEquals(pm.getClass().getName(), hm.getClass().getName());

  }

}
