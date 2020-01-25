package transactions;

import intfs.*;
import methods.HoldMethod;
import global.*;

public abstract class AddEmployeeTransaction implements Transaction {

  protected int empId;
  protected String itsAddress;
  protected String itsName;

  public void execute() {
    PaymentClassification pc = getClassification();
    PaymentSchedule ps = getSchedule();
    PaymentMethod pm = new HoldMethod();
    Employee e = new Employee(empId, itsName, itsAddress);
    e.setClassification(pc);
    e.setMethod(pm);
    e.setSchedule(ps);
    GpayrollDatabase.addEmployee(empId, e);
  }

  public abstract PaymentClassification getClassification();

  public abstract PaymentSchedule getSchedule();

}
