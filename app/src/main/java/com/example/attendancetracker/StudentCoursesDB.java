package com.example.attendancetracker;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

public class StudentCoursesDB extends SQLiteOpenHelper {

    private static final String DB_Name = "StudentCoursesDB";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "StudentCourses";

    private static final String ID_COL = "id3AUTOINCREMENT";

    private static final String S_COL = "sID";

    private static final String C_COL = "cID";

    private static final String SC_S_NAME = "scName";

    private static final String SC_C_NAME = "csName";

    public static final String S_TABLE_NAME = "Students";

    public static final String S_ID_COL = "id2AUTOINCREMENT";

    private static final String NAME_COL = "sName";

    public static final String C_TABLE_NAME = "Courses";

    public static final String C_ID_COL = "idAUTOINCREMENT";

    private static final String C_NAME_COL = "cName";

    private static final String T_TABLE_NAME = "Teachers";

    private static final String T_ID_COL = "id1AUTOINCREMENT";

    private static final String T_NAME_COL = "tName";

    private static final String TC_TABLE_NAME = "TeacherCourses";

    private static final String TC_ID_COL = "id4AUTOINCREMENT";

    private static final String T_COL = "tID";

    private static final String TC_T_NAME = "tcName";

    private static final String TC_C_NAME = "ctName";

    private static final String E_TABLE_NAME = "Events";

    private static final String E_ID = "id5AUTOINCREMENT";

    private static final String E_DATE = "eDate";

    private static final String E_TIME = "eTime";

    private static final String E_END_TIME = "eEndTime";

    private static final String E_NAME = "eName";

    private static final String SE_EVENT_TABLE = "StudentEvents";

    private static final String SE_EVENT_ID = "id6AUTOINCREMENT";

    private static final String SE_EVENT_E_ID = "eID";

    private static final String SE_STUDENT_NAME = "eStudentName";

    private static final String E_C_NAME = "ecName";

    private static final String SE_EVENT_NAME = "sEventName";

    private static final String SURVEY_TABLE_NAME = "Surveys";

    private static final String SURVEY_ID = "surIDAUTOINCREMENT";

    private static final String SURVEY_CLASS_RATING = "ClassRating";

    private static final String SURVEY_TEACHER_RATING = "TeacherRating";

    private static final String SURVEY_CLASS_MATERIALS_RATING = "MaterialsRating";

    private static final String SURVEY_EVENT_NAME = "SurveyEventName";

    public StudentCoursesDB(@Nullable Context context) { super(context, DB_Name, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + S_COL + " INTEGER, "
                + C_COL + " INTEGER, "
                + SC_S_NAME + " TEXT, "
                + SC_C_NAME + " TEXT, " +
                "FOREIGN KEY (" + S_COL + ") REFERENCES " + S_TABLE_NAME + "( " + S_ID_COL + ")," +
                "FOREIGN KEY (" + C_COL + ") REFERENCES " + C_TABLE_NAME + "( " + C_ID_COL + "));";

        String query1 = "CREATE TABLE " + S_TABLE_NAME + " ("
                + S_ID_COL + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + NAME_COL + " TEXT)";

        String query2 = "CREATE TABLE " + C_TABLE_NAME + " ("
                + C_ID_COL + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + C_NAME_COL + " TEXT)";

        String query3 = "CREATE TABLE " + T_TABLE_NAME + " ("
                + T_ID_COL + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + T_NAME_COL + " TEXT)";

        String query5 = "CREATE TABLE " + E_TABLE_NAME + " ("
                + E_ID + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + E_DATE + " DATE, "
                + E_TIME + " TIME, "
                + E_END_TIME + " TIME, "
                + E_NAME + " TEXT, "
                + E_C_NAME + " TEXT)";

        String query4 = "CREATE TABLE " + TC_TABLE_NAME + " ("
                + TC_ID_COL + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + T_COL + " INTEGER, "
                + C_COL + " INTEGER, "
                + TC_T_NAME + " TEXT, "
                + TC_C_NAME + " TEXT, " +
                "FOREIGN KEY (" + T_COL + ") REFERENCES " + T_TABLE_NAME + "( " + T_ID_COL + ")," +
                "FOREIGN KEY (" + C_COL + ") REFERENCES " + C_TABLE_NAME + "( " + C_ID_COL + "));";

        String query6 = "CREATE TABLE " + SE_EVENT_TABLE + " ("
                + SE_EVENT_ID + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + S_COL + " INTEGER, "
                + C_COL + " INTEGER, "
                + SE_EVENT_E_ID + " INTEGER, "
                + SE_STUDENT_NAME + " TEXT, "
                + SE_EVENT_NAME + " TEXT, " +
                "FOREIGN KEY (" + S_COL + ") REFERENCES " + S_TABLE_NAME + "( " + S_ID_COL + ")," +
                "FOREIGN KEY (" + C_COL + ") REFERENCES " + C_TABLE_NAME + "( " + C_ID_COL + ")," +
                "FOREIGN KEY (" + SE_EVENT_E_ID + ") REFERENCES " + E_TABLE_NAME + "( " + E_ID + "));";

        String query7 = "CREATE TABLE " + SURVEY_TABLE_NAME + " ("
                + SURVEY_ID + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + S_COL + " INTEGER, "
                + C_COL + " INTEGER, "
                + SE_EVENT_E_ID + " INTEGER, "
                + SURVEY_CLASS_RATING + " TEXT, "
                + SURVEY_TEACHER_RATING + " TEXT, "
                + SURVEY_CLASS_MATERIALS_RATING + " TEXT, "
                + SURVEY_EVENT_NAME + " TEXT, " +
                "FOREIGN KEY (" + S_COL + ") REFERENCES " + S_TABLE_NAME + "( " + S_ID_COL + ")," +
                "FOREIGN KEY (" + C_COL + ") REFERENCES " + C_TABLE_NAME + "( " + C_ID_COL + ")," +
                "FOREIGN KEY (" + SE_EVENT_E_ID + ") REFERENCES " + E_TABLE_NAME + "( " + E_ID + "));";

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query5);
        db.execSQL(query);
        db.execSQL(query4);
        db.execSQL(query6);
        db.execSQL(query7);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + S_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + C_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + T_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TC_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + E_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SE_EVENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SURVEY_TABLE_NAME);
        onCreate(db);
    }

    public void addNewStudent(String studentName) {
        SQLiteDatabase db2 = this.getWritableDatabase();

        ContentValues values2 = new ContentValues();

        values2.put("sName", studentName);

        db2.insert("Students", null, values2);

    }

    public void addNewTeacher(String teacherName) {
        SQLiteDatabase db1 = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();

        values1.put("tName", teacherName);

        db1.insert("Teachers", null, values1);

    }

    public void add(String sName, String cName) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values1 = new ContentValues();

        ContentValues values2 = new ContentValues();

        Cursor cursor1 = db.rawQuery("SELECT Students.id2AUTOINCREMENTAUTOINCREMENT FROM Students WHERE Students.sName = '" + sName + "';", null);

        Cursor cursor2 = db.rawQuery("SELECT Courses.idAUTOINCREMENTAUTOINCREMENT FROM Courses WHERE Courses.cName = '" + cName + "';", null);

        if (cursor1.moveToFirst()) {
            cursor1.getString(0);
        }

        if (cursor2.moveToFirst()) {
            cursor2.getString(0);
        }

        //values1.put("sID", cursor1.getInt(0));

        //values2.put("cID", cursor2.getInt(0));

        //db.insert("StudentCourses", null, values1);

        //db.insert("StudentCourses", null, values2);

        db.execSQL("INSERT INTO StudentCourses (sID, cID, scName, csName) VALUES ('"+cursor1+"', '"+cursor2+"', '"+sName+"', '"+cName+"')");

        //SQL Query can be used here, INSERT...

    }

    public void addT(String tName, String cName) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor1 = db.rawQuery("SELECT Teachers.id1AUTOINCREMENTAUTOINCREMENT FROM Teachers WHERE Teachers.tName = '" + tName + "';", null);

        Cursor cursor2 = db.rawQuery("SELECT Courses.idAUTOINCREMENTAUTOINCREMENT FROM Courses WHERE Courses.cName = '" + cName + "';", null);

        if (cursor1.moveToFirst()) {
            cursor1.getString(0);
        }

        if (cursor2.moveToFirst()) {
            cursor2.getString(0);
        }

        db.execSQL("INSERT INTO TeacherCourses (tID, cID, tcName, ctName) VALUES ('"+cursor1+"', '"+cursor2+"', '"+tName+"', '"+cName+"')");

    }

    public void del(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void create(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + "AUTOINCREMENT INTEGER PRIMARY KEY, "
                + S_COL + " INTEGER, "
                + C_COL + " INTEGER, " +
                "FOREIGN KEY (" + S_COL + ") REFERENCES " + S_TABLE_NAME + "( " + S_ID_COL + ")," +
                "FOREIGN KEY (" + C_COL + ") REFERENCES " + C_TABLE_NAME + "( " + C_ID_COL + "));";
    }
}
