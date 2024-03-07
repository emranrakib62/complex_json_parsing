package com.example.complexjsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
ProgressBar progress;
TextView textview;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress=findViewById(R.id.progress);
        textview=findViewById(R.id.textview);
String url="https://emranrakib.000webhostapp.com/Apps/info.json";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject Response) {
progress.setVisibility(View.GONE);
                Log.d("serverRes",Response.toString() );
                try {
                    String Name= Response.getString("Name");
                    String Mobile= Response.getString("Mobile");
                    textview.append(Name);
                    textview.append(Mobile);
                    JSONArray jsonArray=Response.getJSONArray("vedio");
                    for(int x=0;x<=jsonArray.length();x++){

                        JSONObject jsonObject=jsonArray.getJSONObject(x);
                        String title=jsonObject.getString("title");
                        String vedio_id=jsonObject.getString("vedio_id");
                        textview.append(title);
                        textview.append(vedio_id);

                    }





                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {




            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);




    }
}