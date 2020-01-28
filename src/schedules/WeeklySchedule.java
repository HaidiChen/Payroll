package schedules;

import intfs.PaymentSchedule;
import global.Date;

public class WeeklySchedule implements PaymentSchedule {

  public boolean isPayDate(Date payDate) {
    return false;
  }
}
