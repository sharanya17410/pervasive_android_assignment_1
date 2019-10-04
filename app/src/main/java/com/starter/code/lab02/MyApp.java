package com.starter.code.lab02;

import android.app.Application;

public class MyApp extends Application {
    PersonalData data=new PersonalData();
    Memo memo=new Memo();
    public PersonalData getData(){
        return this.data;
    }
    public Memo getMemo(){
        return this.memo;
    }
//    public void setData(PersonalData pd){
//        this.data=data;
//    }
}
