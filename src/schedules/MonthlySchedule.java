package schedules;

import intfs.PaymentSchedule;
import global.Date;

public class MonthlySchedule implements PaymentSchedule {

  public boolean isPayDate(Date payDate) {
    return payDate.isLastDayOfMonth();
  }

}
