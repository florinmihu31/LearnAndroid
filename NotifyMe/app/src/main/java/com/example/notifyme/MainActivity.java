package com.example.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_DELETE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_DELETE_NOTIFICATION";
    private static final int NOTIFICATION_ID = 0;

    private Button buttonNotify;
    private Button buttonUpdate;
    private Button buttonCancel;

    private NotificationManager mNotificationManager;
    private NotificationReceiver mNotificationReceiver = new NotificationReceiver();
    private DeleteNotificationReceiver mDeleteNotificationReceiver = new DeleteNotificationReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNotify = findViewById(R.id.notify);
        buttonNotify.setOnClickListener(view -> sendNotification());

        buttonUpdate = findViewById(R.id.update);
        buttonUpdate.setOnClickListener(view -> updateNotification());

        buttonCancel = findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(view -> cancelNotification());

        createNotificationChannel();

        setNotificationButtonState(true, false, false);

        registerReceiver(mNotificationReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));
        registerReceiver(mDeleteNotificationReceiver, new IntentFilter(ACTION_DELETE_NOTIFICATION));
    }

    public void sendNotification() {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                updateIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent cancelIntent = new Intent(ACTION_DELETE_NOTIFICATION);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                cancelIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent)
                .setDeleteIntent(cancelPendingIntent);
        mNotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        setNotificationButtonState(false, true, true);
    }

    private void updateNotification() {
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        Intent cancelIntent = new Intent(ACTION_DELETE_NOTIFICATION);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                cancelIntent, PendingIntent.FLAG_ONE_SHOT);

        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Updated!"))
                .setDeleteIntent(cancelPendingIntent);

        mNotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        setNotificationButtonState(false, false, true);
    }

    private void cancelNotification() {
        mNotificationManager.cancel(NOTIFICATION_ID);
        setNotificationButtonState(true, false, false);
    }

    public void createNotificationChannel() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");

            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        return new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
    }

    void setNotificationButtonState(boolean isNotifyEnabled,
                                    boolean isUpdateEnabled,
                                    boolean isCancelEnabled) {
        buttonNotify.setEnabled(isNotifyEnabled);
        buttonUpdate.setEnabled(isUpdateEnabled);
        buttonCancel.setEnabled(isCancelEnabled);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mNotificationReceiver);
        unregisterReceiver(mDeleteNotificationReceiver);
        super.onDestroy();
    }

    public class NotificationReceiver extends BroadcastReceiver {

        public NotificationReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }

    public class DeleteNotificationReceiver extends BroadcastReceiver {

        public DeleteNotificationReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("PLM", "AICI");
            cancelNotification();
        }
    }
}