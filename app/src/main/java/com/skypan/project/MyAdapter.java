package com.skypan.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    public MyAdapter(){

    }

    ArrayList<HashMap<String,String>> arrayList;
    HashMap<String,String> hashMap = new HashMap<>();

    //儲存品名陣列
    List<String> list_str1 = new ArrayList<>();
    //儲存價格陣列
    List<String> list_str2 = new ArrayList<>();

    public MyAdapter(ArrayList<HashMap<String,String>> data) {

        arrayList = data;
    }


    // 建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{

        Button btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
        TextView txtItemInput = (TextView) itemView.findViewById(R.id.txtNumber_Input);
        TextView txtPriceInput = (TextView) itemView.findViewById(R.id.txtOrderInfo_Input);


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


            // 店家移除菜色
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 移除項目，getAdapterPosition為點擊的項目位置
                    removeItem(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 連結項目布局檔list_item

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menuofday_list_item, parent, false);
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
        return arrayList.size();
    }


    // 新增項目
    public void addItem(String str1,String str2){
        list_str1.add(str1);
        list_str2.add(str2);

        hashMap.put("Dishes",str1);
        hashMap.put("Amount",str2);
        arrayList.add(hashMap);
        notifyItemInserted(getItemCount());
    }

    // 刪除項目
    public void removeItem(int position){
        list_str1.remove(position);
        list_str2.remove(position);

        arrayList.remove(position);
        notifyItemRemoved(position);
    }

    //回傳品名
    public List<String> list_str1_intent(){
        return list_str1;
    }

    //回傳價格
    public List<String> list_str2_intent(){
        return list_str2;
    }

}