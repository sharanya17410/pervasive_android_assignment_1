package com.starter.code.lab02;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonalData {
    String name;
    String email;
    String phoneNumber;
    private  SQLiteDatabase database;

    public PersonalData(){


    }
    public void setDatabase(SQLiteOpenHelper databaseHelper){
        database = databaseHelper.getWritableDatabase();
    }
    public SQLiteDatabase getDatabase(){
        return this.database;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
