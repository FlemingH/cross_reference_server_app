package com.volvo.crs;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.protobuf.InvalidProtocolBufferException;
import com.volvocars.crprotobuf.HdRoute;

public class HdRouteContentProvider extends ContentProvider {

    /** Bundle Key same as pusher */
    private static final String HD_ROUTE_PROTO_BUNDLE_KEY = "hd_route";

    private static final String TAG = "ContentProvider";

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {

        byte[] byteArray = extras.getByteArray(HD_ROUTE_PROTO_BUNDLE_KEY);

        try {
            HdRoute.NavigationRoute navigationRoute = HdRoute.NavigationRoute.parseFrom(byteArray);
            Log.d(TAG, "Received ContentProvider json is \n" + navigationRoute);

            new Thread(){
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(getContext(), "VCC service received lib mock string!",
                            Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return super.call(method, arg, extras);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
