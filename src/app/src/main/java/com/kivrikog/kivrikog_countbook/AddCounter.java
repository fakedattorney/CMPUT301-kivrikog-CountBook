
package com.kivrikog.kivrikog_countbook;
//imports that has been used in the making of this app
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import static android.provider.Telephony.Mms.Part.FILENAME;
import static com.kivrikog.kivrikog_countbook.MainActivity.items;
import static com.kivrikog.kivrikog_countbook.R.id.comments;
import static com.kivrikog.kivrikog_countbook.R.id.comments;
import static com.kivrikog.kivrikog_countbook.R.id.initialValue;
/*
*AddCounter Class
*
* Version 1.0
*
* Date September 30th, 2017
*
* Copyright Notice: This project have been created for class 301 by Pelin Kivrikoglu, all rights reserved.
*
 */
//addcounter class extends from the activity class
public class AddCounter extends Activity {
    //initializing edit text and date that has been used in this class
    EditText nameEditText;
    EditText initialValueEditText;
    EditText commentEditText;
    Date date;
    //for on create, we will add a counter with using the layout of activity_add_counter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //adding a counter, getting values by their ids.
        setTitle("Add a Counter");
        //since we are using activity_add_counter we get the ids from the layout xml file
        setContentView(R.layout.activity_add_counter);
        Button saveButton = (Button) findViewById(R.id.add_count);
        nameEditText = (EditText) findViewById(R.id.name);
        initialValueEditText = (EditText) findViewById(initialValue);
        commentEditText = (EditText) findViewById(comments);

        //whenever the savebutton clicked the string versions of name, initial value and comment will be stored in items list
        //and sent to mainactivity class
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameText = nameEditText.getText().toString();
                String valueText = initialValueEditText.getText().toString();
                String commentText = commentEditText.getText().toString();
                Counter c = new Counter(nameText, valueText, commentText,date);
                EditCounter.items.add(c);
                Intent i = new Intent(AddCounter.this, MainActivity.class);
                startActivity(i);
                saveInfile();
                finish();
            }
        });
    }
//we will saveinfile and throw some errors here
    /*saveInFile method saves the items in the file
    *@throw RuntimException
    * @throw IOException
     */
    private void saveInfile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson= new Gson();
            gson.toJson(items, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}