package affiliations;

import intfs.Affiliation;
import java.util.HashMap;
import global.*;

public class UnionAffiliation implements Affiliation {

    private int memberId;
    private double dues;
    private HashMap<Date, ServiceCharge> itsCharges = new HashMap<>();

    public UnionAffiliation(int memberId, double dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    public ServiceCharge getServiceCharge(Date date) {
        return itsCharges.get(date);
    }

    public void addServiceCharge(ServiceCharge sc) {
        itsCharges.put(sc.getDate(), sc);
    }

    public double getDues() {
        return dues;
    }

    public int getMemberId() {
        return memberId;
    }

    public double calculateDeductions(Paycheck pc) {
        double totalDues = 0;
        int fridays = 
            numberOfFridaysInPayPeriod(pc.getPayPeriodStartDate(), pc.getPayDate());
        totalDues = dues * fridays;
        for (Date date: itsCharges.keySet()) {
            ServiceCharge sc = getServiceCharge(date);
            Date serviceChargeDate = sc.getDate();
            if (serviceChargeDate.isInPayPeriod(pc)) {
                double charges = sc.getCharge();
                totalDues += charges;
            }
        }
        return totalDues;
    }

    private int numberOfFridaysInPayPeriod(Date startDate, Date endDate) {
        int fridays = 0;
        for (Date date = startDate.addDays(1);
                date.before(endDate) || date.equals(endDate); 
                date = date.addDays(1)) {
            if (date.getDayOfWeek() == Date.FRIDAY) {
                fridays += 1;
            }
                }
        return fridays;
    }
}
