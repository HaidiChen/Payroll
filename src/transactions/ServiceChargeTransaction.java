package transactions;

import classifications.CommissionedClassification;
import intfs.Transaction;
import global.*;
import affiliations.UnionAffiliation;

public class ServiceChargeTransaction implements Transaction {

  private long date;
  private double charge;
  private int memberId;

  public ServiceChargeTransaction(int memberId, long date, double charge) {
    this.date = date;
    this.charge = charge;
    this.memberId = memberId;
  }

  public void execute() {
    Employee e = GpayrollDatabase.getUnionMember(memberId);
    if (e != null) {
      try {
        UnionAffiliation ua = (UnionAffiliation) e.getAffiliation();
        ServiceCharge sc = new ServiceCharge(date, charge);
        ua.addServiceCharge(sc);
      }
      catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    else {
      System.out.println("No such employee");
    }
  }

}
