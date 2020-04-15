package vn.cns.covid19.Utils;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by Ho Dong Trieu on 11/14/2018
 */

public class DateUtils {

    public static String dateToString(Date date, String format) {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String GetCurrentDateTime (String format) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Saigon");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat destFormat = new SimpleDateFormat(format);
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        destFormat.setTimeZone(tz);
        return destFormat.format(new Date());
    }

    public static Date getBeginningOfDay() {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);

        return cal.getTime();
    }

    private static int numberOfDays() {
        return 1;
    }

    public static int numberOfMinutes() {
        return numberOfDays() * 24 * 60;
    }

    public static int millisecondsToMinutes(long milliseconds) {
        return (int) (milliseconds / (1000 * 60));
    }
}
