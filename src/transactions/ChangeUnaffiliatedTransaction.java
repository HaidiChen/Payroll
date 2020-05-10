package transactions;

import intfs.Affiliation;
import affiliations.NoAffiliation;
import affiliations.UnionAffiliation;
import global.Employee;
import global.GpayrollDatabase;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {

    public ChangeUnaffiliatedTransaction(int empId) {
        this.empId = empId;
    }

    protected Affiliation getAffiliation() {
        return new NoAffiliation();
    }

    protected void recordMembership(Employee e) {
        try {
            UnionAffiliation ua = (UnionAffiliation) e.getAffiliation();
            int memberId = ua.getMemberId();
            GpayrollDatabase.removeUnionMember(memberId);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
