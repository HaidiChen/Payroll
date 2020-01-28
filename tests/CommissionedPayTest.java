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

}
