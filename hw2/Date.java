/* Date.java */

import java.io.*;
import java.lang.*;
class Date {

  /* Put your private data fields here. */

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */

    private int day;
    private int month;
    private int year;

    private void init(int month, int day, int year) {
	if (isValidDate(month, day, year)) {
	  this.month = month;
	  this.day = day;
	  this.year = year;
	}
	else {
	    System.out.println(" Invalid Date Input!!");
	    System.exit(0);
	}
    }
  public Date(int month, int day, int year) {
      init(month, day, year);
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
      String[] mdy = s.split("/");
      if (mdy.length != 3) {
	  System.out.println("invalid data string");
	  System.exit(0);
	  
      }
      int month = Integer.parseInt(mdy[0]);
      int day = Integer.parseInt(mdy[1]);
      int year = Integer.parseInt(mdy[2]);
      init(month, day, year);
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
      if (year % 4 == 0) {
	  if (year % 100 == 0) {
	      if (year % 400 == 0) {
		  return true;
	      }
	      else return false;
	  }
	  else return true;
      }
      else return false;
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
      int[] thirtyOne = {1, 3, 5, 7, 8, 10, 12};
      for ( int x : thirtyOne) {
	  if (x == month) return 31;
      } 
      if (month == 2) {
	  if (isLeapYear(year)) return 29;
	  else return 28;
      }
      return 30;
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
      return 0 < month && month < 13 && 0 < day && day < 32 && year > 0; 
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
      return this.month + "/" + this.day + "/" + this.year;
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
      if (year < d.year) return true;
      else if (year == d.year && month < d.month) return true;
      else if (year == d.year && month == d.month && day < d.day) return true;
      else return false;
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
      if (year == d.year && month == d.month && day == d.day) return false;
      else if (isBefore(d)) return false;
      else return true;
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
      int days = 0;
      days += this.day;
      for (int i = 1; i < this.month; i++){
	  days += daysInMonth(i, year);
      }
      return days;
  }


    private int daysSinceZero() {
	int days = 0;
	for (int i = 0; i < this.year; i++) {
	    days += (isLeapYear(i) ? 366 : 365);
	}
	days += this.dayInYear();
	return days;
    }

    /** Determines the difference in days between d and this Date.  For example,
     *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */

  public int difference(Date d) {
      return this.daysSinceZero() - d.daysSinceZero();
  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting isLeapYear, isValidDate and daysInMonth");
    System.out.println("year 2000 isLeapYear: " + isLeapYear(2000));
    System.out.println("year 1900 isLeapYear: " + isLeapYear(1900));
    System.out.println("year 1 isLeapYear: " + isLeapYear(1));
    System.out.println("2/13/1991 isValidDate: " + isValidDate(2, 13, 1991));
    System.out.println("1/1/1 isValidDate: " + isValidDate(1,1,1));
    System.out.println("20/13/1" + isValidDate(20,13,1));
    System.out.println("March: " + daysInMonth(3,100));
    System.out.println("Feb: " + daysInMonth(2,1991));
    System.out.println("Dec: "+ daysInMonth(12, 2000));
    System.out.println("Nov: " + daysInMonth(11,1899));

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting dayInYear.");
    System.out.println("12/31/1975 should be 365: " + d1.dayInYear());
    System.out.println("1/1/1976 should be 1: " + d2.dayInYear());
    Date dd = new Date(12, 31, 2000);
    System.out.println("12/31/2000 should be 366: " + dd.dayInYear());
    
    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
