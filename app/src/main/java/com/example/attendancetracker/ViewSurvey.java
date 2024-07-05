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

public class ViewSurvey extends AppCompatActivity {

    String username;

    String course;

    String event;

    StudentCoursesDB studentCoursesDB;

    SQLiteDatabase db;

    ArrayList<Surveys> surveysClassRatingArrayList;

    ArrayList<Surveys> surveysTeacherRatingArrayList;

    ArrayList<Surveys> surveysMaterialsRatingArrayList;

    SurveyAdapter surveyAdapter;

    RecyclerView rvCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

        Intent intent = getIntent();
        //username = intent.getStringExtra("username");
        course = intent.getStringExtra("aCourse");
        event = intent.getStringExtra("anEvent");

        surveysClassRatingArrayList = new ArrayList<>();

        surveysTeacherRatingArrayList = new ArrayList<>();

        surveysMaterialsRatingArrayList = new ArrayList<>();

        studentCoursesDB = new StudentCoursesDB(this);

        findId();

        surveysClassRatingArrayList = displayData();
        surveysTeacherRatingArrayList = displayData1();
        surveysMaterialsRatingArrayList = displayData2();
        surveyAdapter = new SurveyAdapter(ViewSurvey.this, surveysClassRatingArrayList, surveysTeacherRatingArrayList, surveysMaterialsRatingArrayList, event);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewSurvey.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(surveyAdapter);

        //coursesDB = new CoursesDB(this);
    }

    private ArrayList<Surveys> displayData() {
        db = studentCoursesDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Surveys.ClassRating FROM Surveys WHERE Surveys.SurveyEventName = '" + event + "'", null);
        if(cursor.moveToFirst()) {
            do {
                surveysClassRatingArrayList.add(new Surveys(cursor.getString(0)));
            } while (cursor.moveToNext());
        }

        /*cursor.moveToFirst();
        for(int i=0; i<6; i++)
        {
            coursesArrayList.add(new Courses(cursor.getString(1)));
            cursor.moveToNext();
        }*/

        return surveysClassRatingArrayList;
    }

    private ArrayList<Surveys> displayData1() {
        db = studentCoursesDB.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT Surveys.TeacherRating FROM Surveys WHERE Surveys.SurveyEventName = '" + event + "'", null);
        if(cursor1.moveToFirst()) {
            do {
                surveysTeacherRatingArrayList.add(new Surveys(cursor1.getString(0)));
            } while (cursor1.moveToNext());
        }

        /*cursor.moveToFirst();
        for(int i=0; i<6; i++)
        {
            coursesArrayList.add(new Courses(cursor.getString(1)));
            cursor.moveToNext();
        }*/

        return surveysTeacherRatingArrayList;
    }

    private ArrayList<Surveys> displayData2() {
        db = studentCoursesDB.getReadableDatabase();
        Cursor cursor2 = db.rawQuery("SELECT Surveys.MaterialsRating FROM Surveys WHERE Surveys.SurveyEventName = '" + event + "'", null);
        if(cursor2.moveToFirst()) {
            do {
                surveysMaterialsRatingArrayList.add(new Surveys(cursor2.getString(0)));
            } while (cursor2.moveToNext());
        }

        /*cursor.moveToFirst();
        for(int i=0; i<6; i++)
        {
            coursesArrayList.add(new Courses(cursor.getString(1)));
            cursor.moveToNext();
        }*/

        return surveysMaterialsRatingArrayList;
    }

    private void findId() {
        rvCourses = findViewById(R.id.idSurveyRecyclerView);
    }
}