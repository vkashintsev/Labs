package Maths;

import java.sql.Timestamp;
import java.util.Calendar;

public class Time {
    public static long getCurrentTime() {
        long currentTime=Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: "+currentTime);
        return currentTime;
    }
}
