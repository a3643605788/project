package com.skypan.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class register  extends AppCompatActivity {

    static HashMap<String,String> hashMap = new HashMap<>();

    EditText register_account;
    EditText register_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        setTitle("店家註冊");


        TextView hinttv = (TextView) findViewById(R.id.hinttv);
        hinttv.setText("");

        register_account = (EditText) findViewById(R.id.register_account);
        register_password = (EditText) findViewById(R.id.register_password);
        EditText password_check = (EditText) findViewById(R.id.password_check);



        Button homebt=(Button)findViewById(R.id.homebt);
        homebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //確認帳密是否有6碼，以及開頭是否為英文

                String account_str = register_account.getText().toString();
                String password_str = register_password.getText().toString();



                if(ErrorHint(account_str) && ErrorHint(password_str)){
                    String password_check_str = password_check.getText().toString();
                    //確認兩次密碼都打一樣
                    if( password_str.equals(password_check_str) ){
                        //註冊
                        Intent intent = new Intent();
                        intent.setClass(register.this,MainActivity.class);

                        hashMap.put(account_str,password_str);

                        // 執行附帶資料的 Intent
                        startActivity(intent);
                    }
                    else{
                        hinttv.setText("密碼不相符");
                    }
                }
                else{
                    hinttv.setText("帳號或密碼格式錯誤");
                }
            }
        });




    }


    //確認帳密是否有6碼，以及開頭是否為英文
    public boolean ErrorHint(String str){
        if(str.length()>5){
            char captial = str.charAt(0);
            if(Character.isLetter(captial)){
                return true;
            }
        }
        return false;
    }


}
