package com.kivrikog.kivrikog_countbook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        Date=simpleDateFormat.format(calendar.getTime());
        //making how date is gonna look

        lvItems = (ListView) findViewById(R.id.lvItems);
        add_counter = (Button)findViewById(R.id.button);

        add_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddCounter.class);
                startActivity(i);
                //here we will intent and when we click the button we will switch to addcounter activity from main activity
            }
        });

        final CustomAdapter customAdapter = new CustomAdapter();
        if(EditCounter.items.isEmpty())
            Toast.makeText(MainActivity.this,"No data. Click button to add some", Toast.LENGTH_SHORT).show();
        lvItems.setAdapter(customAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                //building our alert dialog for editing the counters
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog, null);
                final EditText editTextName = mView.findViewById(R.id.etName);
                final EditText editTextValue = mView.findViewById(etValue);
                final EditText editTextComment = mView.findViewById(R.id.etComment);
                editTextName.setText(EditCounter.items.get(i).getName());
                editTextValue.setText(EditCounter.items.get(i).getInitialValue());
                editTextComment.setText(EditCounter.items.get(i).getComment());
                //assigning the incrementation button and decrementation button
                Button mLogin = (Button) mView.findViewById(R.id.btnLogin);
                Button add = (Button)mView.findViewById(R.id.btnPlus);
                Button minus=(Button)mView.findViewById(R.id.btnMinus);
                Button reset=(Button)mView.findViewById(R.id.btnReset);



                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int value = Integer.parseInt(editTextValue.getText().toString());
                        value++;//incrementing
                        editTextValue.setText(String.valueOf(value));
                        saveInFile();
                    }
                });

                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int value = Integer.parseInt(editTextValue.getText().toString());
                        value--;//decrementing
                        editTextValue.setText(String.valueOf(value));
                        saveInFile();
                    }
                });
                reset.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int value = Integer.parseInt(editTextValue.getText().toString());
                        value=0;
                        editTextValue.setText(String.valueOf(value));
                        saveInFile();
                    }
                });

                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newName = editTextName.getText().toString();
                        String newValue = editTextValue.getText().toString();
                        String newComment = editTextComment.getText().toString();



                        if (newName.length() != 0 && newComment.length() != 0 && newValue.length() != 0) {
                            EditCounter.update(i, newName, newValue, newComment, date);
                            //our list consists of these values, name, value,comment and date
                            lvItems.setAdapter(customAdapter);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "You must fill in the text fields!", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                EditCounter.items.remove(i);
                lvItems.setAdapter(customAdapter);
                return false;

            }//deleting the counter by clicking on it too long
        });


    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return EditCounter.items.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.list_row,null);
            TextView nameValue = (TextView)view.findViewById(R.id.nameValue);
            TextView initialValue = (TextView)view.findViewById(R.id.initialValue);
            TextView commentValue = (TextView)view.findViewById(R.id.commentValue);
            TextView current_date= (TextView)view.findViewById(R.id.current_date);
            //viewing the final values

            nameValue.setText(EditCounter.items.get(i).getName());
            initialValue.setText(EditCounter.items.get(i).getInitialValue());
            commentValue.setText(EditCounter.items.get(i).getComment());
            current_date.setText(Date);
            return view;

        }
    }

    protected void onStart(){
        super.onStart();
        loadFromFile();


    }
    private void loadFromFile() {
        Log.d("loading","1");

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();

            //temp = gson.fromJson(in,listType);
            items = gson.fromJson(in,listType);

            Log.d("Items:", "" + items.size());
            Log.d("loading","2");

        } catch (FileNotFoundException e) {
            Log.d("loading","3");

            e.printStackTrace();
        } catch (IOException e) {
            Log.d("loading","4");

            throw new RuntimeException();
        }


    }
    private void saveInFile() {
        Log.d("saving","1");

        try {
            FileOutputStream fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson= new Gson();
            gson.toJson(items, writer);
            writer.flush();
            fos.close();
            Log.d("saving","2");
            Log.d("size of items when saving", ""+items.size());

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        Log.d("saving","3");

    }



}
