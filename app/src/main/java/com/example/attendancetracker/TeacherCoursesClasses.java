package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TeacherCoursesClasses extends AppCompatActivity {

    SQLiteDatabase db1;

    StudentCoursesDB studentCoursesDB;
    ArrayList<Courses> coursesArrayList;
    RecyclerView rvCourses;
    TeacherCoursesClassesAdapter teacherCoursesClassesAdapter;

    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_courses_classes);

        coursesArrayList = new ArrayList<>();

        studentCoursesDB = new StudentCoursesDB(this);

        findId();

        coursesArrayList = displayData();
        teacherCoursesClassesAdapter = new TeacherCoursesClassesAdapter(TeacherCoursesClasses.this, coursesArrayList, username);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TeacherCoursesClasses.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(teacherCoursesClassesAdapter);
    }

    private ArrayList<Courses> displayData() {

        db1 = studentCoursesDB.getReadableDatabase();
        Cursor cursor = db1.rawQuery("SELECT TeacherCourses.ctName FROM TeacherCourses", null);

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
        rvCourses = findViewById(R.id.idTCCourses);
    }
}