import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import intfs.*;
import schedules.*;
import transactions.*;
import global.*;
import methods.*;
import classifications.*;
import affiliations.*;

public class CommissionedPayTest {

    private int empId;
    private Employee e;

    private AddCommissionedEmployee ace;

    @Before
    public void setUp() {
        ace = new AddCommissionedEmployee(empId, "Clerk", "Croker", 2000.00, 20.00);
    }

    @Test
    public void paySingleEmployeeTwoSalesReciptSpanningTwoPayPeriods() {
        ace.execute();
        Date date1 = new Date(2001, 11, 9);
        Date date2 = new Date(2001, 11, 23);
        Date date3 = new Date(2001, 11, 29);
        SalesReceiptTransaction srt = new SalesReceiptTransaction(date1, 2.0, empId);
        srt.execute();
        srt = new SalesReceiptTransaction(date2, 3.0, empId);
        srt.execute();
        srt = new SalesReceiptTransaction(date3, 4.0, empId);
        srt.execute();
        Date payDate = new Date(2001, 11, 30);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 2000 + 7.0 * 20.00);
    }

    @Test
    public void paySingleEmployeeTwoSalesReceipt() {
        ace.execute();
        Date date1 = new Date(2001, 11, 13);
        Date date2 = new Date(2001, 11, 9);
        Date payDate = new Date(2001, 11, 16);
        SalesReceiptTransaction srt = new SalesReceiptTransaction(date1, 2.0, empId);
        srt.execute();
        srt = new SalesReceiptTransaction(date2, 4.0, empId);
        srt.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 2000.00 + 6.0 * 20.00);
    }

    @Test
    public void paySingleEmployeeOneSalesReceipt() {
        ace.execute();
        Date payDate = new Date(2001, 11, 16);
        Date date = new Date(2001, 11, 9);
        SalesReceiptTransaction srt = new SalesReceiptTransaction(date, 2.0, empId);
        srt.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 2000.00 + 2.0 * 20.00);
    }

    @Test
    public void paySingleEmployeeOnWrongDate() {
        ace.execute();
        Date payDate = new Date(2001, 11, 9);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertNull(pc);
    }

    @Test
    public void paySingleEmployeeNoSalesRecipt() {
        ace.execute();
        Date payDate = new Date(2001, 11, 16);

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validatePaycheck(pt, empId, payDate, 2000.00);
    }

    private void validatePaycheck(
            PaydayTransaction pt, int empId, Date payDate, double pay) {
        Paycheck pc = pt.getPaycheck(empId);
        assertEquals(pc.getPayDate(), payDate);
        assertEquals(Double.valueOf(pay), Double.valueOf(pc.getGrossPay()));
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(Double.valueOf(pc.getDeductions()), Double.valueOf(0.0));
        assertEquals(Double.valueOf(pc.getNetPay()), Double.valueOf(pay));
            }


}
