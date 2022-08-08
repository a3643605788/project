package com.skypan.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class clientmenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientmenu);

        //查看購物車
        Button listbt=(Button)findViewById(R.id.listbt);
        listbt.setOnClickListener(listbtListener);

    }

    private Button.OnClickListener listbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(clientmenu.this,pay.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };


}
