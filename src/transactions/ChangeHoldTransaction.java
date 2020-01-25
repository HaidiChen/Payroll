package transactions;

import intfs.PaymentMethod;
import methods.HoldMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {

  private String address;
  
  public ChangeHoldTransaction(int empId, String address) {
    this.address = address;
    this.empId = empId;
  }

  protected PaymentMethod getMethod() {
    return new HoldMethod(address);
  }
}
