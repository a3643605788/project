package com.skypan.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class menuorder extends AppCompatActivity {

    //RecyclerView
    private RecyclerView recycler_view;
    //儲存購物車的資料
    private ODAdapter adapter;
    //儲存購物車的資料，放進底下 ODAdapter 類裡
    private ArrayList<HashMap<String,String>> mData = new ArrayList<>();
    //用來餵入"訂單處理"的mData
    static HashMap<String,String> hashMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuorder);

        setTitle("訂單處理");


        // 連結元件
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view_client);
        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        // 設置格線
        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        //儲存消費者該筆訂單
        for(int i=0; i<pay.hashMap_payOrderInfo.size() ; i++){
            hashMap.put(i+"",pay.hashMap_payOrderInfo.get(i+"")+ "\n" + "合計: $" + pay.hashMap_sumOfmonny.get(i+""));
            mData.add(i,hashMap);
        }

        // 將資料交給adapter
        adapter = new ODAdapter(mData);
        // 設置adapter給recycler_view
        recycler_view.setAdapter(adapter);


        //返回
        Button returnbt=(Button)findViewById(R.id.returnbt);
        returnbt.setOnClickListener(returnbtListener);



    }

    public class ODAdapter extends RecyclerView.Adapter<ODAdapter.ViewHolder> {

        private ArrayList<HashMap<String,String>> mData;

        ODAdapter(ArrayList<HashMap<String,String>> data) {
            mData = data;
        }

        // 建立ViewHolder
        class ViewHolder extends RecyclerView.ViewHolder{
            // 宣告元件(訂單資訊)
            TextView txtOrderInfo_Input = (TextView) itemView.findViewById(R.id.txtOrderInfo_Input);
            // 宣告元件(訂單編號)
            TextView txtNumber_Input = (TextView) itemView.findViewById(R.id.txtNumber_Input);
            // 宣告元件(完成訂單)
            Button Deletebt = (Button) itemView.findViewById(R.id.Deletebt);
            // 宣告元件(訂單編號)
            EditText NumberET = (EditText) itemView.findViewById(R.id.NumberET);

            ViewHolder(View itemView) {
                super(itemView);



                //完成訂單
                Button Deletebt = (Button) findViewById(R.id.Deletebt);
                Deletebt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText NumberET = (EditText) findViewById(R.id.NumberET);
                        String index_str = NumberET.getText().toString();
                        int index = Integer.parseInt(index_str)-1;

                        //把原本購物車的hashmap，該筆訂單更新成已完成
                        pay.hashMap_payOrderInfo.put(index+"","(已完成)");
                        //menuorder這個activity，複製購物車的把原本購物車的hashmap到這個這個頁面的hashMap，該筆訂單也更新成已完成
                        hashMap.put(index+"","(已完成)");

                        //替換mData第i(index)個值
                        mData.set(index,pay.hashMap_payOrderInfo);
                        notifyItemChanged(index);
                    }
                });
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 連結項目布局檔list_item
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // 設置訂單編號要顯示的內容
            holder.txtNumber_Input.setText((position+1)+"");
            // 設置訂單資訊要顯示的內容
            holder.txtOrderInfo_Input.setText(mData.get(position).get(position+""));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

    }


    //返回
    private Button.OnClickListener returnbtListener=new Button.OnClickListener(){
        public void onClick(View v){
            onPause();
            Intent intent = new Intent();
            intent.setClass(menuorder.this,bossmenu.class);

            // 執行附帶資料的 Intent
            startActivity(intent);
        }
    };



}
