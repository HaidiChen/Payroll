package transactions;

import classifications.CommissionedClassification;
import schedules.BiweeklySchedule;
import intfs.PaymentClassification;
import intfs.PaymentSchedule;

public class ChangeCommissionedTransaction 
    extends ChangeClassificationTransaction {

    private double salary;
    private double rate;

    public ChangeCommissionedTransaction(int empId, double salary, double rate) {
        this.empId = empId;
        this.salary = salary;
        this.rate = rate;
    }

    protected PaymentClassification getClassification() {
        return new CommissionedClassification(salary, rate);
    }

    protected PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}
