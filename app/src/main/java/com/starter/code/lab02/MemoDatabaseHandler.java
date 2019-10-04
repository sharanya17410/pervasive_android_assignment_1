package com.starter.code.lab02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class MemoDatabaseHandler extends SQLiteOpenHelper {

    /** Version number to keep track of database version.
     * If this version number is changed then SQLite engine will
     * assume that the database has underwent changes and tries
     * update the necessary tables.
     */
    private static final int DATABASE_VERSION = 1;

    // Name to given to the database
    private static final String DATABASE_NAME = "memo_db";

    public MemoDatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // To initialize with necessary tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE memo(title String, content String);");
    }
    public void clearMemo() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM memo;");
    }
    // This method is triggered when the table's schema is updated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS memo");
        onCreate(db);
    }
    public Memo getMemo(String content){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM memo where title='"+content+"';", null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        Memo memo = new Memo();
        // Looping through the records, creating user object and storing in the userslist
        do{

            memo.title = cursor.getString(0);
            memo.content = cursor.getString(1);

        }while(cursor.moveToNext());
      return memo;
    }
    public void updateMemo(String content, String title,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE memo SET title='"+title+"',content='"+content+"' WHERE title='"+id+"' ");
    }
    public void insertMemo(Memo memo){
        // Creating and opening a writable SQLiteDatabase object
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating the content which maps the table's attributes with the values
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", memo.getTitle());
        contentValues.put("content", memo.getContent());

        // Inserting into the database
        db.insert("memo", null, contentValues);

        // Closing the connection
        db.close();
    }

    public List<Memo> getAllMemos(){
        // Creating and opening a Readable SQLiteDatabase object
        SQLiteDatabase db = this.getReadableDatabase();

        // List to store all the users
        List<Memo> memoList = new ArrayList<Memo>();

        // Querying the databse
        Cursor cursor = db.rawQuery("SELECT * FROM memo;", null);
           // db.execSQL("Delete from memo;");
        // Checking if the cursor's data is null
        if(cursor!=null){
            cursor.moveToFirst();
        }

        // Looping through the records, creating user object and storing in the userslist
        do{
            Memo memo = new Memo();
            memo.title = cursor.getString(0);
            memo.content = cursor.getString(1);
            memoList.add(memo);
        }while(cursor.moveToNext());

        // Returning the users list
        return memoList;
    }


}