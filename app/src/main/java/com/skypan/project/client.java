package com.skypan.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client);

        setTitle("訪客登入");

        //返回
        Button returnbt=(Button)findViewById(R.id.returnbt);
        returnbt.setOnClickListener(returnListener);

        //我的訂單
        Button My_Orderbt=(Button)findViewById(R.id.My_Orderbt);
        My_Orderbt.setOnClickListener(My_OrderListener);

        //菜單
        Button clientmenubt=(Button)findViewById(R.id.clientmenubt);
        clientmenubt.setOnClickListener(clientmenuListener);
    }

    //返回
    private Button.OnClickListener returnListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(client.this,MainActivity.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

    //我的訂單
    private Button.OnClickListener My_OrderListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(client.this,My_Order.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

    //菜單
    private Button.OnClickListener clientmenuListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(client.this,clientmenu.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };
}
