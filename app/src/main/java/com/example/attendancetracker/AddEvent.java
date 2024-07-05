package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Fragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEvent extends AppCompatActivity {

    private Button timePicker;

    private Button datePicker;

    private Button endTimePicker;

    private EditText eventName;

    private Button test;

    String time;

    String eTime;

    String date;

    SQLiteDatabase db;

    StudentCoursesDB studentCoursesDB;

    String course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        eventName = findViewById(R.id.idEventName);

        timePicker = findViewById(R.id.pickTime);

        datePicker = findViewById(R.id.pickDate);

        endTimePicker = findViewById(R.id.pickEndTime);

        test = findViewById(R.id.test);

        Intent intent = getIntent();

        course = (String) intent.getStringExtra("anExtraCourse");

        studentCoursesDB = new StudentCoursesDB(AddEvent.this);

        db = studentCoursesDB.getWritableDatabase();

        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime(timePicker);
            };
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(datePicker);
            }
        });

        endTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndTime(endTimePicker);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eName = eventName.getText().toString();
                db.execSQL("INSERT INTO Events (eDate, eTime, eEndTime, eName, ecName) VALUES ('"+date+"', '"+time+"', '"+eTime+"', '"+eName+"', '"+course+"')");

                Toast.makeText(AddEvent.this, "Successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDate(Button datePicker) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                date = simpleDateFormat.format(calendar.getTime());
            }
        };

        new DatePickerDialog(AddEvent.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void showTime(Button timePicker) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

                time = simpleDateFormat.format(calendar.getTime());
            }
        };

        new TimePickerDialog(AddEvent.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    public void showEndTime(Button timePicker) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

                eTime = simpleDateFormat.format(calendar.getTime());
            }
        };

        new TimePickerDialog(AddEvent.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }
}