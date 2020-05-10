package transactions;

import intfs.PaymentClassification;
import intfs.PaymentSchedule;
import global.Employee;

public abstract class ChangeClassificationTransaction 
    extends ChangeEmployeeTransaction {

    public void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }
    protected abstract PaymentClassification getClassification();
    protected abstract PaymentSchedule getSchedule();
}
