package classifications;

import intfs.PaymentClassification;
import global.*;
import java.util.HashMap;

public class HourlyClassification implements PaymentClassification {

  private HashMap<Date, TimeCard> itsCards = new HashMap<>();
  private double rate;

  public HourlyClassification(double rate) {
    this.rate = rate;
  }

  public double getRate() {
    return rate;
  }

  public TimeCard getTimeCard(Date date) {
    return itsCards.get(date);
  }

  public void addTimeCard(TimeCard tc) {
    itsCards.put(tc.getDate(), tc);
  }

  public double calculatePay(Paycheck pc) {
    double totalRate = 0;
    Date payPeriod = pc.getPayDate();

    for (Date date: itsCards.keySet()) {
      TimeCard tc = getTimeCard(date);
      if (isInPayPeriod(tc, payPeriod)) {
        double hours = tc.getHours();
        if (hours > 8) {
          double extraHours = hours - 8;
          extraHours *= 1.5;
          totalRate += (8 + extraHours) * rate;
        }
        else {
          totalRate += hours * rate;
        }
      }
    }
    return totalRate;
  }

  private boolean isInPayPeriod(TimeCard tc, Date payPeriod) {
    Date periodStartDate = payPeriod.addDays(-5);
    Date periodEndDate = payPeriod;
    Date timeCardDate = tc.getDate();
    return timeCardDate.after(periodStartDate) && 
      (timeCardDate.before(periodEndDate) || timeCardDate.equals(periodEndDate));
  }
  
}
