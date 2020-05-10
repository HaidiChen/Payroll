package transactions;

import intfs.PaymentMethod;
import global.Employee;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public void change(Employee e) {
        e.setMethod(getMethod());
    }
    protected abstract PaymentMethod getMethod();
}
