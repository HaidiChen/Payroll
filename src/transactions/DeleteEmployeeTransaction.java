package transactions;

import intfs.Transaction;
import global.GpayrollDatabase;

public class DeleteEmployeeTransaction implements Transaction {
  
  private int empId;

  public DeleteEmployeeTransaction(int empId) {
    this.empId = empId;
  }

  public void execute() {
    GpayrollDatabase.deleteEmployee(empId);
  }
}
