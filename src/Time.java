import java.util.Date;

public class Time {
    static Date date = new Date();

    /**
     * @return a String containing the date in this format: [yyyy-mm-dd hh:mm:ss]
     */
    static public String getDate() {
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDate();
        int hour = date.getHours();
        int min = date.getMinutes();
        int sec = date.getSeconds();
        return String.format("[%02d-%02d-%02d %02d:%02d:%02d]", year, month, day, hour, min, sec);
    }
}
