package transactions;

import intfs.PaymentClassification;
import intfs.PaymentSchedule;
import classifications.HourlyClassification;
import schedules.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
  
  private double rate;

  public ChangeHourlyTransaction(int empId, double rate) {
    this.rate = rate;
    this.empId = empId;
  }

  protected PaymentClassification getClassification() {
    return new HourlyClassification(rate);
  }

  protected PaymentSchedule getSchedule() {
    return new WeeklySchedule();
  }
}
