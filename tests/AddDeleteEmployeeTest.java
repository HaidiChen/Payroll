import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertSame;
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

public class AddDeleteEmployeeTest {

    private int empId;
    private Employee e;

    private AddSalariedEmployee ase;
    private AddHourlyEmployee ahe;
    private AddCommissionedEmployee ace;

    private CommissionedClassification cc;
    private HourlyClassification hc;
    private SalariedClassification sc;

    @Before
    public void setUp() {
        ase = new AddSalariedEmployee(empId, "Alix", "Arlinton", 200.00);
        ahe = new AddHourlyEmployee(empId, "Berb", "Boston", 20.00);
        ace = new AddCommissionedEmployee(empId, "Clerk", "Croker", 2000.00, 20.00);
    }

    @Test
    public void deleteEmployee() {
        ace.execute();

        e = GpayrollDatabase.getEmployee(empId);
        assertEquals("Clerk", e.getName());

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();

        e = GpayrollDatabase.getEmployee(empId);
        assertNull(e);
    }

    @Test
    public void addCommissionedEmployee() {
        ace.execute();

        e = GpayrollDatabase.getEmployee(empId);
        assertEquals("Clerk", e.getName());
        assertEquals("Croker", e.getAddress());

        PaymentClassification pc = e.getClassification();
        cc = (CommissionedClassification) pc;
        assertEquals(Double.valueOf(cc.getRate()), Double.valueOf(20.00));
        assertEquals(Double.valueOf(cc.getSalary()), Double.valueOf(2000.00));

        PaymentSchedule ps = e.getSchedule();
        BiweeklySchedule ws = new BiweeklySchedule();
        assertEquals(ps.getClass().getName(), ws.getClass().getName());

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = new HoldMethod();
        assertEquals(pm.getClass().getName(), hm.getClass().getName());

    }

    @Test
    public void addHourlyEmployee() {
        ahe.execute();

        e = GpayrollDatabase.getEmployee(empId);
        assertEquals("Berb", e.getName());
        assertEquals("Boston", e.getAddress());

        PaymentClassification pc = e.getClassification();
        hc = (HourlyClassification) pc;
        assertEquals(Double.valueOf(hc.getRate()), Double.valueOf(20.00));

        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ws = new WeeklySchedule();
        assertEquals(ps.getClass().getName(), ws.getClass().getName());

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = new HoldMethod();
        assertEquals(pm.getClass().getName(), hm.getClass().getName());

    }

    @Test
    public void addSalariedEmployee() {
        ase.execute();

        e = GpayrollDatabase.getEmployee(empId);
        assertEquals("Alix", e.getName());
        assertEquals("Arlinton", e.getAddress());

        PaymentClassification pc = e.getClassification();
        sc = (SalariedClassification) pc;
        assertEquals(Double.valueOf(200.00), Double.valueOf(sc.getSalary()));

        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = new MonthlySchedule();
        assertEquals(ps.getClass().getName(), ms.getClass().getName());

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = new HoldMethod();
        assertEquals(pm.getClass().getName(), hm.getClass().getName());

    }

}
