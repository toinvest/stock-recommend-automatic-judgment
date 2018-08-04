package edu.mask.collection.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final long oneday = 24 * 60 * 60 * 1000L;

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDay() {
        return sdf.format(new Date());
    }

    public static String getDay(int index) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -index);
        return sdf.format(calendar.getTime());
    }

    public static Date resolve(String d) {
        try {
            return sdf.parse(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int diffDate(Date from, Date to) {
        return Long.valueOf((from.getTime() - to.getTime()) / oneday).intValue();
    }
}
