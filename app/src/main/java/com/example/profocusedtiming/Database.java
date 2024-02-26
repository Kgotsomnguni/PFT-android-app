package com.example.profocusedtiming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(firstname text,surname text,username text,email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table TimeSheets(Username text,Taskname text,Date Date,StartTime text,Endtime text,Description text,image text, Category text)";
        sqLiteDatabase.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String Name,String Surname,String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("firstname",Name);
        cv.put("surname",Surname);
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();

    }
    public int login(String username, String Password){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]= Password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
        if(c.moveToFirst())
        {
            result=1;
        }
        return result;
    }

    public void CreateEntry(String username,String TaskName,String Date, String StartTime, String EndTime,String Description,String Image,String Categoryname){
        ContentValues cv = new ContentValues();
        cv.put("firstname",username);
        cv.put("TaskName",TaskName);
        cv.put("Date",Date);
        cv.put("StartTime",StartTime);
        cv.put("EndTime",EndTime);
        cv.put("Description",Description);
        cv.put("Image",Image);
        cv.put("Categoryname",Categoryname);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("TimeSheets",null,cv);
        db.close();
    }

    public int CheckTimesheet(String username, String Taskname){
        int result=0;
        String str[] = new String[2];
                str[0] = username;
                str[1] =Taskname;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from Timesheets where username=? and Taskname",str);
        if(c.moveToFirst()){
             result=1;
        }
        db.close();
        return result;
    }
   /* public int CreateTimeSheet(String Taskname, String Password){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]= Password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
        if(c.moveToFirst())
        {
            result=1;
        }
        return result;
    }*/
}
