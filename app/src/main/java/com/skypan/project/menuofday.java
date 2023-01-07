package com.skypan.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class menuofday extends AppCompatActivity {

    //放品名，要讓用戶菜單抓的
    static List<String> list_Item;
    //放價格，要讓用戶菜單抓的
    static List<String> list_Price;

    //RecyclerView
    private RecyclerView recycler_view;
    //儲存今日菜單的資料
    private MyAdapter adapter;
    //儲存今日菜單的資料，放進MyAdapter類裡
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();


    //元件
    EditText AddDishesEditText;
    EditText AddAmountEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuofday);

        setTitle("今日菜單");


        AddDishesEditText = (EditText) findViewById(R.id.NumberET);
        AddAmountEditText = (EditText) findViewById(R.id.AddAmountEditText);


        // 連結元件
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view_client);


        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        // 設置格線
        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        //添加菜色
        Button Addbt = (Button) findViewById(R.id.Addbt);
        Addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 新增菜色、價格
                String str1 = AddDishesEditText.getText().toString();
                String str2 = AddAmountEditText.getText().toString();

                adapter.addItem(str1,str2);
            }
        });

        // 將資料交給adapter
        adapter = new MyAdapter(arrayList);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(adapter);


        //完成菜單
        Button FinishAddbt = (Button) findViewById(R.id.FinishAddbt);
        FinishAddbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 完成今日菜單並送到客戶訂單
                list_Item = adapter.list_str1_intent();
                list_Price = adapter.list_str2_intent();
                finish();
            }
        });
    }


}