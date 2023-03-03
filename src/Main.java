
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
  final public static String SEP = ",";

  enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER,
  }

  enum Season {
    WINTER,
    SPRING,
    SUMMER,
    FALL,
  }

  public static Map<Month, Integer> buildMonthsMap(File inputFile) throws IOException {
    Map<Month, Integer> months = new HashMap<>();
    BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile));

    months.put(Month.JANUARY, 0);
    months.put(Month.FEBRUARY, 0);
    months.put(Month.MARCH, 0);
    months.put(Month.APRIL, 0);
    months.put(Month.MAY, 0);
    months.put(Month.JUNE, 0);
    months.put(Month.JULY, 0);
    months.put(Month.AUGUST, 0);
    months.put(Month.SEPTEMBER, 0);
    months.put(Month.OCTOBER, 0);
    months.put(Month.NOVEMBER, 0);
    months.put(Month.DECEMBER, 0);

    for (String row = inputFileReader.readLine(); row != null; row = inputFileReader.readLine()) {
      int sepPoz = row.indexOf(SEP);
      String month = row.substring(0, sepPoz);
      month = month.toUpperCase();
      int days = Integer.parseInt(row.substring(sepPoz + 1));
      if (months.containsKey(Month.valueOf(month))) {
        months.put(Month.valueOf(month), days);
      }
    }
    inputFileReader.close();
    return months;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Enter the month: ");
    String month = inputReader.readLine();
    Month monthToLook = Month.valueOf(month.toUpperCase()); // месяц для поиска в словаре
    Map<Month, Integer> monthDays = buildMonthsMap(new File("res/months.csv"));
    if (monthDays.containsKey(monthToLook)) {
      System.out.printf("The month '%s' contains %d days%n", month, monthDays.get(monthToLook));
      System.out.printf("The month '%s' has ordinal number %d%n", month, monthToLook.ordinal() + 1);
      int nextMonthIndex = (monthToLook.ordinal() + 1);
      Month nextMonth = Month.values()[nextMonthIndex % Month.values().length];
      System.out.printf("The next month is '%s'%n", nextMonth);
      System.out.printf("It has %d days", monthDays.get(nextMonth));
      System.out.println();


      if (12 == nextMonthIndex || nextMonthIndex <= 2) {
        System.out.println("The period is: " + Season.WINTER);
      } else if (nextMonthIndex <= 5) {
        System.out.println("The period is: " + Season.SPRING);
      } else if (nextMonthIndex <= 8) {
        System.out.println("The period is: " + Season.SUMMER);
      } else {
        System.out.println("The period is: " + Season.FALL);

      }
    } else {
      System.out.println("No such month: " + month); // Нет такого месяца
    }
  }
}