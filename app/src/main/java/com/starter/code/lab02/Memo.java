package com.starter.code.lab02;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Memo {
    String title;
    String content;
    private  MemoDatabaseHandler database;

    Memo(){

    }
    public void setDatabase(MemoDatabaseHandler databaseHelper){
        database = databaseHelper;
    }
    public MemoDatabaseHandler getDatabase(){
        return this.database;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}

