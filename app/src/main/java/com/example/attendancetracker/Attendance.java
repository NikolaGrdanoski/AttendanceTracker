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

public class Attendance extends AppCompatActivity {

    private Button viewSurveys;
    String username;

    String course;

    SQLiteDatabase db;
    StudentCoursesDB studentCoursesDB;

    ArrayList<Events> coursesArrayList;
    RecyclerView rvCourses;
    AttendanceAdapter attendanceAdapter;

    String event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        Intent intent = getIntent();
        username = intent.getStringExtra("tUsername");
        course = intent.getStringExtra("course");
        event = intent.getStringExtra("event");

        viewSurveys = findViewById(R.id.idSurveyView);

        coursesArrayList = new ArrayList<>();

        studentCoursesDB = new StudentCoursesDB(this);

        findId();

        coursesArrayList = displayData();
        attendanceAdapter = new AttendanceAdapter(Attendance.this, coursesArrayList, username, course, event);

        viewSurveys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Attendance.this, ViewSurvey.class);
                //intent1.putExtra("username", username);
                intent1.putExtra("aCourse", course);
                intent1.putExtra("anEvent", event);
                startActivity(intent1);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Attendance.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(attendanceAdapter);

        //coursesDB = new CoursesDB(this);
    }

    private ArrayList<Events> displayData() {
        db = studentCoursesDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT StudentEvents.eStudentName FROM StudentEvents WHERE StudentEvents.sEventName = '" + event + "'", null);
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
        rvCourses = findViewById(R.id.idAttendance);
    }
}