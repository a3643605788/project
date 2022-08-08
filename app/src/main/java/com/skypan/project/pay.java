package com.skypan.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class pay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);

        //送出訂單
        Button paybt=(Button)findViewById(R.id.paybt);
        paybt.setOnClickListener(paybtListener);

    }

    private Button.OnClickListener paybtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(pay.this,clientmenu.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
            finish();
        }
    };
}
