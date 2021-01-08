package com.supervise.tasksystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VirtualTime {
    private Date date;
    public VirtualTime(String s){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        this.date = format.parse(s);
    }catch (ParseException e){
            e.printStackTrace();
        }
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
