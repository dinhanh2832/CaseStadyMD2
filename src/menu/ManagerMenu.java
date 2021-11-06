package menu;

import com.downloaddata.TimeOfDay;
import sirvice.ManagerDay;

import java.util.Scanner;

public class ManagerMenu {
    public void showMenu() {
        System.out.println("--------------------------");
        System.out.println("Menu");
        System.out.println("1. Thời tiết hiện tại: ");
        System.out.println("2. Thời tiết ngày cần xem: ");
        System.out.println("3. Thời tiết 3 ngày tới: ");
        System.out.println("0. Exit");
        System.out.println("--------------------------");
        System.out.println("Enter your choice: ");
    }

    public ManagerDay returnTimeOfDay() {
        TimeOfDay.getTimeOfDay().validate(TimeOfDay.getTimeOfDay().source());
        return new ManagerDay();
    }

    public void returnWeatherNow() {
        returnTimeOfDay().getNow();
    }

    public void returnWeatherInWeek(String str) {
        returnTimeOfDay().getDayInWeek(str);
    }

    public void returnWeatherThreeDay() {
        returnTimeOfDay().getThreeNextDay();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while (true) {
            showMenu();
            while (choice == -1) {
                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Hãy nhập vào số: ");
                    sc.nextLine();
                }
            }
            switch (choice) {
                case 1 -> returnWeatherNow();
                case 2 -> {
                    System.out.println("Nhập vào ngày(bao gồm cả số 0)");
                    sc.nextLine();
                    String day = sc.nextLine();
                    System.out.println("Nhập vào tháng(bao gồm cả số 0)");
                    int month = sc.nextInt();
                    String month1 = String.valueOf(month);
                    String dayMonth = day + " " + "Tháng" + " " + month1;
                    System.out.println("Ngày " + dayMonth);
                    returnWeatherInWeek(dayMonth);
                }
                case 3 -> returnWeatherThreeDay();
                case 0 -> System.exit(0);
                default -> System.out.println("No choice!");
            }
            choice = -1;
        }
    }
}
