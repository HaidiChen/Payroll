package intfs;

public interface TransactionSource {
  public Transaction getTransaction(String type,
      int empId, String name, String address, double salary, double rate);
}
