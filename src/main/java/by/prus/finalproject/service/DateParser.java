package by.prus.finalproject.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Date stringToDate (String stringDate){

        Date date = null;
        try {
            date = df.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Something going wrong with date in Driver Class");
        }
        return date;
    }

    public String dateToString (Date date){

        return df.format(date.getTime());

    }

}
