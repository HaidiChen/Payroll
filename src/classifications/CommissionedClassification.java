package classifications;

import global.SalesReceipt;
import global.Paycheck;
import java.util.HashMap;
import intfs.PaymentClassification;

public class CommissionedClassification implements PaymentClassification {

  private double rate;
  private double salary;
  private HashMap<Long, SalesReceipt> itsReceipts = new HashMap<>();

  public CommissionedClassification(double salary, double rate) {
    this.salary = salary;
    this.rate = rate;
  }

  public double getSalary() {
    return salary;
  }

  public double getRate() {
    return rate;
  }

  public void addSalesReceipt(SalesReceipt sr) {
    itsReceipts.put(sr.getDate(), sr);
  }

  public SalesReceipt getSalesReceipt(long date) {
    return itsReceipts.get(date);
  }

  public double calculatePay(Paycheck pc) {
    return 0;
  }
  
}
