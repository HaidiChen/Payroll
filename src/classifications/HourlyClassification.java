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
        double totalPay = 0;
        for (Date date: itsCards.keySet()) {
            TimeCard tc = getTimeCard(date);
            Date timeCardDate = tc.getDate();
            if (timeCardDate.isInPayPeriod(pc)) {
                double hours = tc.getHours();
                if (hours > 8) {
                    double extraHours = hours - 8;
                    extraHours *= 1.5;
                    totalPay += (8 + extraHours) * rate;
                }
                else if (timeCardDate.isWeekend()) {
                    totalPay += hours * 1.5 * rate;
                }
                else {
                    totalPay += hours * rate;
                }
            }
        }
        return totalPay;
    }

}
