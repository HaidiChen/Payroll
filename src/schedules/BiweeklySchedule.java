package schedules;

import intfs.PaymentSchedule;
import global.Date;

public class BiweeklySchedule implements PaymentSchedule {

  public boolean isPayDate(Date payDate) {
    return false;
  }

}
