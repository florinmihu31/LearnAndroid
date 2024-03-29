package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID +
            ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        int number = intent.getIntExtra(MainActivity.EXTRA_TAG, -1);

        if (intentAction != null) {
            String toastMessage = "Unknown intent action";

            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power Connected!";
                    break;

                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power Disconnected!";
                    break;

                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Custom Broadcast Received!\n";
                    toastMessage += "Square of random number: " + (number * number);
                    break;

                case Intent.ACTION_HEADSET_PLUG:
                    toastMessage = "Headset Plugged!";
                    break;
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
