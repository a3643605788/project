package com.skypan.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class register  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //註冊
        Button homebt=(Button)findViewById(R.id.homebt);
        homebt.setOnClickListener(homebtListener);
    }

    private Button.OnClickListener homebtListener=new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };


}
