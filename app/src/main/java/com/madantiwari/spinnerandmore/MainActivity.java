package com.madantiwari.spinnerandmore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ScrollingTabContainerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner spincountry, spinplayer;
    private AutoCompleteTextView autotext;
    private TextView txtdate, tvtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spincountry = findViewById(R.id.spinCountry);
        spinplayer = findViewById(R.id.spinPlayer);
        autotext = findViewById(R.id.autoText);
        txtdate = findViewById(R.id.txtDate);
        tvtime = findViewById(R.id.tvTime);

        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    loadDatePicker();
            }
        });

        tvtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    loadTime();
            }
        });

        String countries[]={"Nepal","India"};
        final String nepaliplayers[] = {"Sandeep","Paras","Sompal"};
        final String indianplayers[] = {"Virat","Dhoni","Rohit"};
        ArrayAdapter adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                countries);

        spincountry.setAdapter(adapter);

        ArrayAdapter<String> nepArray = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, nepaliplayers);
        autotext.setAdapter(nepArray);
        autotext.setThreshold(1);

       spincountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(spincountry.getSelectedItem().toString().equals("Nepal"))
               {
                   ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                           MainActivity.this, android.R.layout.simple_list_item_1, nepaliplayers
                   );
                   spinplayer.setAdapter(arrayAdapter);
               }
               else
               {
                   ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                           MainActivity.this, android.R.layout.simple_list_item_1, indianplayers
                   );
                   spinplayer.setAdapter(arrayAdapter);

               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


    }
    //function for date
            private void loadDatePicker()
            {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog( this, this, year, month, day);
                datePickerDialog.show();

            }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
             String date = "Month/Day/Year:"+month+"/"+dayOfMonth+"/"+year;
             txtdate.setText(date);
    }
      //function for time
    private void loadTime(){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        final int second = c.get(Calendar.SECOND);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new
                TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String amPm;
                        if(hourOfDay>=12)
                        {
                            hourOfDay-=12;
                            amPm="Pm";
                            }
                        else
                            {
                                amPm="Am";
                        }

                        tvtime.setText(("Time is:"+hourOfDay+":"+minute+" "+amPm).toString());
                    }
                }, hour,minute, true);
        timePickerDialog.show();
    }

}
