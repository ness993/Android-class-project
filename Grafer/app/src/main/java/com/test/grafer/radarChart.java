package com.test.grafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class radarChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);
        RadarChart mradarChart =findViewById(R.id.radarCHARTchart);

        Intent i =getIntent();
        ArrayList<String> ime = i.getStringArrayListExtra("imena");
        ArrayList<String> vrjednost =i.getStringArrayListExtra("vrijednosti");

        ArrayList<RadarEntry> radarChartEntry = new ArrayList<>();
        for (String value : vrjednost){
            Integer s=0;
            try {
                s= Integer.parseInt(value);
            }catch (Exception e){}
            radarChartEntry.add(new RadarEntry(s));
        }
        ArrayList<String> laberls = new ArrayList<>();
        for (String value : ime){
            String sstring=value+" Dan";
            laberls.add(sstring);
        }

        RadarDataSet radarDataSet = new RadarDataSet(radarChartEntry, "Oboljeli");
        radarDataSet.setColor(Color.RED);
        radarDataSet.setLineWidth(3f);
        radarDataSet.setValueTextColor(Color.RED);
        radarDataSet.setValueTextSize(14f);
        RadarData radarData= new RadarData(radarDataSet);
        XAxis xAxis=mradarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(laberls));
        mradarChart.setData(radarData);
        mradarChart.getDescription().setText("Broj oboljelih od COVID-19 u RH");

    }
}
