package com.test.grafer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class piechart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        Intent i =getIntent();
        ArrayList<String> ime = i.getStringArrayListExtra("imena");
        ArrayList<String> vrjednost =i.getStringArrayListExtra("vrijednosti");

        PieChart mPieChart =findViewById(R.id.graftortakojitrebapostaviti);

        ArrayList<PieEntry> oboljeli = new ArrayList<>();
        for (int ina =0; ina< ime.size();ina++){
            Integer integera=0;
            try {integera=Integer.parseInt(vrjednost.get(ina));}catch (Exception e){}
            oboljeli.add(new PieEntry(integera,ime.get(ina)));
        }

        PieDataSet pieDataSet = new PieDataSet(oboljeli, "Novo Oboljeli");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData=new PieData(pieDataSet);
        mPieChart.setData(pieData);
        mPieChart.getDescription().setText("Oboljelih od COVI-19 u RH");
        mPieChart.animateX(1100);
    }
}
