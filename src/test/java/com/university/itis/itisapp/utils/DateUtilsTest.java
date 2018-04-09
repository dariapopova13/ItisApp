package com.university.itis.itisapp.utils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void getDateMonthRange() {
        Date date = new Date();
        DateUtils dateUtils = new DateUtils();
        dateUtils.getDateMonthRange(date);
    }
}