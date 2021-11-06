package model;

public class Hour {
    private int hour;
    private String temperature;
    private String description;
//    private String wind;
    private String humidity;
    private String airPressure;

//    public Day(String temperature, String description, String wind, String humidity, String airPressure) {
//        this.temperature = temperature;
//        this.description = description;
//        this.wind = wind;
//        this.humidity = humidity;
//        this.airPressure = airPressure;
//    }

    public Hour(int hour, String temperature, String description, String humidity, String airPressure) {
        this.hour = hour;
        this.temperature = temperature;
        this.description = description;
        this.humidity = humidity;
        this.airPressure = airPressure;
    }

    public Hour() {
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getWind() {
//        return wind;
//    }

//    public void setWind(String wind) {
//        this.wind = wind;
//    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(String airPressure) {
        this.airPressure = airPressure;
    }

    @Override
    public String toString() {
        return String.format("|%-3d|%-12s|%-25s|%-7s|%-20s|",
               hour,"    "+temperature+"°C","  "+description,"  "+humidity,"      "+airPressure +" hPa");
    }
    public static void printLine(int space) {
        for (int i = 0; i <= space; i++) {
            if (i == space) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }
        }
    }
    public static void printFullLine() {
        System.out.print("+");
        printLine(3);
        printLine(12);
        printLine(25);
        printLine(7);
        printLine(20);
        System.out.println();
    }
}
