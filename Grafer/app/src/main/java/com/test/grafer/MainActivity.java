package com.test.grafer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView MTextViewResult;
    private RequestQueue mQuene;
    private TextInputEditText url;


    private ArrayList<String> vrednosti;
    private ArrayList<String> imena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        url = findViewById(R.id.urlImputFeald);
        url.setText("https://raw.githubusercontent.com/ness993/jsondata/master/data.json");
         Button barChart = findViewById(R.id.barchartButton);
         Button load =findViewById(R.id.loadButton);
         Button pie =findViewById(R.id.pieButton);
         Button radar =findViewById(R.id.rcButton);
         Button linechart =findViewById(R.id.linechartbutton);


        imena = new ArrayList<>();
        vrednosti= new ArrayList<>();
        MTextViewResult = findViewById(R.id.text_view_result);
       mQuene = Volley.newRequestQueue(this);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParser(url.getText().toString());
                MTextViewResult.setText("");
                MTextViewResult.setText("Učitano");

            }});


        String urla=url.getText().toString();
        jsonParser(urla);
       barChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jsonParser(url.getText().toString());
                Intent i = new Intent(MainActivity.this, barchart.class);
                i.putExtra("imena", imena);
                i.putExtra("vrijednosti",vrednosti);
                startActivity(i);
            }
        });
       pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jsonParser(url.getText().toString());
                Intent i = new Intent(MainActivity.this, piechart.class);
                i.putExtra("imena", imena);
                i.putExtra("vrijednosti",vrednosti);
                startActivity(i);
            }
        });

       radar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               jsonParser(url.getText().toString());
               Intent i = new Intent(MainActivity.this, radarChart.class);
               i.putExtra("imena", imena);
               i.putExtra("vrijednosti",vrednosti);
               startActivity(i);
           }
       });

        linechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jsonParser(url.getText().toString());
                Intent i = new Intent(MainActivity.this, linechart.class);
                i.putExtra("vrijednosti",vrednosti);
                startActivity(i);
            }
        });





    }
        private void jsonParser (@Nullable String url ){
       // MTextViewResult.append("jsonParser \n\n")
            if (url.isEmpty()){
                 url = "https://raw.githubusercontent.com/ness993/jsondata/master/data.json";
            }
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                          //   MTextViewResult.append("try block \n\n");
                            JSONArray jsonArray = response.getJSONArray("lista");
                            for(int i =0; i<jsonArray.length();i++){
                                JSONObject podatci = jsonArray.getJSONObject(i);

                                String date = podatci.getString("date");
                                String num = podatci.getString("num");
                                imena.add(date);
                                vrednosti.add(num);
                               // MTextViewResult.append(date+" "+num+"\n\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQuene.add(req);
    }

    }

