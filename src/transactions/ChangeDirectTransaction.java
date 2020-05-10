package transactions;

import intfs.PaymentMethod;
import methods.DirectMethod;

public class ChangeDirectTransaction extends ChangeMethodTransaction {

    private String bank;
    private long account;

    public ChangeDirectTransaction(int empId, String bank, long account) {
        this.bank = bank;
        this.empId = empId;
        this.account = account;
    }

    protected PaymentMethod getMethod() {
        return new DirectMethod(bank, account);
    }
}
