package global;

import intfs.*;

public class Employee {

  private int empId;
  private String name;
  private String address;
  private PaymentClassification pc;
  private PaymentMethod pm;
  private PaymentSchedule ps;
  private Affiliation af;

  public Employee(int empId, String name, String address) {
    this.empId = empId;
    this.name = name;
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public String getName() {
    return name;
  }

  public PaymentClassification getClassification() {
    return pc;
  }

  public PaymentSchedule getSchedule() {
    return ps;
  }

  public PaymentMethod getMethod() {
    return pm;
  }

  public Affiliation getAffiliation() {
    return af;
  }

  public void setSchedule(PaymentSchedule ps) {
    this.ps = ps;
  }

  public void setMethod(PaymentMethod pm) {
    this.pm = pm;
  }

  public void setClassification(PaymentClassification pc) {
    this.pc = pc;
  }

  public void setAffiliation(Affiliation af) {
    this.af = af;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isPayDate(Date payDate) {
    return ps.isPayDate(payDate);
  }

  public void payday(Paycheck paycheck) {
    double grossPay = pc.calculatePay(paycheck);
    double deductions = af.calculateDeductions(paycheck);
    double netPay = grossPay - deductions;
    paycheck.setGrossPay(grossPay);
    paycheck.setNetPay(netPay);
    pm.pay(paycheck);
  }

}
