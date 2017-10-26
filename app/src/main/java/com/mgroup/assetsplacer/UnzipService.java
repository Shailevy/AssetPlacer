package com.mgroup.assetsplacer;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.File;
import java.io.InputStream;
import android.net.Uri;
// import android.media.MediaScannerConnection

import static com.mgroup.assetsplacer.BootReciver.PREF_KEY_BOOL_WAS_DONE;
/**
 * Created by Shai on 24-Oct-17.
 */

public class UnzipService extends IntentService {
    public UnzipService() {
        super("Unzipper");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //TODO: get the target dir from the assets
        File mSDPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		// Log.d("SHAI", mSDPath.getAbsolutePath());
        if (Unzipper.unzip(getInputStreamFromRawDir(), mSDPath.getAbsolutePath())) {
			refreshMediaStore(mSDPath);
                            
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean(PREF_KEY_BOOL_WAS_DONE, true).apply();
        }
    }

    private InputStream getInputStreamFromRawDir() {
        return getResources().openRawResource(R.raw.content);
    }
    
    private void refreshMediaStore(File path) {
	    final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		final Uri contentUri = Uri.fromFile(path); 
		scanIntent.setData(contentUri);
		sendBroadcast(scanIntent);
    
	}

}
