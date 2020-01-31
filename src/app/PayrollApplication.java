package app;

import intfs.*;
import global.*;

public class PayrollApplication {
  
  public static void main(String[] args) {
    TransactionSource sc = new TransactionSourceImpl();
    String transactionType = "AddHourlyEmployee";
    int empId = 1;
    String name = "Bill";
    String address = "Boston";
    double rate = 20.0;
    double salary = 2000.0;
    Transaction t = 
      sc.getTransaction(transactionType, empId, name, address, salary, rate);
    t.execute();
    Employee e = GpayrollDatabase.getEmployee(empId);
    System.out.println(e.getName());
  }
}
