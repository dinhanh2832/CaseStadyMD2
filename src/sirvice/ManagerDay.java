package sirvice;

import com.downloaddata.TimeNow;
import com.downloaddata.TimeOfDay;
import model.Hour;

import java.util.*;

public class ManagerDay implements Manager {
    private Map<String, ArrayList<Hour>> listDay = new HashMap<>();

    public ManagerDay() {
    }

    public ArrayList<Hour> addInformation(int j) {
        ArrayList<Hour> listHour = new ArrayList<>();
        for (int i = 0; i < 24; i += 3) {
            String temperature = TimeOfDay.getTimeOfDay().getListTemperature().get((i) / 3 + j * 8);
            String description = TimeOfDay.getTimeOfDay().getListDescription().get((i) / 3 + j * 9);
            String humidity = TimeOfDay.getTimeOfDay().getListHumidity().get((i) / 3 + j * 8);
            String airPressure = TimeOfDay.getTimeOfDay().getListAirPressure().get((i) / 3 + j * 8);
            listHour.add(new Hour(i, temperature, description, humidity, airPressure));
        }
        return listHour;
    }

    public Map<String, ArrayList<Hour>> addDay() {
        for (int j = 0; j < 11; j++) {
            listDay.put(TimeOfDay.getTimeOfDay().getListWeekdays().get(j), addInformation(j));
        }
        return listDay;
    }

    public void get() {
        for (int i = 0; i < 11; i++) {
            System.out.println(TimeOfDay.getTimeOfDay().getListWeekdays().get(i));
        }
    }

    @Override
    public void getNow() {
        TimeNow.getContent().validate(TimeNow.getContent().source());
    }

    @Override
    public void getDayInWeek(String weekday) {
        System.out.printf("|%-3s|%-12s|%-25s|%-7s|%-20s|%n",
                "Giờ", "  Nhiệt độ", "        Bầu trời", " Độ ẩm", "  Áp suất không khí");
        Hour.printFullLine();
        for (Hour hour : addDay().get(weekday)) {
            System.out.println(hour);
            Hour.printFullLine();
        }
    }

    public void getThreeNextDay() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Ngày " + TimeOfDay.getTimeOfDay().getListWeekdays().get(i));
            System.out.printf("|%-3s|%-12s|%-25s|%-7s|%-20s|%n",
                    "Giờ", "  Nhiệt độ", "        Bầu trời", " Độ ẩm", "  Áp suất không khí");
            Hour.printFullLine();
            for (Hour hour : addDay().get(TimeOfDay.getTimeOfDay().getListWeekdays().get(i))) {
                System.out.println(hour);
                Hour.printFullLine();
            }
        }
    }
}
