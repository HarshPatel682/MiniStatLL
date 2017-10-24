package com.example.harshpatel.ministatll;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;

public class Scanner_BTLE {

    private MainActivity ma;

    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler handler;

    private long scanPeriod;
    private int signalStrength;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public Scanner_BTLE(MainActivity mainActivity, long scanPeriod, int signalStrength) {
        ma = mainActivity;
        handler = new Handler();
        this.scanPeriod = scanPeriod;
        this.signalStrength = signalStrength;
        final BluetoothManager bluetoothManager = (BluetoothManager) ma.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
    }

    public boolean isScanning() {
        return mScanning;
    }

    public void start() {
        if (!My_Utils.checkBluetooth(mBluetoothAdapter)) {
            My_Utils.requestUserBluetooth(ma);
            //ma.stopScan();
        } else {
            scanLeDevice(true);
        }


    }

    public void stop() {
        scanLeDevice(false);
    }

    private void scanLeDevice(final boolean enable) {
        if (enable && !mScanning) {
            My_Utils.toast(ma.getApplication(), "Starting BTLE Scan...");

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    My_Utils.toast(ma.getApplication(), "Stopping BTLE Scan...");
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);

                    ma.stopScan();
                }
            }, scanPeriod);

            mScanning  = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);

        }
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {

            final int new_rssi = rssi;
            if (rssi > signalStrength) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ma.addDevice(device, new_rssi);
                    }
                });
            }
        }
    };
}
