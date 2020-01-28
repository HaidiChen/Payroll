package global;

import java.util.Calendar;

public class Date {

  private Calendar calendar;

  public Date(int year, int month, int date) {
    calendar = Calendar.getInstance();
    calendar.set(year, month - 1, date);
  }

  @Override
  public boolean equals(Object object) {
    Date otherDate = (Date) object;
    if (isSameDate(otherDate)) {
      return true;
    }
    return false;
  }

  private boolean isSameDate(Date otherDate) {
    if (otherDate.toString().equals(toString())) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) +
      "/" + calendar.get(Calendar.DATE);
  }

  public boolean isLastDayOfMonth() {
    int date = calendar.get(Calendar.DATE);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    Calendar newDate = Calendar.getInstance();
    newDate.set(year, month, date + 1);

    return month != newDate.get(Calendar.MONTH);
  }

  public boolean isEndOfWeek() {
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    if (dayOfWeek == 6) {
      return true;
    }
    return false;
  }
}
