package com.kivrikog.kivrikog_countbook;

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

import static com.kivrikog.kivrikog_countbook.R.id.comments;
import static com.kivrikog.kivrikog_countbook.R.id.comments;
import static com.kivrikog.kivrikog_countbook.R.id.initialValue;

public class AddCounter extends Activity {

    EditText nameEditText;
    EditText initialValueEditText;
    EditText commentEditText;
    Date date;
    private static final String FILENAME = "file.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //adding a counter, getting values by their ids.
        setTitle("Add a Counter");
        setContentView(R.layout.activity_add_counter);
        Button saveButton = (Button) findViewById(R.id.add_count);
        nameEditText = (EditText) findViewById(R.id.name);
        initialValueEditText = (EditText) findViewById(initialValue);
        commentEditText = (EditText) findViewById(comments);


        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameText = nameEditText.getText().toString();
                String valueText = initialValueEditText.getText().toString();
                String commentText = commentEditText.getText().toString();
                // adding them into items editcounter
                Counter c = new Counter(nameText, valueText, commentText,date);
                EditCounter.items.add(c);

                //get
                //newclass.save(listofcounters)

                //list = newclass.load()
                saveInFile();
                Intent i = new Intent(AddCounter.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void saveInFile() {
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
    }
}