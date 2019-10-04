package com.starter.code.lab02;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText phone,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //   res/layout/activity_main.xml
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email=findViewById(R.id.email);
    }

    public void doSomething(View view){
        String name2 = name.getText().toString();
        String number = phone.getText().toString();
        String email2=email.getText().toString();

        Toast.makeText(MainActivity.this, name + " : " + number, Toast.LENGTH_LONG).show();
        PersonalData data=new PersonalData();
        data.setName(name2);
        data.setEmail(email2);
        data.setPhoneNumber(number);
        PersonalData check = ((MyApp)getApplicationContext()).getData();
        check.setName(name2);
        check.setPhoneNumber(number);
        check.setEmail(email2);

        // MyApp app=new MyApp();
        PersonalDataDatabaseHandler datadbHandler=new PersonalDataDatabaseHandler(this);
        check.setDatabase(datadbHandler);
        datadbHandler.insertData(check);
        PersonalData savedData=datadbHandler.getAllPersonalData();

        //app.setData(data);
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

        // add data to intent and pass it to next screen
        intent.putExtra("name",  "Name :  : " + name2);
        intent.putExtra("phone",  "Phone Number : " + number);
        intent.putExtra("email",  "Email ID : " + email2);


        startActivity(intent);

    }

}
