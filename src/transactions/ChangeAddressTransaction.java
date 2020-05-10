package transactions;

import global.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {

    private String newAddress;

    public ChangeAddressTransaction(int empId, String newAddress) {
        this.empId = empId;
        this.newAddress = newAddress;
    }

    public void change(Employee e) {
        e.setAddress(newAddress);
    }
}
