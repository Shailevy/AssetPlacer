package com.mgroup.assetsplacer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Shai on 24-Oct-17.
 */

public class ServiceActivity extends Activity {
    public static String TAG = Unzipper.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "UnZipper started");
//        boolean mUnzipAlways = getResources().getBoolean(R.bool.unzip_always);
        startUnzipAndFinish();
    }

    private void startUnzipAndFinish() {
        startService(new Intent(this, UnzipService.class));
        debugLogFilesAfterUnzip();
        finish();
    }

    public void debugLogFilesAfterUnzip() {
        String path = Environment.getExternalStorageDirectory().toString();
        Log.d(TAG, "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            Log.d(TAG, "FileName:" + files[i].getName());
        }
    }

}
