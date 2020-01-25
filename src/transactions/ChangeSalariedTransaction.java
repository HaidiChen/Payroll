package transactions;

import classifications.SalariedClassification;
import schedules.MonthlySchedule;
import intfs.PaymentClassification;
import intfs.PaymentSchedule;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {

  private double salary;

  public ChangeSalariedTransaction(int empId, double salary) {
    this.empId = empId;
    this.salary = salary;
  }

  protected PaymentClassification getClassification() {
    return new SalariedClassification(salary);
  }

  protected PaymentSchedule getSchedule() {
    return new MonthlySchedule();
  }
}
