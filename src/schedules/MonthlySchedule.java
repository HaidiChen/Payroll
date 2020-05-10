package schedules;

import intfs.PaymentSchedule;
import global.Date;

public class MonthlySchedule implements PaymentSchedule {

    public boolean isPayDate(Date payDate) {
        return payDate.isLastDayOfMonth();
    }

    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        int dayOfMonth = payPeriodEndDate.getDayOfMonth();
        return payPeriodEndDate.addDays(-dayOfMonth);
    }

}
