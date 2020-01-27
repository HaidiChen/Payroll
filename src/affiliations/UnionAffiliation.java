package affiliations;

import intfs.Affiliation;
import java.util.HashMap;
import global.ServiceCharge;

public class UnionAffiliation implements Affiliation {

  private int memberId;
  private double dues;
  private HashMap<Long, ServiceCharge> itsCharges = new HashMap<>();

  public UnionAffiliation(int memberId, double dues) {
    this.memberId = memberId;
    this.dues = dues;
  }

  public ServiceCharge getServiceCharge(long date) {
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
}
