package com.volvo.crs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_UPDATE_UI = "action.updateUI";
    public UpdateUIBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_UPDATE_UI);
        broadcastReceiver = new UpdateUIBroadcastReceiver();
        registerReceiver(broadcastReceiver, filter);

        Button clearButton = findViewById(R.id.button);
        final TextView logView = findViewById(R.id.logView);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logView.setText("");
            }
        });
    }

    private class UpdateUIBroadcastReceiver extends BroadcastReceiver {

        final TextView logView = findViewById(R.id.logView);

        @Override
        public void onReceive(Context context, Intent intent) {
            String text = (String) logView.getText();
            logView.setText(String.format("%s\n" + getCurTime() + ": service received mock string", text));
        }
    }

    public String getCurTime () {
        long timecurrentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        return sdf.format(timecurrentTimeMillis);
    }
}
