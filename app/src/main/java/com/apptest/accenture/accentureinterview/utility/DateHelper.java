package com.apptest.accenture.accentureinterview.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    private String date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat mySqlFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DateHelper(String date){
        this.date = date;
    }

    public String getDate(){

        Date convertedDate;
        try {
            convertedDate = dateFormat.parse(this.date);
            this.date = mySqlFormat.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this.date;
    }
}
