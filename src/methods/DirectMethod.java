package methods;

import intfs.PaymentMethod;
import global.Paycheck;

public class DirectMethod implements PaymentMethod {
  
  private String bank;
  private long account;

  public DirectMethod(String bank, long account) {
    this.bank = bank;
    this.account = account;
  }

  public String getBank() {
    return bank;
  }

  public long getAccount() {
    return account;
  }

  public void pay(Paycheck pc) {}

  public String getMethodName() {
    return "Direct";
  }
}
