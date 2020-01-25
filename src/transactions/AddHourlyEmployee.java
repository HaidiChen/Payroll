package transactions;

import intfs.PaymentSchedule;
import intfs.PaymentClassification;
import schedules.WeeklySchedule;
import classifications.HourlyClassification;

public class AddHourlyEmployee extends AddEmployeeTransaction {
  
  private double rate;

  public AddHourlyEmployee(int empId, String name, String address, double rate) {
    this.empId = empId;
    this.itsName = name;
    this.itsAddress = address;
    this.rate = rate;
  }

  public PaymentSchedule getSchedule() {
    return new WeeklySchedule();
  }

  public PaymentClassification getClassification() {
    return new HourlyClassification(rate);
  }

}
