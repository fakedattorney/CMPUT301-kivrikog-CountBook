package com.kivrikog.kivrikog_countbook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Date;

import static android.R.attr.value;
import static com.kivrikog.kivrikog_countbook.R.id.current_date;
import static com.kivrikog.kivrikog_countbook.R.id.etValue;
import static com.kivrikog.kivrikog_countbook.R.id.initialValue;
import static com.kivrikog.kivrikog_countbook.R.id.lvItems;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";


    public static ArrayList<Counter> items= new ArrayList<>();
    private ArrayAdapter<Counter> adapter;

    ListView lvItems;
    Button add_counter;
    Dialog d;
    SimpleDateFormat simpleDateFormat;
    String Date;
    Calendar calendar;
    Date date;
    private String initialValue;
    //assigning the date calender button and list view we will be using




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date = simpleDateFormat.format(calendar.getTime());
        //making how date is gonna look

        lvItems = (ListView) findViewById(R.id.lvItems);
    }

}
