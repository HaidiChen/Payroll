package transactions;

import intfs.*;
import methods.HoldMethod;
import affiliations.NoAffiliation;
import global.*;

public abstract class AddEmployeeTransaction implements Transaction {

    protected int empId;
    protected String itsAddress;
    protected String itsName;

    public void execute() {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();
        Affiliation af = new NoAffiliation();
        Employee e = new Employee(empId, itsName, itsAddress);
        e.setClassification(pc);
        e.setMethod(pm);
        e.setSchedule(ps);
        e.setAffiliation(af);
        GpayrollDatabase.addEmployee(empId, e);
    }

    public abstract PaymentClassification getClassification();

    public abstract PaymentSchedule getSchedule();

}
