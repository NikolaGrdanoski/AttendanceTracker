package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.os.Bundle;

import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EventsCalendar extends AppCompatActivity {

    SQLiteDatabase db;
    StudentCoursesDB studentCoursesDB;
    ArrayList<Events> coursesArrayList;
    RecyclerView rvCourses;
    StudentEventsAdapter studentEventsAdapter;

    String username;

    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_calendar);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        course = "Default";

        coursesArrayList = new ArrayList<>();

        studentCoursesDB = new StudentCoursesDB(this);

        findId();

        coursesArrayList = displayData();
        studentEventsAdapter = new StudentEventsAdapter(EventsCalendar.this, coursesArrayList, username, course);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EventsCalendar.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(studentEventsAdapter);


        //coursesDB = new CoursesDB(this);
    }

    private ArrayList<Events> displayData() {
        db = studentCoursesDB.getReadableDatabase();

        Calendar calendar1 = Calendar.getInstance();
        DateFormat newCalendar1 = new android.icu.text.SimpleDateFormat("yyyy-MM-dd");
        Date time1 = calendar1.getTime();
        DateFormat newTime1 = new android.icu.text.SimpleDateFormat("HH:mm");
        //String currentTime1 = newTime1.format(time1);
        //String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Cursor cursor = db.rawQuery("SELECT Events.eName FROM Events, StudentCourses WHERE StudentCourses.csName IS NOT NULL", null);
        if(cursor.moveToFirst()) {
            do {
                coursesArrayList.add(new Events(cursor.getString(0)));
            } while (cursor.moveToNext());
        }

        /*cursor.moveToFirst();
        for(int i=0; i<6; i++)
        {
            coursesArrayList.add(new Courses(cursor.getString(1)));
            cursor.moveToNext();
        }*/

        return coursesArrayList;
    }

    private void findId() {
        rvCourses = findViewById(R.id.idCalendarEvents);
    }
}