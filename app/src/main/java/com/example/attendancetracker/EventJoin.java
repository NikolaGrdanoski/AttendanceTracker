package com.example.attendancetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EventJoin extends AppCompatActivity {

    String username;

    String course;

    StudentCoursesDB studentCoursesDB;

    SQLiteDatabase db;

    private Button join;

    private Button surveyBtn;

    String event;

    String dbTime;

    String dbTime1;

    String dbEndTime;

    String dbEndTime1;

    String dbDate;

    Integer dbTimeI;

    Integer dbTimeI1;

    Integer dbEndTimeI;

    Integer dbEndTimeI1;

    Integer dbDateI;

    Integer currentTimeI;

    Integer currentTimeI1;

    Integer dateI;

    Cursor cursor;

    Cursor cursor1;

    Cursor cursor2;

    Cursor cursor3;

    Cursor cursor4;

    Cursor cursor5;

    Cursor cursor6;

    Cursor cursor7;

    String check;

    Location location;

    ShowNotification showNotification;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_join);

        join = findViewById(R.id.idJoin);

        surveyBtn = findViewById(R.id.idSurvey);

        Intent intent = getIntent();
        username = intent.getStringExtra("tUsername");
        course = intent.getStringExtra("course");
        event = intent.getStringExtra("event");

        studentCoursesDB = new StudentCoursesDB(EventJoin.this);

        db = studentCoursesDB.getReadableDatabase();

        Calendar calendar1 = Calendar.getInstance();
        Date time1 = calendar1.getTime();
        DateFormat newTime1 = new android.icu.text.SimpleDateFormat("HH:mm");
        String currentTime1 = newTime1.format(time1);
        String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        cursor6 = db.rawQuery("SELECT Events.eEndTime FROM Events WHERE Events.ecName = '" + course + "';", null);

        cursor7 = db.rawQuery("SELECT Events.eTime FROM Events WHERE Events.ecName = '" + course + "';", null);

        String currentTimeS1 = currentTime1.replace(":", "");

        currentTimeI1 = Integer.parseInt(currentTimeS1);

        if (cursor6.moveToFirst()) {
            dbEndTime1 = cursor6.getString(0);
            String dbEndTimeS1 = dbEndTime1.replace(":", "");
            dbEndTimeI1 = Integer.parseInt(dbEndTimeS1);
        }

        if (currentTimeI1 >= dbEndTimeI1) {
            surveyBtn.setEnabled(true);

            surveyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(EventJoin.this, Survey.class);
                    intent1.putExtra("aUsername", username);
                    intent1.putExtra("aCourse", course);
                    intent1.putExtra("event", event);
                    startActivity(intent1);
                }
            });
        } else {
            surveyBtn.setEnabled(false);
        }

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                Date time = calendar.getTime();
                DateFormat newTime = new android.icu.text.SimpleDateFormat("HH:mm");
                String currentTime = newTime.format(time);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                Criteria criteria = new Criteria();

                criteria.setAccuracy(Criteria.ACCURACY_FINE);

                String p = lm.getBestProvider(criteria, true);

                if (ActivityCompat.checkSelfPermission(EventJoin.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(EventJoin.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                location = new Location(p);

                if (p == null) {
                    p = "default";
                }

                find_Location(EventJoin.this);

                location = lm.getLastKnownLocation(p);

                double lat = location.getLatitude();

                double l = location.getLongitude();

                double FEITlat = 42;

                double FEITl = 21.4;

                studentCoursesDB = new StudentCoursesDB(EventJoin.this);

                db = studentCoursesDB.getReadableDatabase();

                cursor = db.rawQuery("SELECT Events.eTime FROM Events WHERE Events.ecName = '" + course + "';", null);
                cursor1 = db.rawQuery("SELECT Events.eEndTime FROM Events WHERE Events.ecName = '" + course + "';", null);
                cursor2 = db.rawQuery("SELECT Events.eDate FROM Events WHERE Events.ecName = '" + course + "';", null);

                if (cursor.moveToLast()) {
                    dbTime = cursor.getString(0);
                    String dbTimeS = dbTime.replace(":", "");
                    dbTimeI = Integer.parseInt(dbTimeS);
                }

                if (cursor1.moveToLast()) {
                    dbEndTime = cursor1.getString(0);
                    String dbEndTimeS = dbEndTime.replace(":", "");
                    dbEndTimeI = Integer.parseInt(dbEndTimeS);
                }

                if (cursor2.moveToLast()) {
                    dbDate = cursor2.getString(0);
                    String dbDateS = dbDate.replace("-", "");
                    dbDateI = Integer.parseInt(dbDateS);
                }

                String currentTimeS = currentTime.replace(":", "");

                String dateS = date.replace("-", "");

                currentTimeI = Integer.parseInt(currentTimeS);

                dateI = Integer.parseInt(dateS);

                if (lat == FEITlat && l == FEITl) {
                    if (dateI.equals(dbDateI)) {
                        if (currentTimeI > dbTimeI && currentTimeI < dbEndTimeI) {
                            Toast.makeText(EventJoin.this, "Success", Toast.LENGTH_SHORT).show();

                            cursor3 = db.rawQuery("SELECT Events.id5AUTOINCREMENTAUTOINCREMENT FROM Events WHERE Events.ecName = '" + course + "' AND Events.eDate = '" + date + "';", null);
                            cursor4 = db.rawQuery("SELECT Students.id2AUTOINCREMENTAUTOINCREMENT FROM Students WHERE Students.sName = '" + username + "';", null);
                            cursor5 = db.rawQuery("SELECT Courses.idAUTOINCREMENTAUTOINCREMENT FROM Courses WHERE Courses.cName = '" + course + "';", null);

                            if (cursor3.moveToFirst()) {
                                cursor3.getInt(0);
                            }

                            if (cursor4.moveToFirst()) {
                                cursor4.getInt(0);
                            }

                            if (cursor5.moveToFirst()) {
                                cursor5.getInt(0);
                            }

                        /*Cursor cursor10 = db.rawQuery("SELECT StudentEvents.eStudentName FROM StudentEvents, Events WHERE StudentEvents.eID = Events.id5AUTOINCREMENTAUTOINCREMENT;", null);

                        if (cursor10.moveToFirst()) {
                            if (!cursor10.isNull(0)) {
                                check = cursor10.getString(0);
                            }
                        }

                        if (check.) {
                            db.execSQL("INSERT INTO StudentEvents (sID, cID, eID, eStudentName, sEventName) VALUES ('" + cursor4 + "', '" + cursor5 + "', '" + cursor3 + "', '" + username + "', '" + event + "')");
                        }

                        else {
                            Toast.makeText(EventJoin.this, "Already present", Toast.LENGTH_SHORT).show();
                        }*/

                            db.execSQL("INSERT INTO StudentEvents (sID, cID, eID, eStudentName, sEventName) VALUES ('" + cursor4 + "', '" + cursor5 + "', '" + cursor3 + "', '" + username + "', '" + event + "')");
                        } else {
                            Toast.makeText(EventJoin.this, "Different time required", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EventJoin.this, "Different day required", Toast.LENGTH_SHORT).show();
                    }
                } else if (lat != FEITlat || l != FEITl) {
                    Toast.makeText(EventJoin.this, "Different place required", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EventJoin.this, "Different place required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        int req = 1;
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);

        if (cursor7.moveToFirst()) {
            dbTime1 = cursor7.getString(0);
            String dbTimeS1 = dbTime1.replace(":", "");
            dbTimeI1 = Integer.parseInt(dbTimeS1);
        }

        if (currentTimeI1 == (dbTimeI1 - 200)) {
            showNotification.showNotification(this, intent1, req, calendar1);
        }
    }

    public void find_Location(Context con) {
        Log.d("Find Location", "in find_location");
        String location_context = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) EventJoin.this.getSystemService(location_context);
        List<String> providers = locationManager.getProviders(true);
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(provider, 1000, 0,
                    new LocationListener() {

                        public void onLocationChanged(Location location) {
                        }

                        public void onProviderDisabled(String provider) {
                        }

                        public void onProviderEnabled(String provider) {
                        }

                        public void onStatusChanged(String provider, int status,
                                                    Bundle extras) {
                        }
                    });
            Location location = locationManager.getLastKnownLocation(provider);
        }
    }
}