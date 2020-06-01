package com.test.grafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class barchart extends AppCompatActivity {

    private Integer imee;
    private Integer vrjednostt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchart);

        TextView alolalo = findViewById(R.id.text_view_result);
        BarChart bar= findViewById(R.id.grafBarovaKojiTrebaPostaviti);


        Intent i =getIntent();
        ArrayList<String> ime = i.getStringArrayListExtra("imena");
        ArrayList<String> vrjednost =i.getStringArrayListExtra("vrijednosti");

        ArrayList<BarEntry> barentry = new ArrayList<>();

        for (int ina=0; ina< ime.size();ina++) {
            try {
                 imee =Integer.parseInt(ime.get(ina));
                 vrjednostt=Integer.parseInt(vrjednost.get(ina));
            }catch (Exception e){}
            barentry.add(new BarEntry(imee,vrjednostt));
        }

        BarDataSet barDataSet = new BarDataSet(barentry, "Oboljeli");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        bar.setFitBars(true);
        bar.setData(barData);
        bar.getDescription().setText("Broj oboljelih od COVID-19 u RH");
        bar.animateY(2000);
    }
}
