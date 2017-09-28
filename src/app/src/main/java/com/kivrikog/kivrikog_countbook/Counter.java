package com.kivrikog.kivrikog_countbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Infrared on 2017-09-27.
 */
//this class has the basic values we need
public class Counter {
    private String name;
    private String initialValue;
    private String comment;
    private Date date;



    public Counter (String nameCounter,String initialValueCounter,String commentCounter, Date dateCounter) {

        name = nameCounter;
        initialValue=initialValueCounter;
        comment = commentCounter;
        date= dateCounter;


    }

//getters and setters for our values
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {return date;}


}


