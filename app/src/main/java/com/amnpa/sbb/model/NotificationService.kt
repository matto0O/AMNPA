package com.amnpa.sbb.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.amnpa.sbb.MainActivity
import com.amnpa.sbb.R

class NotificationService: BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?){
        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(channel)

        val pendingIntent = PendingIntent.getActivity(
            context,
            CODE,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Notif title")
            .setContentText("Notification")
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }

    companion object
    {
        private const val CHANNEL_ID = "channel1"
        private const val CHANNEL_NAME = "Notifications"
        private const val CODE = 1
    }
}