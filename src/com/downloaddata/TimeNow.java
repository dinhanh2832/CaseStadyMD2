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

public class TimeNow extends Generic {
    private static final TimeNow downloadData = new TimeNow();
    private static final String TEMPERATURE_NOW = "<b>[+](.*?)</b>";
    private static final String TIME_NOW = "<div class=\"digi\" id=\"dig[1234]\">(.*?)</div>";
    private static final String DESCRIPTION = "<td class=\"t0\"><img src=\"(.*?)\" alt=\"(.*?)\"";
    private static final String WIND = ": <b>[0-9]+\s<i ";
    private static final String HUMIDITY = "đối: <b>(.*?)%</b>";
    private static final String AIR_PRESSURE = "khí: <b>(.*?) <i title=";
    private static String content = null;
    private static List<String> list = new ArrayList<>();

    public TimeNow() {
    }

    public static TimeNow getContent() {
        return downloadData;
    }

    @Override
    public String source() {
        try {
            URL url = new URL("https://vi.meteocast.net/extended-forecast/vn/ha-noi/");
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            content = content.replaceAll("\\n+", "");
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
        Pattern p1 = Pattern.compile(TIME_NOW);
        Pattern p2 = Pattern.compile(TEMPERATURE_NOW);
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
            String result = str.substring(28, 29);
            list.add(result);
        }
        System.out.println("Thời gian hiện tại là: " + list.get(0) + list.get(1) + ":" + list.get(2) + list.get(3));
        if (m2.find()) {
            String str = m2.group();
            String result = str.substring(4, 9);
            System.out.println("Nhiệt độ hiện tại là: " + result);
        }
        if (m3.find()) {
            String str = m3.group();
            String result = str.substring(41, str.length() - 1);
            System.out.println("Bầu trời: " + result);
        }
        if (m4.find()) {
            String str = m4.group();
            String result = str.substring(5, 6);
            System.out.println("Gió giật: " + result + " m/giây");
        }
        if (m5.find()) {
            String str = m5.group();
            String result = str.substring(8, 11);
            System.out.println("Độ ẩm tương đối: " + result);
        }
        if (m6.find()) {
            String str = m6.group();
            String result = str.substring(8, 12);
            System.out.println("Áp suất không khí: " + result + " hPa");
        }
    }
}
