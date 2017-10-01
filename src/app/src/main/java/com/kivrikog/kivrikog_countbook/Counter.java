/*
*Counter Class
*
* Version 1.0
*
* Date September 30th, 2017
*
* Copyright Notice: This project have been created for class 301 by Pelin Kivrikoglu, all rights reserved.
*
 */

package com.kivrikog.kivrikog_countbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


//this class has been used as an initialization class for all of the values
public class Counter {
    private String name;
    private String initialValue;
    private String comment;
    private Date date;

/*
*Counter class for our values
* @param nameCounter name of the counter
* @param intialValue the value that we will assign
* @param commentCounter comment that we will enter
* @param date date of the counter
 */

    public Counter (String nameCounter,String initialValueCounter,String commentCounter, Date dateCounter) {

        name = nameCounter;
        initialValue=initialValueCounter;
        comment = commentCounter;
        date= dateCounter;


    }
/*getName gets the name written
*@returns name
 */
//getters and setters for our values
    public String getName() {
        return name;
    }
    /*setName sets the name to the written name
    *@param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /*getInitialValue gets the intialvalue written
    *@returns intialValue
     */
//getters and setters for our values
    public String getInitialValue() {
        return initialValue;
    }
    /*setInitialValue sets the intialvalue to the written initialValue
    *@param initialValue
     */

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }
     /*getComment gets the Comment written
    *@returns comment
     */

    public String getComment() {
        return comment;
    }
    /*setComment sets the comment to the written comment
    *@param comment
     */

    public void setComment(String comment) {
        this.comment = comment;
    }
     /*getDate gets the date of the day that counter has been created
    *@returns date
     */

    public Date getDate() {return date;}


}


