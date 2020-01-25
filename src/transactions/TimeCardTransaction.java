package transactions;

import classifications.HourlyClassification;
import intfs.Transaction;
import global.*;

public class TimeCardTransaction implements Transaction {

  private long date;
  private double hours;
  private int empId;

  public TimeCardTransaction(long date, double hours, int empId) {
    this.date = date;
    this.hours = hours;
    this.empId = empId;
  }

  public void execute() {
    Employee e = GpayrollDatabase.getEmployee(empId);
    if (e != null) {
      try {
        HourlyClassification hc = (HourlyClassification) e.getClassification();
        TimeCard tc = new TimeCard(date, hours);
        hc.addTimeCard(tc);
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
