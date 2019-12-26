package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import static android.os.Build.ID;
import static android.provider.Contacts.SettingsColumns.KEY;
import static java.sql.Types.INTEGER;
import static java.text.Collator.PRIMARY;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="REG";
    public static final String COL_2="COLLAGE";
    public static final String COL_3="BRANCH";
    public static final String COL_4="SEC";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " +TABLE_NAME+" (REG INTEGER PRIMARY KEY AUTOINCREMENT,COLLAGE TEXT,BRANCH TEXT,SEC INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
   sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
   onCreate(sqLiteDatabase);
    }
    public boolean insertData(String COLLAGE,String BRANCH,String SEC)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,COLLAGE);
        contentValues.put(COL_3,BRANCH);
        contentValues.put(COL_4,SEC);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
            return true;

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
