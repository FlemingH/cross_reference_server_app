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

    private static final String TAG = "CR AIDL";

    private Binder mBinder = new IMyAidlInterface.Stub() {

        @Override
        public int add(int arg1, int agr2) throws RemoteException {

            new Thread(){
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "VCC service received mock app message",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();

            Log.d(TAG, "a1 = " + arg1 + " a2 = " + agr2);
            return arg1 + arg1;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
