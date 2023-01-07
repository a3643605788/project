package com.skypan.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class pay extends AppCompatActivity {

    //該訂單的資料，用HashMap好餵給My_Order
    static HashMap<String,String> hashMap_payOrderInfo = new HashMap<>();
    static HashMap<String,String> hashMap_sumOfmonny = new HashMap<>();
    //編號
    static int int_index=0;
    //訂單資料
    static String txtOrderInfo="";
    //合計
    static int txtsumOfmonny=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);

        setTitle("購物車");



        TextView txtOrderData_Input = (TextView) findViewById(R.id.txtOrderData_Input);
        TextView txtSum_Input = (TextView) findViewById(R.id.txtSum_Input);

        //呼叫textview捲軸的方法
        txtOrderData_Input.setMovementMethod(ScrollingMovementMethod.getInstance());

        //儲存菜色數量
        ArrayList<Integer> array_Sum = new ArrayList<>();
        //儲存菜名
        ArrayList<String> array_Item = new ArrayList<>();
        //儲存菜價
        ArrayList<Integer> array_Price = new ArrayList<>();
        array_Sum = getIntent().getExtras().getIntegerArrayList("Sum");
        array_Item = getIntent().getExtras().getStringArrayList("ItemName");
        array_Price = getIntent().getExtras().getIntegerArrayList("Price");




        txtOrderInfo = "";
        txtsumOfmonny=0;
        //迴圈儲存訂單資料和總額，再放進購物車裡顯示
        for(int i=0 ; i<array_Item.size() ; i++){
            //數量*品項名    $品項價格
            txtOrderInfo = txtOrderInfo + array_Sum.get(i) + "\u3000"  + " *" +
                            "\u3000" + array_Item.get(i) + "\u3000\u3000" + "$" +
                            array_Price.get(i) + "\n";
            //合計    數量*品項價格
            txtsumOfmonny = txtsumOfmonny + (array_Sum.get(i)) * (array_Price.get(i));
        }
        txtOrderData_Input.setText(txtOrderInfo);
        txtSum_Input.setText(txtsumOfmonny+"\u3000TWD");


        //送出購物車訂單
        Button Paybt = (Button) findViewById(R.id.Paybt);
        Paybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                intent.setClass(pay.this,My_Order.class);

                //如果訂單不超過200筆，訂單編號++，否則歸零
                if(int_index>200){
                    int_index = 0;
                    hashMap_payOrderInfo.put(int_index+"",txtOrderInfo);
                    hashMap_sumOfmonny.put(int_index+"",txtsumOfmonny+"");
                    int_index ++;
                }

                else{
                    hashMap_payOrderInfo.put(int_index+"",txtOrderInfo);
                    hashMap_sumOfmonny.put(int_index+"",txtsumOfmonny+"");
                    int_index++;
                }

                // 執行附帶資料的 Intent
                startActivity(intent);
                finish();
            }
        });

        //取消購物車菜單
        Button Cancelbt = (Button) findViewById(R.id.Cancelbt);
        Cancelbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

}
