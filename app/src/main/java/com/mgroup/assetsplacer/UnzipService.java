package com.mgroup.assetsplacer;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.InputStream;

import static com.mgroup.assetsplacer.BootReciver.PREF_KEY_BOOL_WAS_DONE;

/**
 * Created by Shai on 24-Oct-17.
 */

public class UnzipService extends IntentService {
    public UnzipService() {
        super("Unzipper");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //TODO: get the target dir from the assets
        File mSDPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        if (Unzipper.unzip(getInputStreamFromRawDir(), mSDPath.getAbsolutePath())) {
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean(PREF_KEY_BOOL_WAS_DONE, true).apply();
        }
    }

    private InputStream getInputStreamFromRawDir() {
        return getResources().openRawResource(R.raw.content);
    }

}
