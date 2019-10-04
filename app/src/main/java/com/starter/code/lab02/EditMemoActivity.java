package com.starter.code.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditMemoActivity extends AppCompatActivity {
    EditText title;
    EditText content;
    String title1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo);
        title = findViewById(R.id.title);
        content=findViewById(R.id.content);
        Intent intent=getIntent();
       title1=intent.getStringExtra("Title");
        title.setText(title1);
        Memo memo=((MyApp)getApplicationContext()).getMemo();
        MemoDatabaseHandler db;
        db = memo.getDatabase();
        Memo newMemo=db.getMemo(title1);
        content.setText(newMemo.getContent());
       // memo.setContent(contentData);
       // memo.setTitle(titleData);
    }
    public void editMemo(View view){
        Memo memo=((MyApp)getApplicationContext()).getMemo();
        MemoDatabaseHandler db;
        db = memo.getDatabase();
      //  Memo newMemo=db.getMemo(title1);
        String title2 = title.getText().toString();
        String content2 = content.getText().toString();
       // String email2=email.getText().toString();

        db.updateMemo(content2,title2,title1);
        Intent intent = new Intent(EditMemoActivity.this, Main2Activity.class);
        startActivity(intent);
    }
}
