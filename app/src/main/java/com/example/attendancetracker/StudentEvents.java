package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class StudentEvents extends AppCompatActivity {
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
        setContentView(R.layout.activity_student_events);

        Intent intent1 = getIntent();

        username = (String) intent1.getStringExtra("aUsername");

        course = (String) intent1.getStringExtra("course");

        coursesArrayList = new ArrayList<>();

        studentCoursesDB = new StudentCoursesDB(this);

        findId();

        coursesArrayList = displayData();
        studentEventsAdapter = new StudentEventsAdapter(StudentEvents.this, coursesArrayList, username, course);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentEvents.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(studentEventsAdapter);


        //coursesDB = new CoursesDB(this);
    }

    private ArrayList<Events> displayData() {
        db = studentCoursesDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Events.eName FROM Events WHERE Events.ecName = '" + course + "'", null);
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
        rvCourses = findViewById(R.id.idAllEvents);
    }
}