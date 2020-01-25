package global;

public class ServiceCharge {

  private double charge;
  private long date;

  public ServiceCharge(long date, double charge) {
    this.date = date;
    this.charge = charge;
  }

  public long getDate() {
    return date;
  }

  public double getAmount() {
    return charge;
  }
}
