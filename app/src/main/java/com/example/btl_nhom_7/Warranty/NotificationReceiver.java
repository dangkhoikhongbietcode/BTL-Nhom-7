package com.example.btl_nhom_7.Warranty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.btl_nhom_7.R;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("message");

        // Hiển thị thông báo khi nhận được sự kiện
        showNotification(context, notificationId, message);
    }

    private void showNotification(Context context, int notificationId, String message) {
        // Xây dựng thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "warranty_channel")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Hạn bảo hành")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Hiển thị thông báo
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, builder.build());
    }
}

