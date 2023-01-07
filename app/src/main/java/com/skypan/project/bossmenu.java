package com.skypan.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class bossmenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bossmenu);

        setTitle("功能選項 : Hi," + MainActivity.login_account_str);

        //批發菜價搜尋
        Button menusearchbt=(Button)findViewById(R.id.menusearchbt);
        menusearchbt.setOnClickListener(menusearchbtListener);

        //今日菜單
        Button menuofdaybt=(Button)findViewById(R.id.menuofdaybt);
        menuofdaybt.setOnClickListener(menuofdaybtListener);

        //訂單處理
        Button menuorderbt=(Button)findViewById(R.id.menuorderbt);
        menuorderbt.setOnClickListener(menuorderbtListener);

        //返回
        Button returnbt=(Button)findViewById(R.id.returnbt);
        returnbt.setOnClickListener(returnListener);
    }

    //批發菜價搜尋
    private Button.OnClickListener menusearchbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(bossmenu.this, menusearch.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

    //今日菜單
    private Button.OnClickListener menuofdaybtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(bossmenu.this,menuofday.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

    //訂單處理
    private Button.OnClickListener menuorderbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(bossmenu.this, menuorder.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

    //返回
    private Button.OnClickListener returnListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(bossmenu.this,MainActivity.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };
}
