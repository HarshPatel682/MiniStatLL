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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setEnabled(false);

    }

    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void addEntry() {
        LineData data = mChart.getData();

        if (data != null) {
            LineDataSet set = (LineDataSet) data.getDataSetByIndex(0);
            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }

            data.addEntry(new Entry((float) Math.random() + 50f , set.getEntryCount()), 0);
            mChart.notifyDataSetChanged();
            mChart.setVisibleXRange(0,6);
            //mChart.moveViewToX(data.getDataSetCount() -7);
        }
    }

    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "the line");
        set.setDrawCircles(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 177));
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(10f);

        return set;
    }
}
