package global;

public class Paycheck {

  private Date payDate;
  private double grossPay;
  private double netPay;

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
}
