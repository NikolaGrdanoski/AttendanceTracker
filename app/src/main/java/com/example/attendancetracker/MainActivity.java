package com.example.attendancetracker;

import static android.os.Build.VERSION_CODES.M;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText idLoginTextEdt;
    private EditText idPasswordTextEdt;

    private Button idLoginButtonBtn;

    private Button idRegisterButtonBtn;

    SQLiteDatabase db;

    StudentCoursesDB studentCoursesDB;

    String aaa, aaa1, aaaaaa, aaaaaa1;

    Intent intent, intent1;

    public String getUsername() {
        return idLoginTextEdt.getText().toString();
    }

    void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            int i = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = new NotificationChannel("eventN", "EventN", i);
            mChannel.setDescription("You have a class in 2 hours");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(mChannel);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idLoginTextEdt = findViewById(R.id.idLoginText);
        idPasswordTextEdt = findViewById(R.id.idPasswordText);
        idLoginButtonBtn = findViewById(R.id.idLoginButton);
        idRegisterButtonBtn = findViewById(R.id.idRegisterButton);

        studentCoursesDB = new StudentCoursesDB(this);

        db = studentCoursesDB.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT Students.* FROM Students", null);

        Cursor cursor1 = db.rawQuery("SELECT Teachers.* FROM Teachers", null);

        createChannel();


        idLoginButtonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = idLoginTextEdt.getText().toString();
                String password = idPasswordTextEdt.getText().toString();


                if(username.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    return;
                }

                aaa = null;
                aaa1 = null;
                char[] usernameCheck = username.toCharArray();

                if(usernameCheck[0] == 's') {
                    if(cursor.moveToFirst()) {
                        do {
                            if (username.equals(cursor.getString(1))) {
                                aaa = username;
                                intent1 = new Intent(MainActivity.this, StudentClass.class);
                                intent1.putExtra("Username", username);
                                startActivity(intent1);
                                break;
                            }
                        } while (cursor.moveToNext());
                        cursor.moveToFirst();

                        if (aaa == null) {
                            Toast.makeText(MainActivity.this, "Not registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (usernameCheck[0] == 'p') {
                    if(cursor1.moveToFirst()) {
                        do {
                            if (username.equals(cursor1.getString(1))) {
                                aaa1 = username;
                                intent = new Intent(MainActivity.this, TeacherClass.class);
                                intent.putExtra("tUsername", username);
                                startActivity(intent);
                                break;
                            }
                        } while (cursor1.moveToNext());
                        cursor1.moveToFirst();

                        if (aaa1 == null) {
                            Toast.makeText(MainActivity.this, "Not registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        idRegisterButtonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = idLoginTextEdt.getText().toString();
                String password = idPasswordTextEdt.getText().toString();

                if(username.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    return;
                }

                aaaaaa = null;
                aaaaaa1 = null;
                char[] usernameCheck = username.toCharArray();

                if(usernameCheck[0] == 's') {
                    if(cursor.moveToFirst()) {
                        do {
                            if (username.equals(cursor.getString(1))) {
                                aaaaaa = username;
                                Toast.makeText(MainActivity.this, "Already registered", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } while (cursor.moveToNext());
                        cursor.moveToFirst();
                    }

                    if (aaaaaa == null) {
                        studentCoursesDB.addNewStudent(username);
                        Toast.makeText(MainActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();

                        intent1 = new Intent(MainActivity.this, StudentClass.class);
                        intent1.putExtra("Username", username);
                        startActivity(intent1);
                    }

                } else if (usernameCheck[0] == 'p') {
                    if(cursor1.moveToFirst()) {
                        do {
                            if (username.equals(cursor1.getString(1))) {
                                aaaaaa1 = username;
                                Toast.makeText(MainActivity.this, "Already registered", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } while (cursor1.moveToNext());
                        cursor1.moveToFirst();
                    }

                    if(aaaaaa1 == null) {
                        studentCoursesDB.addNewTeacher(username);
                        Toast.makeText(MainActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();

                        intent = new Intent(MainActivity.this, TeacherClass.class);
                        intent.putExtra("tUsername", username);
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
