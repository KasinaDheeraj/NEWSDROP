package com.example.newsdrop.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    public static String time="";
    public static String getDate(String stringDate){
        String newDate="";
        String pattern="E, d MMM YYYY";
        String timePattern="HH:mm a";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);

        try {
            Date date=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(stringDate);
            newDate=sdf.format(date);
            time=new SimpleDateFormat(timePattern).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate=stringDate;
        }
        return newDate;
    }
}
