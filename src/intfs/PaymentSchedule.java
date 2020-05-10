package intfs;

import global.Date;

public interface PaymentSchedule {
    public boolean isPayDate(Date payDate);
    public Date getPayPeriodStartDate(Date payPeriodEndDate);
}
