package com.downloaddata;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeOfDay extends Generic {
    private static final TimeOfDay timeOfDay = new TimeOfDay();
    private static final String WEEKDAYS = "class=\"md\">(.*?)</b><b class=\"ym\">(.*?)</b";
    private static final String TEMPERATURE = "class=\"q4\">[+](.*?)<i> °C";
    private static final String DESCRIPTION = "class=\"q3\">(.*?)</u>";
    private static final String WIND = "giật: </b>(.*?) <i ";
    private static final String HUMIDITY = "đối: </b>(.*?)%</p>";
    private static final String AIR_PRESSURE = "khí: </b>(.*?) <i";
    private static String content = null;
    private final List<String> listWeekdays = new ArrayList<>();
    private final List<String> listTemperature = new ArrayList<>();
    private final List<String> listDescription = new ArrayList<>();
    private final List<String> listWind = new ArrayList<>();
    private final List<String> listHumidity = new ArrayList<>();
    private final List<String> listAirPressure = new ArrayList<>();

    public TimeOfDay() {
    }

    public static TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public List<String> getListWeekdays() {
        return listWeekdays;
    }

    public List<String> getListTemperature() {
        return listTemperature;
    }

    public List<String> getListDescription() {
        return listDescription;
    }

    public List<String> getListWind() {
        return listWind;
    }

    public List<String> getListHumidity() {
        return listHumidity;
    }

    public List<String> getListAirPressure() {
        return listAirPressure;
    }

    @Override
    public String source() {
        try {
            URL url = new URL("https://vi.meteocast.net/3h-forecast/vn/ha-noi/");
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public void validate(String content) {
        content = content.replaceAll("\\n+", "");
        Pattern p1 = Pattern.compile(WEEKDAYS);
        Pattern p2 = Pattern.compile(TEMPERATURE);
        Pattern p3 = Pattern.compile(DESCRIPTION);
        Pattern p4 = Pattern.compile(WIND);
        Pattern p5 = Pattern.compile(HUMIDITY);
        Pattern p6 = Pattern.compile(AIR_PRESSURE);
        Matcher m1 = p1.matcher(content);
        Matcher m2 = p2.matcher(content);
        Matcher m3 = p3.matcher(content);
        Matcher m4 = p4.matcher(content);
        Matcher m5 = p5.matcher(content);
        Matcher m6 = p6.matcher(content);
        while (m1.find()) {
            String str = m1.group();
            String result1 = str.substring(11, 13);
            String result2 = str.substring(31, 39);
            String result3 = result1 + " " + result2;
            listWeekdays.add(result3);
        }
        while (m2.find()) {
            String str = m2.group();
            String result = str.substring(12, 14);
            listTemperature.add(result);
        }
        while (m3.find()) {
            String str = m3.group();
            String result = str.substring(11, str.length() - 4);
            listDescription.add(result);
        }
        while (m4.find()) {
            String str = m4.group();
            String result = str.substring(10, str.length() - 3);
            listWind.add(result);
        }
        while (m5.find()) {
            String str = m5.group();
            String result = str.substring(9, str.length() - 4);
            listHumidity.add(result);
        }
        while (m6.find()) {
            String str = m6.group();
            String result = str.substring(9, str.length() - 3);
            listAirPressure.add(result);
        }
    }
}
