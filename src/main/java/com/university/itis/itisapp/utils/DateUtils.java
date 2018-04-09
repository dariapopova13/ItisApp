package com.university.itis.itisapp.utils;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private SimpleDateFormat format;

    public DateUtils() {
        format = new SimpleDateFormat(DATE_FORMAT);
    }

    public Pair<Date, Date> getDateMonthRange(String date) {
        Date getDate;
        try {
            getDate = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            getDate = new Date();
        }
        return getDateMonthRange(getDate);
    }

    public Pair<Date, Date> getDateMonthRange(Date date) {

        Date begining, end;

        {
            Calendar calendar = getCalendarOfDate(date);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }
        {
            Calendar calendar = getCalendarOfDate(date);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        return Pair.of(begining, end);
    }

    private Calendar getCalendarOfDate(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    private void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    public int getWeekday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public LocalDate toLocalDate(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getFormatedDate(Date date) {
        return format.format(date);
    }

    public Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public String getFormatedDate(LocalDate localDate) {
        Date date = toDate(localDate);
        return getFormatedDate(date);
    }
}
