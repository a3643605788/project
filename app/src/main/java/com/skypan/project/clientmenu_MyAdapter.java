package com.skypan.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class clientmenu_MyAdapter extends RecyclerView.Adapter<clientmenu_MyAdapter.ViewHolder> {


    public clientmenu_MyAdapter(){

    }


    ArrayList<HashMap<String,String>> arrayList;
    HashMap<String,String> hashMap = new HashMap<>();


    clientmenu_MyAdapter(ArrayList<HashMap<String,String>> data) {
        arrayList = data;
    }


    //儲存總共有幾個菜色
    int arrayList_size;
    //客人點的菜色數量
    int ItemSum[] = new int[arrayList_size+10];
    //菜名
    String ItemName[] = new String[arrayList_size+10];
    //菜價
    int Price[] = new int[arrayList_size+10];

    // 建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtItemInput = (TextView) itemView.findViewById(R.id.txtNumber_Input);
        TextView txtPriceInput = (TextView) itemView.findViewById(R.id.txtOrderInfo_Input);
        EditText SumET = (EditText) itemView.findViewById(R.id.NumberET);



        //餐點份量總數
        int amount = 0;


        ViewHolder(View itemView) {
            // 宣告元件
            super(itemView);


            // 點擊項目時
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast
                    Toast.makeText(view.getContext(),
                            "品名: " +txtItemInput.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });


            //減少點餐份量
            Button reducebt = (Button) itemView.findViewById(R.id.reducebt);
            reducebt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount--;
                    //在陣列紀錄該菜色的點餐數量
                    reduceItemSum(getAdapterPosition());
                    SumET.setText("" + amount);
                }
            });


            //增加點餐份量
            Button addbt = (Button) itemView.findViewById(R.id.addbt);
            addbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    amount++;
                    //在陣列紀錄該菜色的點餐數量
                    addItemSum(getAdapterPosition());
                    SumET.setText("" + amount);
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 連結項目布局檔list_item

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clientmenu_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 設置txtItem要顯示的內容

        String str1 = arrayList.get(position).get("Dishes");
        String str2 = arrayList.get(position).get("Amount");
        holder.txtItemInput.setText(str1);
        holder.txtPriceInput.setText(str2);
    }

    @Override
    public int getItemCount() {
        // 回傳整個 Adapter 包含幾筆資料。
        arrayList_size = arrayList.size();
        return arrayList.size();
    }


    //儲存有購買的菜名
    public void setItemName(int index,String ItemName){
        this.ItemName[index] = ItemName;
    }
    //回傳購買的菜名
    public String getItemName(int index){
        return ItemName[index];
    }


    //儲存有購買的菜價
    public void setPrice(int index,int Price){
        this.Price[index] = Price;
    }
    //回傳購買的菜價
    public Integer getPrice(int index){
        return Price[index];
    }


    //紀錄菜色數量(-)
    public void reduceItemSum(int index){
        ItemSum[index]--;
    }
    //紀錄菜色數量(+)
    public void addItemSum(int index){
        ItemSum[index]++;
    }
    //clientmenu_MyAdapter 抓取菜色數量
    public Integer getItemSum(int index){
        return ItemSum[index];
    }



}