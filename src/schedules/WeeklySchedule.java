package schedules;

import intfs.PaymentSchedule;
import global.Date;

public class WeeklySchedule implements PaymentSchedule {

  public boolean isPayDate(Date payDate) {
    return payDate.isEndOfWeek();
  }

  public Date getPayPeriodStartDate(Date payPeriodEndDate) {
    return payPeriodEndDate.addDays(-7);
  }
}
