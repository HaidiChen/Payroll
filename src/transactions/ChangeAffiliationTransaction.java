package transactions;

import intfs.Affiliation;
import global.Employee;

public abstract class ChangeAffiliationTransaction 
    extends ChangeEmployeeTransaction {

    public void change(Employee e) {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }
    protected abstract Affiliation getAffiliation();
    protected abstract void recordMembership(Employee e);
}
