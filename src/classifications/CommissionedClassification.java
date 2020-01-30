package classifications;

import global.*;
import java.util.HashMap;
import intfs.PaymentClassification;

public class CommissionedClassification implements PaymentClassification {

  private double rate;
  private double salary;
  private HashMap<Date, SalesReceipt> itsReceipts = new HashMap<>();

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

  public SalesReceipt getSalesReceipt(Date date) {
    return itsReceipts.get(date);
  }

  public double calculatePay(Paycheck pc) {
    Date payDate = pc.getPayDate();
    double totalPay = salary;
    for (Date date: itsReceipts.keySet()) {
      SalesReceipt sr = getSalesReceipt(date);
      if (isInSamePeriod(sr, payDate)) {
        totalPay += sr.getAmount() * rate;
      }
    }
    return totalPay;
  }

  private boolean isInSamePeriod(SalesReceipt sr, Date payDate) {
    Date salesReceiptDate = sr.getDate();
    Date periodStartDate = payDate.addDays(-12);
    Date periodEndDate = payDate;
    return salesReceiptDate.after(periodStartDate) && 
      (salesReceiptDate.before(periodEndDate) || 
       salesReceiptDate.equals(periodEndDate));
  }
  
}
