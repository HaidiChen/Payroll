package transactions;

import classifications.CommissionedClassification;
import intfs.Transaction;
import global.*;

public class SalesReceiptTransaction implements Transaction {

  private long date;
  private double amount;
  private int empId;

  public SalesReceiptTransaction(long date, double amount, int empId) {
    this.date = date;
    this.amount = amount;
    this.empId = empId;
  }

  public void execute() {
    Employee e = GpayrollDatabase.getEmployee(empId);
    if (e != null) {
      try {
        CommissionedClassification hc = (CommissionedClassification) e.getClassification();
        SalesReceipt tc = new SalesReceipt(date, amount);
        hc.addSalesReceipt(tc);
      }
      catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    else {
      System.out.println("No such employee");
    }
  }

}
