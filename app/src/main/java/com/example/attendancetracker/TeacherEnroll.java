package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TeacherEnroll extends AppCompatActivity {
    StudentCoursesDB studentCoursesDB;

    SQLiteDatabase db;

    private Button idEnrollBtn;

    String username;

    String course;

    Context context;

    Context context1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_enroll);

        Intent intent1 = this.getIntent();

        username = (String) intent1.getStringExtra("tUsername");

        Intent intent2 = this.getIntent();

        course = (String) intent2.getStringExtra("course");

        idEnrollBtn = findViewById(R.id.idTeacherEnroll);

        studentCoursesDB = new StudentCoursesDB(this);

        db = studentCoursesDB.getWritableDatabase();

        idEnrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentCoursesDB.addT(username, course);
                Toast.makeText(TeacherEnroll.this, "Successfully enrolled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}