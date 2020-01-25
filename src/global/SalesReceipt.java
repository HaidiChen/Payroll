package global;

public class SalesReceipt {

  private double amount;
  private long date;

  public SalesReceipt(long date, double amount) {
    this.date = date;
    this.amount = amount;
  }

  public long getDate() {
    return date;
  }

  public double getAmount() {
    return amount;
  }
}
