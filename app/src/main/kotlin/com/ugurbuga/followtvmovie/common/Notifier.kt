/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ugurbuga.followtvmovie.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.ugurbuga.followtvmovie.R
import com.ugurbuga.followtvmovie.domain.image.mapper.ImageMapper

/**
 * Utility class for posting notifications.
 * This class creates the notification channel (as necessary) and posts to it when requested.
 */
object Notifier {

    fun postNotification(
        id: Int,
        title: String,
        imageUrl: String,
        content: String,
        context: Context,
        intent: PendingIntent
    ) {
        val channelId = context.getString(R.string.default_channel_id)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            val existingChannel = notificationManager.getNotificationChannel(channelId)
            if (existingChannel == null) {
                // Create the NotificationChannel
                val name = context.getString(R.string.default_channel)
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(channelId, name, importance)
                channel.description = context.getString(R.string.default_channel_description)
                notificationManager.createNotificationChannel(channel)
            }
        }

        val builder = NotificationCompat.Builder(context, channelId)
        builder.setContentTitle(title)
            .setColor(ContextCompat.getColor(context, R.color.primary_color))
            .setSmallIcon(R.drawable.ic_tv)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(intent)
            .setAutoCancel(true)
            .setContentText(content)

        Glide.with(context)
            .asBitmap()
            .load(ImageMapper.getPosterUrl(imageUrl))
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    builder.setLargeIcon(resource)
                        .setStyle(
                            NotificationCompat.BigPictureStyle()
                                .bigPicture(resource)
                                .bigLargeIcon(null)
                        )
                    setNotificationAfterImageLoad(id, builder, context)
                }
            })
    }

    fun setNotificationAfterImageLoad(
        id: Int,
        builder: NotificationCompat.Builder,
        context: Context,
    ) {
        val notification = builder.build()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(id, notification)
    }
}
