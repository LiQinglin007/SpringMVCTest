package com.xiaomi.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";
    private static DateFormat dateFormat = null;
    private static DateFormat dateTimeFormat = null;
    private static DateFormat timeFormat = null;
    private static DateFormat dateFormat_YEAR_MONTH = null;
    private static DateFormat dateFormat_MONTH = null;
    private static Calendar gregorianCalendar = null;
    public static final String DATE_YEAR_MONTH = "yyyy-MM";
    public static final String DATE_MONTH = "MM";

    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat_YEAR_MONTH = new SimpleDateFormat("yyyy-MM");
        dateFormat_MONTH = new SimpleDateFormat("MM");
        gregorianCalendar = new GregorianCalendar();
    }

    public DateUtil() {
    }

    public static Date formatDate(String date, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(date);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String getDateFormat(Date date) {
        return dateFormat.format(date);
    }

    public static String getMonthDateFormat(Date date) {
        return dateFormat_YEAR_MONTH.format(date);
    }

    public static String getDateTimeFormat(Date date) {
        return dateTimeFormat.format(date);
    }

    public static String getDateTimeStr(long locale) {
        Date date = new Date(locale);
        return getDateTimeFormat(date);
    }

    public static String getTimeFormat(Date date) {
        return timeFormat.format(date);
    }

    public static String getDateFormat(Date date, String formatStr) {
        return StringUtils.isNotBlank(formatStr) ? (new SimpleDateFormat(formatStr)).format(date) : null;
    }

    public static Date getDateFormat(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Date getDateTimeFormat(String date) {
        try {
            return dateTimeFormat.parse(date);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Date getNowDate() {
        return getDateFormat(dateFormat.format(new Date()));
    }

    public static Date getNowDateYearMonth() {
        return getDateFormat(dateFormat_YEAR_MONTH.format(new Date()));
    }

    public static Date getFirstDayOfWeek() {
        gregorianCalendar.setFirstDayOfWeek(2);
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(7, gregorianCalendar.getFirstDayOfWeek());
        return gregorianCalendar.getTime();
    }

    public static Date getLastDayOfWeek() {
        gregorianCalendar.setFirstDayOfWeek(2);
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(7, gregorianCalendar.getFirstDayOfWeek() + 6);
        return gregorianCalendar.getTime();
    }

    public static Date getFirstDayOfWeek(Date date) {
        if (date == null) {
            return null;
        } else {
            gregorianCalendar.setFirstDayOfWeek(2);
            gregorianCalendar.setTime(date);
            gregorianCalendar.set(7, gregorianCalendar.getFirstDayOfWeek());
            return gregorianCalendar.getTime();
        }
    }

    public static Date getLastDayOfWeek(Date date) {
        if (date == null) {
            return null;
        } else {
            gregorianCalendar.setFirstDayOfWeek(2);
            gregorianCalendar.setTime(date);
            gregorianCalendar.set(7, gregorianCalendar.getFirstDayOfWeek() + 6);
            return gregorianCalendar.getTime();
        }
    }

    public static Date getFirstDayOfMonth() {
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(5, 1);
        return gregorianCalendar.getTime();
    }

    public static Date getLastDayOfMonth() {
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(5, 1);
        gregorianCalendar.add(2, 1);
        gregorianCalendar.add(5, -1);
        return gregorianCalendar.getTime();
    }

    public static Date getFirstDayOfMonth(Date date) {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(5, 1);
        return gregorianCalendar.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(5, 1);
        gregorianCalendar.add(2, 1);
        gregorianCalendar.add(5, -1);
        return gregorianCalendar.getTime();
    }

    public static Date getDayBefore(Date date) {
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(5);
        gregorianCalendar.set(5, day - 1);
        return gregorianCalendar.getTime();
    }

    public static Date getDayAfter(Date date) {
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(5);
        gregorianCalendar.set(5, day + 1);
        return gregorianCalendar.getTime();
    }

    public static int getNowYear() {
        Calendar d = Calendar.getInstance();
        return d.get(1);
    }

    public static int getNowMonth() {
        Calendar d = Calendar.getInstance();
        return d.get(2) + 1;
    }

    public static int getNowMonthDay() {
        Calendar d = Calendar.getInstance();
        return d.getActualMaximum(5);
    }

    public static List<Date> getEveryDay(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            startDate = getDateFormat(getDateFormat(startDate));
            endDate = getDateFormat(getDateFormat(endDate));
            List<Date> dates = new ArrayList();
            gregorianCalendar.setTime(startDate);
            dates.add(gregorianCalendar.getTime());

            while(gregorianCalendar.getTime().compareTo(endDate) < 0) {
                gregorianCalendar.add(5, 1);
                dates.add(gregorianCalendar.getTime());
            }

            return dates;
        } else {
            return null;
        }
    }

    public static Date getFirstMonth(int monty) {
        Calendar c = Calendar.getInstance();
        c.add(2, -monty);
        return c.getTime();
    }

    public static List<Integer> getYearList() {
        int nowyear = getNowYear();
        List<Integer> list = new ArrayList();

        for(int i = nowyear; i > nowyear - 15; --i) {
            list.add(i);
        }

        return list;
    }

    public static List<String> getYearList6() {
        int nowyear = getNowYear();
        List<String> list = new ArrayList();

        for(int i = nowyear; i > nowyear - 6; --i) {
            list.add(String.valueOf(i));
        }

        return list;
    }

    public static Date getDateAfterSecond(Date date, int second) {
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(13);
        gregorianCalendar.set(13, day + second);
        return gregorianCalendar.getTime();
    }

    public static Date getDateAfterHour(Date date, int hour) {
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(10);
        gregorianCalendar.set(10, day + hour);
        return gregorianCalendar.getTime();
    }

    public static String stampToDate(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = (new Long(s)).longValue();
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }

    public static List<String> getNowDateMonthList(int count) {
        List<String> list = new ArrayList();

        for(int i = count - 1; i >= 0; --i) {
            Date d = getFirstMonth(i);
            list.add(dateFormat_YEAR_MONTH.format(d));
            System.out.println(dateFormat_YEAR_MONTH.format(d));
        }

        return list;
    }

    public static List<String> getNowMonthList(int count) {
        List<String> list = new ArrayList();

        for(int i = count - 1; i >= 0; --i) {
            Date d = getFirstMonth(i);
            list.add(dateFormat_MONTH.format(d));
        }

        return list;
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = new String[]{"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static void main(String[] arg) {
        getNowYear();
        String operateTime = getDateFormat(new Date(), "HH:mm");
        System.out.println("---:" + operateTime);
        System.out.println(getWeekOfDate(new Date()));
    }
}
