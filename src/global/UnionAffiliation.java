package global;

import intfs.Affiliation;
import java.util.HashMap;

public class UnionAffiliation implements Affiliation {

  private double hours;
  private HashMap<Long, ServiceCharge> itsCharges = new HashMap<>();

  public UnionAffiliation(double hours) {
    this.hours = hours;
  }

  public ServiceCharge getServiceCharge(long date) {
    return itsCharges.get(date);
  }

  public void addServiceCharge(ServiceCharge sc) {
    itsCharges.put(sc.getDate(), sc);
  }
}
