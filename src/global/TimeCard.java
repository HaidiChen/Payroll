package global;

public class TimeCard {

  private double hours;
  private long date;

  public TimeCard(long date, double hours) {
    this.date = date;
    this.hours = hours;
  }

  public long getDate() {
    return date;
  }

  public double getHours() {
    return hours;
  }
}
