package com.example.notewithhand;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Button btnfinish= (Button) findViewById(R.id.finish_button);
        Button btnforgo=(Button) findViewById(R.id.forgo_button);
        editText = (EditText) findViewById(R.id.edit_text);
        btnfinish.setOnClickListener(this);
        btnforgo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.finish_button:
                MYSQLiteOpenHelper dbHelper;
                dbHelper = new MYSQLiteOpenHelper(this,"Record.db",null,1);
                String note= editText.getText().toString();
                SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                String time= formatter.format(curDate);
                Intent intent=new Intent(CreateActivity.this,MainActivity.class);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("note",note);
                values.put("time",time);
                db.insert("record",null,values);
                dbHelper.close();
                startActivity(intent);
                break;
            case R.id.forgo_button:
                Intent intent1=new Intent(CreateActivity.this,MainActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
