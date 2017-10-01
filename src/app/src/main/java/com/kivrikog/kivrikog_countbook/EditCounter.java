/*
*EditCounter Class
*
* Version 1.0
*
* Date September 30th, 2017
*
* Copyright Notice: This project have been created for class 301 by Pelin Kivrikoglu, all rights reserved.
*
 */
package com.kivrikog.kivrikog_countbook;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Date;

//this class is the array class, we created our arraylists here and add and remove our values
class EditCounter {
    static ArrayList<Counter> items= new ArrayList<Counter>();

    public ArrayList<Counter> getItems(){
        return items;
    }
    private ArrayAdapter<Counter> adapter;

/*
*Boolean update method
* @param position
*@param newName : for the name that will be assigned
 * @param newValue: for the value that will be assigned
 * @param date: for the date of the counter
 * @param newComment: writing a comment
* @throws exception e, for e.printStackTrace
* @returns true if we can add or remove from counter
* @returns false if error
 */
    public static Boolean update(int position,String newName, String newValue, String newComment, Date date){

        try {
            Counter c = new Counter(newName,newValue,newComment,date);
            items.remove(position);
            items.add(position,c);

            return true;
        }//throwing some exceptions
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    /*
    *Boolean delete method
    * @param position position of the item in the list
    * @return true if items removed
    * @returns false if error
    * @throws exception e, for e.printStackTrace
     */
    public static Boolean delete (int position){
        try {
            items.remove(position);
            return true;
        }//throwing some exceptions
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
