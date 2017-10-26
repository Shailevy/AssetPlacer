package com.mgroup.assetsplacer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;


/**
 * Created by Shai on 24-Oct-17.
 */

public class BootReciver extends BroadcastReceiver {
    public static final String PREF_KEY_BOOL_WAS_DONE = "was_already_done";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context.getResources().getBoolean(R.bool.do_once)) {
            //check if done already
            if (!PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_BOOL_WAS_DONE, false)) {
                runService(context);
            }
        } else {
            runService(context);
        }
    }

    private void runService(Context context) {
        Intent serviceIntent = new Intent(context, ServiceActivity.class);
        context.startService(serviceIntent);
    }
}
