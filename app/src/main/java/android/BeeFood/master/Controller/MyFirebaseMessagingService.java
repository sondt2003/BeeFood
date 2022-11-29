package android.BeeFood.master.Controller;

import android.BeeFood.master.View.Onboarding_Sign_up_Sign_in.MainActivity;
import android.BeeFood.master.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        RemoteMessage.Notification notification=message.getNotification();
        if(notification==null){
            return;
        }
        String Title=notification.getTitle();
        String strMessage=notification.getBody();
        senNotification(Title,strMessage);
    }

    private void senNotification(String title, String strMessage) {
        Intent intent=new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,"push notification").
                setContentTitle(title).
                setContentText(strMessage)
                .setSmallIcon(R.drawable.ic_launcher_background).setContentIntent(pendingIntent);
        Notification notification=notificationBuilder.build();
        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(manager!=null){
            manager.notify(1,notification);
        }
    }
}
