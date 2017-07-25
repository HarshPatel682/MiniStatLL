package com.example.harshpatel.ministatll;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class graph_example extends AppCompatActivity {

    static ArrayList<Double> xValues;
    static ArrayList<Double> yValues;

    private LineChart mChart;
    double x = 0;
    double y = 0;
    private Button stop_button, save_data_onto_file_button;
    private boolean shouldIStop;

    //this gets the date and time, because you wanna remember when you did the experiment
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String date;

    //this function will save the data to a file after the save_button is clicked
    public void saveToFile() {
        //ASK ORLANDO if he wants the user to be able to name the file...
        String FILENAME = "output.txt"; //it can be changed to .csv after
        String data = "" + date + "\n";
        for (int i = 0; i < xValues.size(); i++) {
            if(i == (xValues.size() - 1)) {
                data += "(" + xValues.get(i) + "," + yValues.get(i) + ")";
            } else {
                data += "(" + xValues.get(i) + "," + yValues.get(i) + "),";
            }
        }
        data += "\n";


        String state;
        state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath()+"/MiniStatLL-App");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dir, FILENAME);
            try {
                FileOutputStream out = new FileOutputStream(file);
                out.write(data.getBytes());
                out.close();
                Toast.makeText(getApplicationContext(), "Content saved!! File name is " + FILENAME, Toast.LENGTH_LONG).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "SD card not found", Toast.LENGTH_LONG).show();
        }



        //this does internal storage
//        try {
//            FileOutputStream out = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//            out.write(data.getBytes());
//            out.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_example);
       /*got this from "[Android] Learn how to create a real time line graph with MPAndroidChart"
        * on Youtube, by Sylvian Saurel
        * https://www.youtube.com/watch?v=a20EchSQgpw&index=5&t=908s&list=PLVDfjBEFgjhBCpeOW11AupWj0bYV1UWrt
        */

        xValues = new ArrayList<Double>();
        yValues = new ArrayList<Double>();

        stop_button = (Button) findViewById(R.id.stop_button);
        save_data_onto_file_button = (Button) findViewById(R.id.save_data_onto_file_button);
        shouldIStop = false;

        //get the current time and date
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        date = simpleDateFormat.format(calendar.getTime());

        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shouldIStop = true;
            }
        });

        save_data_onto_file_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldIStop) {
                    //save to file if the stop button has been pushed ONLY
                    saveToFile();
                }
            }
        });
        mChart = (LineChart) findViewById(R.id.Chart);

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

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            addEntry();
//                        }
//                    });
//
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!shouldIStop) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    try {
                        Thread.sleep(100);
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

            //fake x and y values
            x += Math.PI/16;
            y = Math.abs(Math.tan(x))*20;

            if(y > 100) {
                y = 100;
            }
//            y = Math.abs(Math.sin(x))*100;
            data.addEntry(new Entry((float)x , (float)y), 0);

            //Putting the values into the arraylist for X and Y values
            xValues.add(x);
            yValues.add(y);

            mChart.notifyDataSetChanged();
            mChart.setVisibleXRange(0,6);
            mChart.moveViewToX((float) (x));
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
