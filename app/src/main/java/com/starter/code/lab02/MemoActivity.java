package com.starter.code.lab02;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MemoActivity extends AppCompatActivity {
    EditText title;
    EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        title = findViewById(R.id.title);
        content=findViewById(R.id.content);
    }
    public void store(View view){
        String titleData=title.getText().toString();
        String contentData=content.getText().toString();


        Memo memo=((MyApp)getApplicationContext()).getMemo();
        memo.setContent(contentData);
        memo.setTitle(titleData);

        MemoDatabaseHandler memodbHandler=new MemoDatabaseHandler(this);

        memodbHandler.insertMemo(memo);
        List<Memo> memoList=memodbHandler.getAllMemos();
        memo.setDatabase(memodbHandler);
        for(int i=0;i<memoList.size();i++){
            Log.d("Memos: Title: ",memoList.get(i).getTitle()+" Content:"+memoList.get(i).getContent());
        }
        Intent intent = new Intent(MemoActivity.this, Main2Activity.class);

        // add data to intent and pass it to next screen
       // intent.putExtra("Title", titleData);

        startActivity(intent);
    }
}
