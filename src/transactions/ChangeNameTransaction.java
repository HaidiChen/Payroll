package transactions;

import global.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {

  private String newName;

  public ChangeNameTransaction(int empId, String newName) {
    this.empId = empId;
    this.newName = newName;
  }

  public void change(Employee e) {
    e.setName(newName);
  }
}
