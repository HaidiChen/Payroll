package global;

import java.util.Calendar;

public class Date {

  private static final int LAST_DAY_OF_WEEK = Calendar.FRIDAY;
  private static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY;
  private Calendar calendar;

  public Date(int year, int month, int date) {
    calendar = Calendar.getInstance();
    calendar.set(year, month - 1, date);
  }

  public int getYear() {
    return calendar.get(Calendar.YEAR);
  }

  public int getMonth() {
    return calendar.get(Calendar.MONTH) + 1;
  }

  public int getDate() {
    return calendar.get(Calendar.DATE);
  }

  private int getDayOfWeek() {
    return calendar.get(Calendar.DAY_OF_WEEK);
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
    return getYear() + "/" + getMonth() + "/" + getDate();
  }

  public boolean isLastDayOfMonth() {
    int date = getDate();
    int month = getMonth();
    int year = getYear();

    Date newDate = new Date(year, month, date + 1);
    return month != newDate.getMonth();
  }

  public boolean isEndOfWeek() {
    int dayOfWeek = getDayOfWeek();
    return dayOfWeek == LAST_DAY_OF_WEEK;
  }

  public Date addDays(int days) {
    return new Date(getYear(), getMonth(), getDate() + days);
  }

  public boolean after(Date when) {
    return calendar.after(when.calendar);
  }

  public boolean before(Date when) {
    return calendar.before(when.calendar);
  }

}
