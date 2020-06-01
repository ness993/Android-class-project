package com.test.grafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.security.cert.Extension;
import java.util.ArrayList;

public class linechart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        Intent i =getIntent();
        ArrayList<String> vrjednost =i.getStringArrayListExtra("vrijednosti");

        LineChart lchart =findViewById(R.id.lineCHARTchart);

        ArrayList<Entry>lineChartEntry = new ArrayList<>();
        int count=0;
        for (String s:vrjednost){

            Integer val=0;
            try {
                    val=Integer.parseInt(s);
            }catch(Exception e){}
            lineChartEntry.add(new Entry(count, val));
            count ++;
        }

        LineDataSet lineDataSet = new LineDataSet(lineChartEntry, "oboljeli");

        LineData lineData= new LineData(lineDataSet);
        lchart.setData(lineData);

        lchart.getDescription().setText("Oboljelih od COVI-19 u RH");
        lchart.animateXY(2500,2500);
    }
}
