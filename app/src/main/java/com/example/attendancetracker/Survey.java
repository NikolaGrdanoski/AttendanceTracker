package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Survey extends AppCompatActivity {

    private EditText classRating;

    private EditText teacherRating;

    private EditText materialsRating;

    private Button surveySubmitBtn;

    String username;

    String course;

    String event;

    StudentCoursesDB studentCoursesDB;

    SQLiteDatabase db;

    Cursor cursor;

    Cursor cursor1;

    Cursor cursor2;

    Cursor cursor3;

    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        classRating = findViewById(R.id.idClassRating);

        teacherRating = findViewById(R.id.idTeacherRating);

        materialsRating = findViewById(R.id.idMaterialsRating);

        surveySubmitBtn = findViewById(R.id.idSurveySubmit);

        Intent intent = getIntent();

        username = intent.getStringExtra("aUsername");

        course = intent.getStringExtra("aCourse");

        event = intent.getStringExtra("event");

        studentCoursesDB = new StudentCoursesDB(Survey.this);

        db = studentCoursesDB.getWritableDatabase();

        cursor3 = db.rawQuery("SELECT StudentEvents.eStudentName FROM StudentEvents WHERE StudentEvents.eStudentName = '" + username + "' AND StudentEvents.sEventName = '" + event + "';", null);

        if (cursor3.moveToFirst()) {
            check = cursor3.getString(0);
        }

        surveySubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classRating1 = classRating.getText().toString();

                String teacherRating1 = teacherRating.getText().toString();

                String materialsRating1 = materialsRating.getText().toString();

                if (classRating1.isEmpty() || teacherRating1.isEmpty() || materialsRating1.isEmpty()) {
                    Toast.makeText(Survey.this, "Please fill out all the forms", Toast.LENGTH_SHORT).show();
                } else if (check.isEmpty()) {
                    Toast.makeText(Survey.this, "Not present during this class", Toast.LENGTH_SHORT).show();
                } else {
                    cursor = db.rawQuery("SELECT Students.id2AUTOINCREMENTAUTOINCREMENT FROM Students WHERE Students.sName = '" + username + "';", null);
                    cursor1 = db.rawQuery("SELECT Events.id5AUTOINCREMENTAUTOINCREMENT FROM Events WHERE Events.ecName = '" + course + "' AND Events.eName = '" + event + "';", null);
                    cursor2 = db.rawQuery("SELECT Courses.idAUTOINCREMENTAUTOINCREMENT FROM Courses WHERE Courses.cName = '" + course + "';", null);

                    if (cursor.moveToFirst()) {
                        cursor.getInt(0);
                    }

                    if (cursor1.moveToFirst()) {
                        cursor1.getInt(0);
                    }
                    if (cursor2.moveToFirst()) {
                        cursor2.getInt(0);
                    }

                    db.execSQL("INSERT INTO Surveys (sID, cID, eID, ClassRating, TeacherRating, MaterialsRating, SurveyEventName) VALUES ('"+cursor+"', '"+cursor1+"', '"+cursor2+"', '"+classRating1+"', '"+teacherRating1+"', '"+materialsRating1+"', '"+event+"')");
                    Toast.makeText(Survey.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}