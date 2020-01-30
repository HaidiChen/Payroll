package schedules;

import intfs.PaymentSchedule;
import global.Date;

public class BiweeklySchedule implements PaymentSchedule {

  public boolean isPayDate(Date payDate) {
    return payDate.isEndOfSecondWeek();
  }

  public Date getPayPeriodStartDate(Date payPeriodEndDate) {
    return payPeriodEndDate.addDays(-12);
  }

}
