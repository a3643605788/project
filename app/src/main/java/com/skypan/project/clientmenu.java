package com.skypan.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class clientmenu extends AppCompatActivity {



    //RecyclerView
    private RecyclerView recycler_view;
    //儲存購物車的資料
    private clientmenu_MyAdapter adapter;
    //儲存購物車的資料，放進clientmenu_MyAdapter類裡
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    //從業者今日菜單抓的品名
    List<String> list_Item = menuofday.list_Item;
    //從業者今日菜單抓的價格
    List<String> list_Price = menuofday.list_Price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientmenu);

        setTitle("菜單");
        // 連結元件
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view_client);
        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        // 設置格線
        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        //品茗跟價格都放進HashMap，跑完迴圈再放進arrayList
        for (int i = 0 ; i<list_Item.size() ; i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("Dishes",list_Item.get(i));
            hashMap.put("Amount",list_Price.get(i));
            arrayList.add(hashMap);
        }



        // 將資料(arrayList)交給adapter
        adapter = new clientmenu_MyAdapter(arrayList);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(adapter);


        //存放菜名及價格，為了購物車頁面判斷消費者有無添購該品項
        for (int i = 0 ; i<list_Item.size() ; i++){
            adapter.setItemName(i,list_Item.get(i));
            adapter.setPrice(i,Integer.parseInt(list_Price.get(i)));
        }

        //查看購物車
        Button listbt=(Button)findViewById(R.id.listbt);
        listbt.setOnClickListener(listbtListener);




    }


    //查看購物車
    private Button.OnClickListener listbtListener=new Button.OnClickListener(){
        public void onClick(View v){

            //傳送品名價格到 pay
            //用迴圈挑出客人有買的品項，逐一放入這三個 ArrayList ，再用 intent 傳送到購物車

            //儲存菜色數量
            ArrayList<Integer> array_Sum = new ArrayList<>();
            //儲存菜名
            ArrayList<String> array_Item = new ArrayList<>();
            //儲存菜價
            ArrayList<Integer> array_Price = new ArrayList<>();


            Intent intent = new Intent();
            intent.setClass(clientmenu.this,pay.class);
            Bundle bundle = new Bundle();


            for (int i = 0 ; i<list_Item.size() ; i++){
                //如果該菜色數量>0 印出
                if (adapter.getItemSum(i) > 0){
                    array_Sum.add(adapter.getItemSum(i));
                    array_Item.add(adapter.getItemName(i));
                    array_Price.add(adapter.getPrice(i));
                }
            }

            bundle.putIntegerArrayList("Sum",array_Sum);
            bundle.putStringArrayList("ItemName",array_Item);
            bundle.putIntegerArrayList("Price",array_Price);


            intent.putExtras(bundle);
            startActivity(intent);


            array_Sum.clear();
            array_Item.clear();
            array_Price.clear();
        }
    };


}
