package com.example.db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.PublicKey;

public class Main2Activity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    DatabaseHelper mydb;
    EditText editText,editText2,editText3;
    Button button,button1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mydb=new DatabaseHelper(this);
        editText=findViewById(R.id.et);
        editText2=findViewById(R.id.et1);
        editText3=findViewById(R.id.et2);
        button=findViewById(R.id.btn);
        button1=findViewById(R.id.btn1);
        imageView=findViewById(R.id.iv);
        AddData();
        ViewAll();
        Click();

    }
    public void AddData()
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted=mydb.insertData(editText.getText().toString(),editText2.getText().toString(),editText3.getText().toString());
                if(isInserted ==true)
                {
                    Toast.makeText(Main2Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Main2Activity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void ViewAll()
    {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.getAllData();
                Cursor res=mydb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","No data found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Phone Number :"+res.getString(2)+"\n");
                    buffer.append("Email ID :"+res.getString(3)+"\n\n");
                }
                showMessage("Contact Details",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String message)
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void Click()
    {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(getPackageManager())!=null)
                {
                    startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
                }

            }
        });
    }
}
