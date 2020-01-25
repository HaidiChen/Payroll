package transactions;

import intfs.PaymentSchedule;
import intfs.PaymentClassification;
import schedules.MonthlySchedule;
import classifications.SalariedClassification;

public class AddSalariedEmployee extends AddEmployeeTransaction {

  private double itsSalary;
  
  public AddSalariedEmployee(
      int empId, String name, String address, double salary) {
    itsSalary = salary;
    this.empId = empId;
    this.itsAddress = address;
    this.itsName = name;
  }

  public PaymentClassification getClassification() {
    return new SalariedClassification(itsSalary);
  }

  public PaymentSchedule getSchedule() {
    return new MonthlySchedule();
  }

}
