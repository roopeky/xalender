package com.example.lkesovellus.classes;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.lkesovellus.Activities.MainActivity;
import com.example.lkesovellus.R;

public class ReminderBroadcast extends BroadcastReceiver {

    protected void onCreate(Bundle savedInstanceState){

    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Intent mainactivity = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainactivity, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel1")
                .setSmallIcon(R.drawable.ilmoitus_kuva)
                .setContentTitle("Xalender")
                .setContentText("Ota lääkkeet!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());

    }
}
