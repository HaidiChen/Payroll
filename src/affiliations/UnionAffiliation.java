package affiliations;

import intfs.Affiliation;
import java.util.HashMap;
import global.*;

public class UnionAffiliation implements Affiliation {

  private int memberId;
  private double dues;
  private HashMap<Date, ServiceCharge> itsCharges = new HashMap<>();

  public UnionAffiliation(int memberId, double dues) {
    this.memberId = memberId;
    this.dues = dues;
  }

  public ServiceCharge getServiceCharge(Date date) {
    return itsCharges.get(date);
  }

  public void addServiceCharge(ServiceCharge sc) {
    itsCharges.put(sc.getDate(), sc);
  }

  public double getDues() {
    return dues;
  }

  public int getMemberId() {
    return memberId;
  }

  public double calculateDeductions(Paycheck pc) {
    return 0;
  }
}
