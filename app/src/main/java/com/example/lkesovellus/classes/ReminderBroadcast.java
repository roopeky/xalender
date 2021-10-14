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
/**
 * Luokka rakentaa ja suorittaa muistutuksen
 * @author Roope Kylli
 * @version 1.0
 */
public class ReminderBroadcast extends BroadcastReceiver {
    /**
     * Kutsutaan, kun saa signaalin ilmoituksesta
     * @param context mistä aktiviteetista: muistutus näkymästä
     * @param intent mihin aktiviteettiin: aktiviteetti MainActivity
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent mainactivity = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);           // Tekee uuden taskin ja clearaan vanhan taskin
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainactivity, 0);
        /**
         * Rakentaa reminderBuilder olion tietyillä asetuksilla
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel1")
                .setSmallIcon(R.drawable.ilmoitus_kuva)                                             // Ilmoituksen pikkukuva
                .setContentTitle("Xalender")                                                        // Ilmoituksen otsikko
                .setContentText("Ota lääkkeet!")                                                    // Ilmoituksen leipäteksti
                .setPriority(NotificationCompat.PRIORITY_HIGH)                                      // ILmoituksen prioriteetti
                .setAutoCancel(true)                                                                // Sulkeutuuko ilmoitus, kun sitä klikataan (true)
                .setContentIntent(pendingIntent);                                                   // Avaa tietyn näkymän, kun ilmoitusta klikataan

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, builder.build());                                        // Lähettää ilmoituksen

    }
}
