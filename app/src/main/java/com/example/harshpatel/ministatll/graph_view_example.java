package com.example.harshpatel.ministatll;

import android.graphics.Color;
import android.graphics.Interpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class graph_view_example extends AppCompatActivity {

    private static final String TAG = "graph_view_example";

//    double x;
//    double y;
    double[] xs = {-33,-67,-134,-201,-268,-336,-403,-470,-537 ,-604,
            -672, -739 ,-806 ,-739 ,-672 ,-604 ,-537 ,-470 ,-403 ,-336,
            -268 ,-201 ,-134 ,-67 ,-33 ,0 ,33,67,134 ,201,
            268, 336 ,403 ,470 ,537 ,604 ,672 ,739 ,806 ,739 ,
            672, 604 ,537 ,470 ,403 ,336 ,268 ,201 ,134 ,67 ,
             33 ,0, -33 ,-67 ,-134 ,-201 ,-268 ,-336 ,-403 ,-470 ,
            -537, -604 ,-672 ,-739 ,-806,-739 ,-672 ,-604 ,-537 ,-470,
            -403 ,-336 ,-268 ,-201 ,-134 ,-67 ,-33, 0 ,33 ,67 ,
            134, 201 ,268 ,336 ,403 ,470 ,537 ,604 ,672 ,739 ,
            806 ,739, 672 ,604 ,537 ,470 ,403 ,336 ,268 ,201 ,
            134 ,67 ,33,0};

    double[] ys = {-2209 ,-2448 ,-2328 ,-2090 ,-2090 ,-2090 ,-2209 ,-2448, -2567 ,-2567 ,
                  -2448 ,-2328 ,-2328 ,-2328 ,-2209 ,-2209 ,-2209, -1851 ,-1731,-1612 ,
                -1492 ,-1492 ,-1492 ,-1373 ,-1373 ,-1254, -1134 ,-537 ,2926 ,8539 ,
                10928 ,8897 ,6986 ,5912 ,5195 ,4717, 4359 ,4001 ,4001 ,3165 ,
            2687 ,2448 ,2328 ,2328 ,2209 ,1970, 1492 ,-537 ,-5314 ,-9375,
            -8420 ,-7345 ,-6389 ,-5792 -5314, -4478,-4120 ,-3762 ,-3642,-3642 ,
            -3881 ,-4120 ,-3762 ,-3523 , -3403 ,-3165 ,-3045 ,-2926 ,-2687 ,-2328,
            -2090 ,-1970 ,-1970, -1970 ,-1851 ,-1731 ,-1731 ,-1612,-1373 ,-895 ,
            2687 ,8420, 10569,8659 ,6628 ,5553 ,4956 ,4478 ,4120 ,3881 ,
            3762 ,3045, 2567 ,2328 ,2209 ,2090 ,2090 ,1851 ,1373 ,-656 ,
            -5434 ,-9495, -8539 ,-7464};

    int currentPosition;
    PointsGraphSeries<DataPoint> dataSeries;
    GraphView scatterPlot;

    private ArrayList<XYValue> valueArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view_example);

        scatterPlot = (GraphView) findViewById(R.id.scatterPlot);
        dataSeries = new PointsGraphSeries<>();
        valueArray = new ArrayList<>();

        dataSeries.setShape(PointsGraphSeries.Shape.POINT);
        dataSeries.setColor(Color.BLUE);
        dataSeries.setSize(5f);

        scatterPlot.getViewport().setScalable(true);
        scatterPlot.getViewport().setScalableY(true);
        scatterPlot.getViewport().setScrollable(true);
        scatterPlot.getViewport().setScrollableY(true);

        scatterPlot.getViewport().setYAxisBoundsManual(true);
        scatterPlot.getViewport().setMaxY(15000);
        scatterPlot.getViewport().setMinY(-15000);

        scatterPlot.getViewport().setXAxisBoundsManual(true);
        scatterPlot.getViewport().setMaxX(1000);
        scatterPlot.getViewport().setMinX(-1000);


        for (int i = 0; i < ys.length; i++) {
            addEntry();
        }

        createScatterPlot();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(currentPosition < 101) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            addEntry();
//                            //createScatterPlot();
//
//                        }
//                    });
//
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
////                    createScatterPlot();
//                }
//
//
//            }
//        }).start();
//    }

    private void addEntry() {
        valueArray.add(new XYValue(xs[currentPosition], ys[currentPosition]));
        Log.e(TAG, "adding " + xs[currentPosition] + " " + ys[currentPosition] + " position " + currentPosition);

        currentPosition++;

//        if(valueArray.size() != 0) {
//            createScatterPlot();
//        } else {
//
//        }
    }

    private void createScatterPlot() {
        valueArray = sortArray(valueArray);

        for (int i = 0; i < valueArray.size(); i++) {
            try {
                double x = valueArray.get(i).getX();
                double y = valueArray.get(i).getY();
                //Log.e(TAG, "adding " + x + " " + y + ".");
                dataSeries.appendData(new DataPoint(x,y), true, 100);
            } catch (IllegalArgumentException e) {

            }
        }

        //Log.e(TAG, "appending data");
        scatterPlot.addSeries(dataSeries);
    }

    private ArrayList<XYValue> sortArray (ArrayList<XYValue> array) {
        int factor = Integer.parseInt(String.valueOf(Math.round(Math.pow(array.size(),2))));
        int m = array.size() -1;
        int count = 0;

        while (true) {
            m--;
            if(m <= 0) {
                m = array.size() -1;
            }
            try {

                double tempX = array.get(m-1).getX();
                double tempY = array.get(m-1).getY();

                if(tempX > array.get(m).getX()) {
                    array.get(m - 1).setY(array.get(m).getY());
                    array.get(m).setY(tempY);

                    array.get(m - 1).setX(array.get(m).getX());
                    array.get(m).setX(tempX);
                } else if(tempX == array.get(m).getX()) {
                    count++;
                } else if(array.get(m).getX() > array.get(m-1).getX()) {
                    count++;
                }
                if(count == factor) {
                    break;
                }


            } catch (ArrayIndexOutOfBoundsException e) {
                e.getMessage();
                break;
            }
        }
        return array;
    }
}
