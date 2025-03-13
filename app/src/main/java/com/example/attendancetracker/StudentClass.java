package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

public class StudentClass extends AppCompatActivity {

    SQLiteDatabase db;
    StudentCoursesDB studentCoursesDB;
    ArrayList<Courses> coursesArrayList;
    RecyclerView rvCourses;
    CoursesAdapter coursesAdapter;

    String username;

    private Button idMyCoursesBtn;
    //List<Courses> coursesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_layout);

        Intent intent1 = getIntent();

        username = (String) intent1.getStringExtra("Username");

        coursesArrayList = new ArrayList<>();

        studentCoursesDB = new StudentCoursesDB(this);

        findId();

        coursesArrayList = displayData();
        coursesAdapter = new CoursesAdapter(StudentClass.this, coursesArrayList, username);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StudentClass.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(coursesAdapter);

        idMyCoursesBtn = findViewById(R.id.idMyCourses);

        idMyCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentClass.this, CoursesClasses.class);
                intent2.putExtra("cUsername", username);
                startActivity(intent2);
            }
        });


        //coursesDB = new CoursesDB(this);
    }

    private ArrayList<Courses> displayData() {
        db = studentCoursesDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Courses.* FROM Courses", null);
        if(cursor.moveToFirst()) {
            do {
                coursesArrayList.add(new Courses(cursor.getString(1)));
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
        rvCourses = findViewById(R.id.idCourses);
    }

}
