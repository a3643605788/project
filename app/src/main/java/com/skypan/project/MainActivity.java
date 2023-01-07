package com.skypan.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //帳號
    static String login_account_str;
    //密碼
    static String login_password_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("JKB呷卡飽");

        TextView hinttv = (TextView) findViewById(R.id.hinttv);
        hinttv.setText("");

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

    //登入
    private Button.OnClickListener loginbtListener=new Button.OnClickListener(){
        public void onClick(View v){


            EditText login_account = (EditText) findViewById(R.id.login_account);;
            EditText login_password = (EditText) findViewById(R.id.login_password);;

            login_account_str = login_account.getText().toString();
            login_password_str = login_password.getText().toString();


            //判斷帳密有沒有輸入 trim():去頭尾空格
            if(login_account_str.trim().length()==0 || login_password_str.trim().length()==0){
                TextView hinttv = (TextView) findViewById(R.id.hinttv);
                hinttv.setText("帳號或密碼未輸入");
            }
            else{
                //判斷輸入的帳號有沒有對應的密碼，沒有的話就是未註冊
                if(register.hashMap.get(login_account_str) == null){
                    TextView hinttv = (TextView) findViewById(R.id.hinttv);
                    hinttv.setText("此帳號尚未註冊");
                }
                //判斷帳密是不是對的，是的話就登入
                else{
                    if(register.hashMap.get(login_account_str).equals(login_password_str)){
                        Intent intent=new Intent();
                        intent.setClass(MainActivity.this,bossmenu.class);

                        // 執行附帶資料的 Intent
                        startActivity(intent);
                    }
                    else{
                        TextView hinttv = (TextView) findViewById(R.id.hinttv);
                        hinttv.setText("帳號或密碼輸入錯誤");
                    }
                }


            }




        }
    };

    //店家註冊
    private Button.OnClickListener registerbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,register.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

    //訪客登入
    private Button.OnClickListener clientbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,client.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };

}