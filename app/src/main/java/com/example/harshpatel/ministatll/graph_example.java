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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerImage;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
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

    static ArrayList<Double> exampleX;
    static ArrayList<Double> exampleY;



    static ArrayList<Double> xValues;
    static ArrayList<Double> yValues;

    private LineChart mChart;
    double x = 0;
    double y = 0;
    int counter = 0;
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
//        for (int i = 0; i < xValues.size(); i++) {
//            if(i == (xValues.size() - 1)) {
//                data += "(" + xValues.get(i) + "," + yValues.get(i) + ")";
//            } else {
//                data += "(" + xValues.get(i) + "," + yValues.get(i) + "),";
//            }
//        }
//        data += "\n";


        for (int i = 0; i < xValues.size(); i++) {
            data += xValues.get(i) + "," + yValues.get(i) + "\n";
        }

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

        double[] xs = {-33,-67,-134,-201,-268,-336,-403,-470,-537 ,-604,-672,
                       -739 ,-806 ,-739 ,-672 ,-604 ,-537 ,-470 ,-403 ,-336,
                       -268 ,-201 ,-134 ,-67 ,-33 ,0 ,33,67,134 ,201 ,268,
                        336 ,403 ,470 ,537 ,604 ,672 ,739 ,806 ,739 ,672,
                        604 ,537 ,470 ,403 ,336 ,268 ,201 ,134 ,67 ,33 ,0,
                        -33 ,-67 ,-134 ,-201 ,-268 ,-336 ,-403 ,-470 ,-537,
                        -604 ,-672 ,-739 ,-806,-739 ,-672 ,-604 ,-537 ,-470,
                        -403 ,-336 ,-268 ,-201 ,-134 ,-67 ,-33, 0 ,33 ,67 ,134,
                        201 ,268 ,336 ,403 ,470 ,537 ,604 ,672 ,739 ,806 ,739,
                        672 ,604 ,537 ,470 ,403 ,336 ,268 ,201 ,134 ,67 ,33,0};

        double[] ys = {-2209 ,-2448 ,-2328 ,-2090 ,-2090 ,-2090 ,-2209 ,-2448,
                     -2567 ,-2567 ,-2448 ,-2328 ,-2328 ,-2328 ,-2209 ,-2209 ,-2209,
                     -1851 ,-1731-1612 ,-1492 ,-1492 ,-1492 ,-1373 ,-1373 ,-1254,
                     -1134 ,-537 ,2926 ,8539 ,10928 ,8897 ,6986 ,5912 ,5195 ,4717,
                     4359 ,4001 ,4001 ,3165 ,2687 ,2448 ,2328 ,2328 ,2209 ,1970,
                     1492 ,-537 ,-5314 ,-9375, -8420 ,-7345 ,-6389 ,-5792 -5314,
                     -4478,-4120 ,-3762 ,-3642,-3642 ,-3881 ,-4120 ,-3762 ,-3523 ,
                     -3403 ,-3165 ,-3045 ,-2926 ,-2687 ,-2328 ,-2090 ,-1970 ,-1970,
                     -1970 ,-1851 ,-1731 ,-1731 ,-1612,-1373 ,-895 ,2687 ,8420,
                      10569,8659 ,6628 ,5553 ,4956 ,4478 ,4120 ,3881 ,3762 ,3045,
                     2567 ,2328 ,2209 ,2090 ,2090 ,1851 ,1373 ,-656 ,-5434 ,-9495,
                     -8539 ,-7464};

        exampleX = new ArrayList<Double>();
        exampleY = new ArrayList<Double>();

        for (int i = 0; i < ys.length; i++) {
            exampleX.add(xs[i]);
            exampleY.add(ys[i]);
        }

        xValues = new ArrayList<Double>();
        yValues = new ArrayList<Double>();

        stop_button = (Button) findViewById(R.id.stop_button);
        save_data_onto_file_button = (Button) findViewById(R.id.save_data_onto_file_button);
        shouldIStop = false;

        //get the current time and date
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
//        xAxis.setAxisMaximum(1000);
//        xAxis.setAxisMinimum(-1000);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);


        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setTextSize(12f); // set the text size
        yAxis.setAxisMinimum(-100); // start at zero
        yAxis.setAxisMaximum(100); // the axis maximum is 100
        yAxis.setTextColor(Color.BLACK);
        yAxis.setGranularity(1f); // interval 1
        yAxis.setLabelCount(100, true); // force 6 labels

        YAxis yAxis1 = mChart.getAxisRight();
        yAxis1.setEnabled(false);

    }

    protected void onResume() {
        super.onResume();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < exampleY.size(); i++) {
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

//            mChart.notifyDataSetChanged();
//            mChart.setVisibleXRange(-1000,1000);

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }

            //fake x and y values
            x += Math.PI/16;
//            if(x > 6) {
//                x -= Math.PI/8;
//            } else {
//                x += Math.PI/16;
//
//            }
//            y = Math.abs(Math.sin(x))*100;
            y = Math.sin(x)*100;

//            if(counter > exampleY.size()) {
//                shouldIStop = true;
//                return;
//            }
//
//            x = exampleX.get(counter);
//            y = exampleY.get(counter);
//            counter++;
            data.addEntry(new Entry((float)x , (float)y), 0);

            //Putting the values into the arraylist for X and Y values
            xValues.add(x);
            yValues.add(y);

            data.notifyDataChanged();
            mChart.notifyDataSetChanged();
            mChart.setVisibleXRange(0,6);
//            mChart.setVisibleXRangeMaximum(2000);
//            mChart.invalidate();
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
