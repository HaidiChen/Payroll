package methods;

import intfs.PaymentMethod;

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
}
