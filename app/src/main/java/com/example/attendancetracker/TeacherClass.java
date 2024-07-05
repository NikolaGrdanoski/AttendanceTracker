package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class TeacherClass extends AppCompatActivity{
    private EditText courseNameEdt;
    private Button addCourseBtn;

    private Button tMyCourses;

    ArrayList<Courses> coursesArrayList;
    RecyclerView rvCourses;
    TeacherCoursesAdapter teacherCoursesAdapter;

    String username;
    StudentCoursesDB studentCoursesDB;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_courses);

        Intent intent1 = getIntent();

        username = (String) intent1.getStringExtra("tUsername");

        courseNameEdt = findViewById(R.id.idCourseName);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        tMyCourses = findViewById(R.id.idTMyCourses);

        studentCoursesDB = new StudentCoursesDB(this);

        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = courseNameEdt.getText().toString();
                db = studentCoursesDB.getWritableDatabase();
                ContentValues cv = new ContentValues();

                if(courseName.isEmpty()) {
                    Toast.makeText(TeacherClass.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
                }

                cv.put("cName", courseName);

                db.insert("Courses", null, cv);
                Toast.makeText(TeacherClass.this, "Succesfully added course", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
            }
        });

        tMyCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(TeacherClass.this, TeacherCoursesClasses.class);
                intent2.putExtra("tUsername", username);
                startActivity(intent2);
            }
        });

        coursesArrayList = new ArrayList<>();

        findId();

        coursesArrayList = displayData();
        teacherCoursesAdapter = new TeacherCoursesAdapter(TeacherClass.this, coursesArrayList, username);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TeacherClass.this, RecyclerView.VERTICAL, false);
        rvCourses.setLayoutManager(linearLayoutManager);
        rvCourses.setAdapter(teacherCoursesAdapter);
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
