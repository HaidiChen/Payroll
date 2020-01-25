package classifications;

import intfs.PaymentClassification;
import global.TimeCard;
import java.util.HashMap;

public class HourlyClassification implements PaymentClassification {

  private HashMap<Long, TimeCard> itsCards = new HashMap<>();
  private double rate;
  private TimeCard timeCard;

  public HourlyClassification(double rate) {
    this.rate = rate;
  }

  public double getRate() {
    return rate;
  }

  public TimeCard getTimeCard(long date) {
    return itsCards.get(date);
  }

  public void addTimeCard(TimeCard tc) {
    itsCards.put(tc.getDate(), tc);
  }
  
}
