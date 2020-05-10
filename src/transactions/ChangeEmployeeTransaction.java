package transactions;

import intfs.Transaction;
import global.Employee;
import global.GpayrollDatabase;

public abstract class ChangeEmployeeTransaction implements Transaction {

    protected int empId;

    public void execute() {
        Employee e = GpayrollDatabase.getEmployee(empId);
        if (e != null) {
            change(e);
        }
    }

    public abstract void change(Employee e);
}
