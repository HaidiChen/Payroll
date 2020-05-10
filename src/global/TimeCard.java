package global;

public class TimeCard {

    private double hours;
    private Date date;

    public TimeCard(Date date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public Date getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }
}
