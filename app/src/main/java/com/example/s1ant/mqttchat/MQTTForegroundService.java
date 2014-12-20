package com.example.s1ant.mqttchat;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;



public class MQTTForegroundService extends Service {
    private static String LOG_TAG = "MQTTForegroundService";
    private static int mid = 0;

    private static boolean serviceRunning = false;


    public MQTTForegroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isRunning()) {
            return START_STICKY;
        }

        super.onStartCommand(intent, flags, startId);
        /*
         * Start the MQTT Thread.
         */

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        connection.end();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        /*
         * Return a reference to our client handler.
         */
        return clientMessenger.getBinder();
    }

    private synchronized static boolean isRunning() {
        /*
         * Only run one instance of the service.
         */
        if (serviceRunning == false) {
            serviceRunning = true;
            return false;
        } else {
            return true;
        }
    }
    /*
     * These are the supported messages from bound clients
     */
    public static final int REGISTER = 0;
    public static final int SUBSCRIBE = 1;
    public static final int PUBLISH = 2;


}
