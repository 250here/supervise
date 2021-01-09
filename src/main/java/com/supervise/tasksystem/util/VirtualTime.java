package com.supervise.tasksystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VirtualTime {
    private static Date date = new Date();
//    public VirtualTime(String s){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//        this.date = format.parse(s);
//    }catch (ParseException e){
//            e.printStackTrace();
//        }
//    }
    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        date = date;
    }
    public static void seDate(String s){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(s);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
