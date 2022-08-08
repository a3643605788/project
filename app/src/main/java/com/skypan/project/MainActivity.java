package com.skypan.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //登入
        Button loginbt=(Button)findViewById(R.id.home_loginbt);
        loginbt.setOnClickListener(loginbtListener);

        //店家註冊
        Button registerbt=(Button)findViewById(R.id.registerbt);
        registerbt.setOnClickListener(registerbtListener);

        //訪客登入
        Button clientbt=(Button)findViewById(R.id.clientbt);
        clientbt.setOnClickListener(clientbtListener);


    }

    private Button.OnClickListener loginbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,bossmenu.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
            //finish();
        }
    };

    private Button.OnClickListener registerbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,register.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

    private Button.OnClickListener clientbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,clientmenu.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

}