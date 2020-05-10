package classifications;

import intfs.PaymentClassification;
import global.Paycheck;

public class SalariedClassification implements PaymentClassification {

    private double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public double calculatePay(Paycheck pc) {
        return salary;
    }
}
