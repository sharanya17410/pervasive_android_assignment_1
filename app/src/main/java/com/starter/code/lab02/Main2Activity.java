package com.starter.code.lab02;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    TextView name;
    TextView phone;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        Intent intent = getIntent();
       PersonalData data = ((MyApp)getApplicationContext()).getData();
        System.out.println(data.getName()+"--------------------------------");
        String name1 = intent.getStringExtra("name");
        String phone1 = intent.getStringExtra("phone");
        String email1 = intent.getStringExtra("email");
        String title=intent.getStringExtra("title");

        name.setText("Name : "+data.getName());
        phone.setText("Phone: "+data.getPhoneNumber());
        email.setText("Email : "+data.getEmail());
        ListView lv = (ListView) findViewById(R.id.memoList);

        //Items in your list
        Memo memo=((MyApp)getApplicationContext()).getMemo();
        MemoDatabaseHandler db;
        db = memo.getDatabase();
       // db.clearMemo();
        List<Memo> ans=new ArrayList<>();
        if(db!=null)
            ans=db.getAllMemos();
    if(ans.size()>0){
        //String[] values = new String[] { "BMW", "MERC", "LAMBO", "FERRARI" };
        String values[]=new String[ans.size()];
        for(int i=0;i<ans.size();i++){
            values[i]=(ans.get(i).getTitle());
        }
        //Adapater that plugs in your values into the list view
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        //setting the adapter
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main2Activity.this, EditMemoActivity.class);
                intent.putExtra("Title",adapter.getItem(position));
                Toast.makeText(getBaseContext(),adapter.getItem(position),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }


    }
    public void nextScreen(View view){
        Intent intent = new Intent(Main2Activity.this, MemoActivity.class);
        startActivity(intent);

    }
}
