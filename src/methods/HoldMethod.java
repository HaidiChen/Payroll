package methods;

import intfs.PaymentMethod;
import global.Paycheck;

public class HoldMethod implements PaymentMethod {

  private String address;

  public HoldMethod() {}

  public HoldMethod(String address) {
    this.address = address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void pay(Paycheck pc) {}

  public String getMethodName() {
    return "Hold";
  }
}
