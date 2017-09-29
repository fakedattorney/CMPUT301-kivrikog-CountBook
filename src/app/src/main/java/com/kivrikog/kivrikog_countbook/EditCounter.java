package com.kivrikog.kivrikog_countbook;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import static android.provider.Telephony.Mms.Part.FILENAME;

/**
 * Created by Infrared on 2017-09-27.
 */
//this class is the array class, we created our arraylists here
class EditCounter {
    static ArrayList<Counter> items= new ArrayList<Counter>();

    public ArrayList<Counter> getItems(){
        return items;
    }
    private ArrayAdapter<Counter> adapter;


    public static Boolean update(int position,String newName, String newValue, String newComment, Date date){

        try {
            Counter c = new Counter(newName,newValue,newComment,date);
            items.remove(position);
            items.add(position,c);
            //saveInFile();

            return true;
        }//throwing some exceptions
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
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
    /*
    // private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson= new Gson();
            gson.toJson(EditCounter.items, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }**/
}
