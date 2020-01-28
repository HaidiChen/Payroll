package global;

import intfs.PaymentMethod;

public class Paycheck {

  private Date payDate;
  private double grossPay;
  private double netPay;
  private PaymentMethod pm;

  public Paycheck(Date payDate) {
    this.payDate = payDate;
  }

  public Date getPayDate() {
    return payDate;
  }

  public void setNetPay(double netPay) {
    this.netPay = netPay;
  }

  public void setGrossPay(double grossPay) {
    this.grossPay = grossPay;
  }

  public void setMethod(PaymentMethod pm) {
    this.pm = pm;
  }

  public double getNetPay() {
    return netPay;
  }

  public double getGrossPay() {
    return grossPay;
  }

  public double getDeductions() {
    return grossPay - netPay;
  }

  public String getField(String field) {
    return pm.getMethodName();
  }
}
