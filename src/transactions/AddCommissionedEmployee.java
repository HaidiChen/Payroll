package transactions;

import intfs.PaymentSchedule;
import intfs.PaymentClassification;
import schedules.BiweeklySchedule;
import classifications.CommissionedClassification;

public class AddCommissionedEmployee extends AddEmployeeTransaction {

    private double salary;
    private double rate;

    public AddCommissionedEmployee(
            int empId, String name, String address, double salary, double rate) {
        this.empId = empId;
        this.itsName = name;
        this.itsAddress = address;
        this.salary = salary;
        this.rate = rate;
            }

    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }

    public PaymentClassification getClassification() {
        return new CommissionedClassification(salary, rate);
    }
}
