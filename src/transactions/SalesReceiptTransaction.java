package transactions;

import classifications.CommissionedClassification;
import intfs.Transaction;
import global.*;

public class SalesReceiptTransaction implements Transaction {

    private Date date;
    private double amount;
    private int empId;

    public SalesReceiptTransaction(Date date, double amount, int empId) {
        this.date = date;
        this.amount = amount;
        this.empId = empId;
    }

    public void execute() {
        Employee e = GpayrollDatabase.getEmployee(empId);
        if (e != null) {
            try {
                CommissionedClassification cc = 
                    (CommissionedClassification) e.getClassification();
                SalesReceipt sr = new SalesReceipt(date, amount);
                cc.addSalesReceipt(sr);
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
