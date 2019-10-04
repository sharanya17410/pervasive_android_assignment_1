package com.starter.code.lab02;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonalDataDatabaseHandler extends SQLiteOpenHelper {

    /** Version number to keep track of database version.
     * If this version number is changed then SQLite engine will
     * assume that the database has underwent changes and tries
     * update the necessary tables.
     */
    private static final int DATABASE_VERSION = 1;

    // Name to given to the database
    private static final String DATABASE_NAME = "personalData_db";

    public PersonalDataDatabaseHandler(MainActivity context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // To initialize with necessary tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE personalData(name String, email String,phone String);");
    }

    // This method is triggered when the table's schema is updated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS personalData");
        onCreate(db);
    }

    public void insertData(PersonalData data){
        // Creating and opening a writable SQLiteDatabase object
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating the content which maps the table's attributes with the values
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", data.getName());
        contentValues.put("email", data.getEmail());
        contentValues.put("phone", data.getPhoneNumber());


        // Inserting into the database
        db.insert("personalData", null, contentValues);

        // Closing the connection
        db.close();
    }

    public PersonalData getAllPersonalData(){
        // Creating and opening a Readable SQLiteDatabase object
        SQLiteDatabase db = this.getReadableDatabase();

        // List to store all the users
        PersonalData data=new PersonalData();

        // Querying the databse
        Cursor cursor = db.rawQuery("SELECT * FROM personalData;", null);

        // Checking if the cursor's data is null
        if(cursor!=null){
            cursor.moveToFirst();
        }

        // Looping through the records, creating user object and storing in the userslist
        do{
            data.name= cursor.getString(0);
            data.email=cursor.getString(1);
            data.phoneNumber=cursor.getString(2);

        }while(cursor.moveToNext());

        // Returning the users list
        return data;
    }


}