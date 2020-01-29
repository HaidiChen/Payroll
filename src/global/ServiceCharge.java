package global;

public class ServiceCharge {

  private double charge;
  private Date date;

  public ServiceCharge(Date date, double charge) {
    this.date = date;
    this.charge = charge;
  }

  public Date getDate() {
    return date;
  }

  public double getAmount() {
    return charge;
  }
}
