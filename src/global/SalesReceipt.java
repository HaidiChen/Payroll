package global;

public class SalesReceipt {

    private double amount;
    private Date date;

    public SalesReceipt(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
