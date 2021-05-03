package com.graduation.hvs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String D2NYR(Date date) throws Exception {
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sj.format(date);
    }

    public static Object D2NYR(Object date) {
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sj.format(date);
    }

    public static String getNow() {
        SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sj.format(new Date());
    }
}
