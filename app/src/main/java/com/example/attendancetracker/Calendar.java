package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity {

    SQLiteDatabase db;
    StudentCoursesDB studentCoursesDB;

    private Button addEventBtn;

    ArrayList<Events> coursesArrayList;
    RecyclerView rvCourses;
    TeacherEventsAdapter teacherEventsAdapter;

    String course;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();

        course = (String) intent.getStringExtra("aCourse");

        username = (String) intent.getStringExtra("tUsername");

        addEventBtn = findViewById(R.id.idAddEvent);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(Calendar.this, AddEvent.class);
                intentAdd.putExtra("anExtraCourse", course);
                startActivity(intentAdd);
            }
        });

        coursesArrayList = new ArrayList<>();

        studentCoursesDB = new StudentCoursesDB(this);

        findId();

        coursesArrayList = displayData();
        teacherEventsAdapter = new TeacherEventsAdapter(Calendar.this, coursesArrayList, username, course);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Calendar.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(teacherEventsAdapter);


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
        rvCourses = findViewById(R.id.idEvents);
    }
}