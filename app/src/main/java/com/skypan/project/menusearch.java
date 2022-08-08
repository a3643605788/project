package com.skypan.project;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class menusearch extends AppCompatActivity {

    final static String TAG = "資料(Json)";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menusearch);


        Button Getbt = (Button) findViewById(R.id.searchbt);
        /**傳送GET*/
        Getbt.setOnClickListener(v -> sendGET());
    }

    private void sendGET() {

        TextView searchview = (TextView) findViewById(R.id.searchview);
        EditText search = (EditText) findViewById(R.id.searchedittext);
        String search_str = search.getText().toString();

        if(search_str == ""){
            searchview.setText("請輸入品名!");
        }

        /**建立連線*/
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        /**設置傳送需求*/
        Request request = new Request.Builder()
                .url("https://data.coa.gov.tw/api/v1/AgriProductsTransType/?Start_time=111.04.01&End_time=111.04.20")
//                .header("Cookie","")//有Cookie需求的話則可用此發送
//                .addHeader("","")//如果API有需要header的則可使用此發送
                .build();
        /**設置回傳*/
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                /**如果傳送過程有發生錯誤*/
                searchview.setText(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                /**取得回傳*/
                //searchview.setText("GET回傳：\n" + response.body().string());
                //Log.d(TAG, response.body().string());



                try{

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("Data");
//
                    String string_CropName ;
                    String CropNameInfo = "";
//
                    for(int i=0 ; i<jsonArray.length() ; i++){
//                        Log.d(TAG,jsonArray.getJSONObject(i).getString("CropName"));
//                        Log.d(TAG,jsonArray.getJSONObject(i).getString("MarketCode"));
//                        Log.d(TAG,jsonArray.getJSONObject(i).getString("MarketName"));
                        string_CropName = jsonArray.getJSONObject(i).getString("CropName");

//                        //測試輸出
//                        if(string_CropName.contains("釋迦")){
//                            Log.d(TAG,"品名:"+jsonArray.getJSONObject(i).getString("CropName"));
//                            Log.d(TAG,"市場名稱:"+jsonArray.getJSONObject(i).getString("MarketName"));
//                            Log.d(TAG,"高價:"+jsonArray.getJSONObject(i).getString("Upper_Price"));
//                            Log.d(TAG,"中價:"+jsonArray.getJSONObject(i).getString("Middle_Price"));
//                            Log.d(TAG,"低價:"+jsonArray.getJSONObject(i).getString("Lower_Price"));
//                            Log.d(TAG,"平均價:"+jsonArray.getJSONObject(i).getString("Avg_Price"));
//                        }
//
//                        //app輸出
//                        if(string_CropName.contains("釋迦")){
//                            CropNameInfo =  CropNameInfo +
//                                    "品名:"+jsonArray.getJSONObject(i).getString("CropName")+"\n"+
//                                    "市場名稱:"+jsonArray.getJSONObject(i).getString("MarketName")+"\n"+
//                                    "高價:"+jsonArray.getJSONObject(i).getString("Upper_Price")+"\n"+
//                                    "中價:"+jsonArray.getJSONObject(i).getString("Middle_Price")+"\n"+
//                                    "低價:"+jsonArray.getJSONObject(i).getString("Lower_Price")+"\n"+
//                                    "平均價:"+jsonArray.getJSONObject(i).getString("Avg_Price")+"\n\n";
//
//                        }

                        //測試輸出
                        if(string_CropName.contains(search_str)){
                            Log.d(TAG,"品名:"+jsonArray.getJSONObject(i).getString("CropName"));
                            Log.d(TAG,"市場名稱:"+jsonArray.getJSONObject(i).getString("MarketName"));
                            Log.d(TAG,"高價:"+jsonArray.getJSONObject(i).getString("Upper_Price"));
                            Log.d(TAG,"中價:"+jsonArray.getJSONObject(i).getString("Middle_Price"));
                            Log.d(TAG,"低價:"+jsonArray.getJSONObject(i).getString("Lower_Price"));
                            Log.d(TAG,"平均價:"+jsonArray.getJSONObject(i).getString("Avg_Price"));
                        }

                        //app輸出
                        if(string_CropName.contains(search_str)){
                            CropNameInfo =  CropNameInfo +
                                    "品名:"+jsonArray.getJSONObject(i).getString("CropName")+"\n"+
                                    "市場名稱:"+jsonArray.getJSONObject(i).getString("MarketName")+"\n"+
                                    "高價:"+jsonArray.getJSONObject(i).getString("Upper_Price")+"\n"+
                                    "中價:"+jsonArray.getJSONObject(i).getString("Middle_Price")+"\n"+
                                    "低價:"+jsonArray.getJSONObject(i).getString("Lower_Price")+"\n"+
                                    "平均價:"+jsonArray.getJSONObject(i).getString("Avg_Price")+"\n\n";

                        }

                    }
                    searchview.setText(CropNameInfo);
                }
                catch (JSONException e){
                    Log.d(TAG,"error");
                }

            }
        });
    }
}
