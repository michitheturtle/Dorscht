package ch.turtlestack.dorscht.classPackage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by michael on 07.04.15.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String title = "Dorscht Reminder";
        String subject = "Bier trinken";
        String body = "Es wäre jetzt an der Zeit ein frisches Bier zu trinken! Pörschtli!";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 2);

        //aktuelle Uhrzeit:
        //System.currentTimeMillis()

        NotificationManager NM;
        NM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification(android.R.drawable.stat_notify_voicemail, title, System.currentTimeMillis());

        PendingIntent pending = PendingIntent.getActivity(context, 0, new Intent(), 0);

        notify.setLatestEventInfo(context, subject, body, pending);

        NM.notify(0, notify);

    }
}