package intfs;

import global.Paycheck;

public interface PaymentMethod {
    public void pay(Paycheck pc);
    public String getMethodName();
}
