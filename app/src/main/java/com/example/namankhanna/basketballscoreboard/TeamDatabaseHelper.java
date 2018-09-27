package com.example.namankhanna.basketballscoreboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TeamDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Teams.db";
    public static final String TABLE_NAME = "TeamNames";
    public static final String COL_0 = "Id";
    public static final String COL_1 = "TeamA";
    public static final String COL_2 = "TeamB";

    public TeamDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create table " + TABLE_NAME + "(" +
                COL_0 + " Integer primary key autoincrement, " +
                COL_1 + " Text," +
                COL_2 + " Text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean writeData(String team1, String team2) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, team1);
        contentValues.put(COL_2, team2);
        long res = database.insert(TABLE_NAME, null, contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }

    public Cursor readData() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + TABLE_NAME, null);
        return cursor;
    }
}
