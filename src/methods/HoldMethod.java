package methods;

import intfs.PaymentMethod;

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
}
