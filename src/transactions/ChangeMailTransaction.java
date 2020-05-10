package transactions;

import intfs.PaymentMethod;
import methods.MailMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {

    private String address;

    public ChangeMailTransaction(int empId, String address) {
        this.address = address;
        this.empId = empId;
    }

    protected PaymentMethod getMethod() {
        return new MailMethod(address);
    }
}
