package transactions;

import global.*;
import intfs.Transaction;
import java.util.ArrayList;
import java.util.HashMap;

public class PaydayTransaction implements Transaction {

  private Date payDate;
  private HashMap<Integer, Paycheck> paychecks = new HashMap<>();

  public PaydayTransaction(Date payDate) {
    this.payDate = payDate;
  }

  public void execute() {
    ArrayList<Integer> empIds = GpayrollDatabase.getAllEmployeeIds();
    for (Integer empId: empIds) {
      Employee e = GpayrollDatabase.getEmployee(empId);
      if (e != null) {
        if (e.isPayDate(payDate)) {
          Paycheck pc = new Paycheck(payDate);
          paychecks.put(empId, pc);
          e.payday(pc);
        }
        else {
          System.out.println("Not pay day");
        }
      }
      else {
        System.out.println("No such employee to pay");
      }
    }
  }

  public Paycheck getPaycheck(int empId) {
    return paychecks.get(empId);
  }
}
