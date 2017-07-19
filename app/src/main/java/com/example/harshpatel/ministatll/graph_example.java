package com.example.harshpatel.ministatll;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

public class graph_example extends AppCompatActivity {

    //private RelativeLayout mainLayout;
    private LineChart mChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_example);
       /*got this from "[Android] Learn how to create a real time line graph with MPAndroidChart"
        * on Youtube, by Sylvian Saurel
        * https://www.youtube.com/watch?v=a20EchSQgpw&index=5&t=908s&list=PLVDfjBEFgjhBCpeOW11AupWj0bYV1UWrt
        */


//        mainLayout = (RelativeLayout) findViewById(R.id.LineChart);
         mChart = (LineChart) findViewById(R.id.Chart);
//        mainLayout.addView(mChart);


        /*
        //String emptyString = "";
        //mChart.setDescription(emptyString);
        Description des = new Description();
        des.setText("this is the description");
        mChart.setDescription(des);
        mChart.setNoDataText("AAAHHHHHHHHHHHHHHHHHHHHHHHH");
        //enable touch gestures
        mChart.setTouchEnabled(true);
        //scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        //enable pinch zoom to avoid scaling x and y seperatly
        mChart.setPinchZoom(true);

        //set alternate background
        mChart.setBackgroundColor(Color.LTGRAY);
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(true);
        xl.setAvoidFirstLastClipping(true);

        YAxis yl = mChart.getAxisLeft();
        yl.setTextColor(Color.WHITE);
        //see if this works if not use "yl.getAxisMaxValue(120f);"
        yl.setAxisMaximum(120f);
        yl.setDrawGridLines(true);

        YAxis yl2 = mChart.getAxisRight();
        yl2.setEnabled(false);
        */
        Description description = new Description();
        description.setText("Here is the description text!!!");
        mChart.setDescription(description);

        mChart.setBackgroundColor(Color.LTGRAY);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(true);
        mChart.setPinchZoom(true);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setTextSize(12f); // set the text size
        yAxis.setAxisMinimum(0f); // start at zero
        yAxis.setAxisMaximum(100f); // the axis maximum is 100
        yAxis.setTextColor(Color.BLACK);
        yAxis.setGranularity(1f); // interval 1
        yAxis.setLabelCount(6, true); // force 6 labels

    }
}
