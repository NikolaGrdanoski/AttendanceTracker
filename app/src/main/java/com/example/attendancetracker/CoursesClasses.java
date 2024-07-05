package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;

public class CoursesClasses extends AppCompatActivity {

    private Button idCalendarBtn;

    SQLiteDatabase db;

    SQLiteDatabase db1;

    StudentCoursesDB studentCoursesDB;
    ArrayList<Courses> coursesArrayList;
    RecyclerView rvCourses;
    CoursesClassesAdapter coursesClassesAdapter;

    String username;
    RecyclerView.LayoutManager layoutManager;
    //List<Courses> coursesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses_classes_layout);

        Intent intent = getIntent();

        username = intent.getStringExtra("cUsername");

        coursesArrayList = new ArrayList<>();

        studentCoursesDB = new StudentCoursesDB(this);

        idCalendarBtn = findViewById(R.id.idCalendar);

        idCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CoursesClasses.this, EventsCalendar.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
            }
        });

        findId();

        coursesArrayList = displayData();
        coursesClassesAdapter = new CoursesClassesAdapter(CoursesClasses.this, coursesArrayList, username);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CoursesClasses.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(coursesClassesAdapter);



        //coursesDB = new CoursesDB(this);
    }

    private ArrayList<Courses> displayData() {

        db1 = studentCoursesDB.getReadableDatabase();
        Cursor cursor = db1.rawQuery("SELECT StudentCourses.csName FROM StudentCourses", null);

        //Cursor cursor1 = db.rawQuery("SELECT COUNT(*) AS brPredmeti FROM Courses", null);
        //int br=0;

        //coursesArrayList.add(new Courses(cursor1.getString(1)));



        //Toasct.makeText(StudentClass.this, br, Toast.LENGTH_LONG).show();
        if(cursor.moveToFirst()) {
            do {
                coursesArrayList.add(new Courses(cursor.getString(0)));
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
        rvCourses = findViewById(R.id.idCCourses);
    }
}