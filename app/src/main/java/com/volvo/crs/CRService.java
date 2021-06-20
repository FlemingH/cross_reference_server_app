package com.volvo.crs;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CRService extends Service {

    private static final String TAG = "Service";

    private Binder mBinder = new IMyAidlInterface.Stub() {

        @Override
        public String push(String crJson) throws RemoteException {

            new Thread(){
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "VCC service received mock string!",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();

            Log.d(TAG, "Received json is " + crJson);
            return crJson;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
