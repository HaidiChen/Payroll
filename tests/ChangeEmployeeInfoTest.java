import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import intfs.*;
import schedules.*;
import transactions.*;
import global.*;
import methods.*;
import classifications.*;
import affiliations.*;

public class ChangeEmployeeInfoTest {

  private int empId;
  private Employee e;

  private AddSalariedEmployee ase;
  private AddHourlyEmployee ahe;
  private AddCommissionedEmployee ace;

  private CommissionedClassification cc;
  private HourlyClassification hc;
  private SalariedClassification sc;

  @Before
  public void setUp() {
    ase = new AddSalariedEmployee(empId, "Alix", "Arlinton", 200.00);
    ahe = new AddHourlyEmployee(empId, "Berb", "Boston", 20.00);
    ace = new AddCommissionedEmployee(empId, "Clerk", "Croker", 2000.00, 20.00);
  }
  
  @Test
  public void changeNoAffiliationTransaction() {
    ace.execute();
    ChangeUnaffiliatedTransaction cmt = new ChangeUnaffiliatedTransaction(empId);
    cmt.execute();
    e = GpayrollDatabase.getEmployee(empId);
    NoAffiliation ua = (NoAffiliation) e.getAffiliation();
    assertNotNull(ua);
  }

  @Test
  public void changeMemberTransaction() {
    int memberId = 7734;
    ahe.execute();
    ChangeMemberTransaction cmt = 
      new ChangeMemberTransaction(empId, memberId, 99.42);
    cmt.execute();
    e = GpayrollDatabase.getEmployee(empId);
    UnionAffiliation ua = (UnionAffiliation) e.getAffiliation();
    assertEquals(Double.valueOf(ua.getDues()), Double.valueOf(99.42));
    Employee member = GpayrollDatabase.getUnionMember(memberId);
    assertEquals(e, member);
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

}
