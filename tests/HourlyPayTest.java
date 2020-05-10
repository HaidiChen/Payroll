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

public class HourlyPayTest {

    private int empId;
    private Employee e;

    private AddHourlyEmployee ahe;

    @Before
    public void setUp() {
        ahe = new AddHourlyEmployee(empId, "Berb", "Boston", 15.25);
    }

    @Test
    public void paySingleEmployeeTimeCardOnWeekend() {
        ahe.execute();
        Date payDate = new Date(2001, 11, 9);
        Date weekendDate1 = new Date(2001, 11, 3);
        Date weekendDate2 = new Date(2001, 11, 4);
        TimeCardTransaction tct = new TimeCardTransaction(weekendDate1, 2.0, empId);
        tct.execute();
        tct = new TimeCardTransaction(weekendDate2, 4.0, empId);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validateHourlyPaycheck(pt, empId, payDate, 6.0 * 15.25 * 1.5);
    }

    @Test
    public void paySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() {
        ahe.execute();
        Date payDate = new Date(2001, 11, 9);
        Date dateInPreviousPayPeriod = new Date(2001, 11, 2);

        TimeCardTransaction tct = new TimeCardTransaction(payDate, 2.0, empId);
        tct.execute();
        TimeCardTransaction t2 = 
            new TimeCardTransaction(dateInPreviousPayPeriod, 5.0, empId);
        t2.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validateHourlyPaycheck(pt, empId, payDate, 2 * 15.25);
    }

    @Test
    public void paySingleHourlyEmployeeTwoTimeCards() {
        ahe.execute();
        Date payDate = new Date(2001, 11, 9);
        TimeCardTransaction tct = new TimeCardTransaction(payDate, 2.0, empId);
        tct.execute();
        tct = new TimeCardTransaction(new Date(2001, 11, 8), 5.0, empId);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validateHourlyPaycheck(pt, empId, payDate, 7 * 15.25);
    }

    @Test
    public void paySingleHourlyEmployeeOnWrongDate() {
        ahe.execute();
        Date payDate = new Date(2001, 11, 8);
        TimeCardTransaction tct = new TimeCardTransaction(payDate, 9.0, empId);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        Paycheck pc = pt.getPaycheck(empId);
        assertNull(pc);
    }

    @Test
    public void paySingleHourlyEmployeeOvertimeOneTimeCard() {
        ahe.execute();
        Date payDate = new Date(2001, 11, 9);

        TimeCardTransaction tct = new TimeCardTransaction(payDate, 9.0, empId);
        tct.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validateHourlyPaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
    }

    @Test
    public void paySingleHourlyEmployeeOneTimeCard() {
        ahe.execute();
        Date payDate = new Date(2001, 11, 9);

        TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId);
        tc.execute();
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validateHourlyPaycheck(pt, empId, payDate, 30.5);
    }

    @Test
    public void paySingleHourlyEmployeeWithNoTimeCards() {
        ahe.execute();
        Date payDate = new Date(2001, 11, 9);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();
        validateHourlyPaycheck(pt, empId, payDate, 0.0);
    }

    private void validateHourlyPaycheck(
            PaydayTransaction pt, int empId, Date payDate, double pay) {
        Paycheck pc = pt.getPaycheck(empId);
        assertEquals(pc.getPayDate(), payDate);
        assertEquals(Double.valueOf(pay), Double.valueOf(pc.getGrossPay()));
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(Double.valueOf(pc.getDeductions()), Double.valueOf(0.0));
        assertEquals(Double.valueOf(pc.getNetPay()), Double.valueOf(pay));
            }

}
