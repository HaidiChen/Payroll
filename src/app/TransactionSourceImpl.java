package app;

import intfs.TransactionSource;
import intfs.Transaction;
import transactions.*;

public class TransactionSourceImpl implements TransactionSource {

    public Transaction getTransaction(String type, int empId, String name, 
            String address, double salary, double rate) {
        Transaction t = null;
        switch (type) {
            case "AddHourlyEmployee":
                t =  new AddHourlyEmployee(empId, name, address, rate);
        }
        return t;
    }
}
