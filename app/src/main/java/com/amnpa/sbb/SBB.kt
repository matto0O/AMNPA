package com.amnpa.sbb

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.amnpa.sbb.model.NotificationService

class SBB: Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel =
            NotificationChannel(
                NotificationService.CHANNEL_ID,
                "Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        val notificationsManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationsManager.createNotificationChannel(channel)
    }
}