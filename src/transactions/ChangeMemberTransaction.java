package transactions;

import intfs.Affiliation;
import affiliations.UnionAffiliation;
import global.Employee;
import global.GpayrollDatabase;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

  private int memberId;
  private double dues;
  
  public ChangeMemberTransaction(int empId, int memberId, double dues) {
    this.memberId = memberId;
    this.empId = empId;
    this.dues = dues;
  }

  protected Affiliation getAffiliation() {
    return new UnionAffiliation(memberId, dues);
  }

  protected void recordMembership(Employee e) {
    GpayrollDatabase.addUnionMember(memberId, e);
  }
}
