package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private final Context context;
    private static final String Data_Base_Name = "Users.db";
    private static final int Data_Base_Version = 1;

    private static final String Table_Name = "users_game";
    private static final String Col_ID = "_id";
    private static final String Col_Name = "name";
    private static final String Col_Score = "score";
    private static final String Col_Country = "country";

    public DataBase(@Nullable Context context) {
        super(context, Data_Base_Name, null, Data_Base_Version);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table_Name +
                " (" + Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Col_Name + " TEXT, " +
                Col_Score + " INTEGER, " +
                Col_Country + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public void addUser(String name, Integer score, String country){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Col_Name, name);
        cv.put(Col_Score, score);
        cv.put(Col_Country, country);
        if(db.insert(Table_Name, null, cv) == -1)
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show();

    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + Table_Name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);
        return cursor;
    }


    public void updateData(String row_id, String name, Integer score, String country){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_Name, name);
        cv.put(Col_Score, score);
        cv.put(Col_Country, country);

        if(db.update(Table_Name, cv,"_id=?", new String[]{row_id}) == -1)
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Upd", Toast.LENGTH_SHORT).show();
    }

    public void deleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();

        if(db.delete(Table_Name,"_id=?", new String[]{row_id}) == -1)
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Del", Toast.LENGTH_SHORT).show();
    }
}
