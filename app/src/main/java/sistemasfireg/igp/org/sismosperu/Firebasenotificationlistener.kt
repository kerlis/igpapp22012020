package sistemasfireg.igp.org.sismosperu
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@SuppressLint("OverrideAbstract", "NewApi")

class Firebasenotificationlistener: NotificationListenerService() {


    private object ApplicationPackageNames {
        val FACEBOOK_PACK_NAME = "com.facebook.katana"
        val FACEBOOK_MESSENGER_PACK_NAME = "com.facebook.orca"
        val WHATSAPP_PACK_NAME = "com.whatsapp"
        val INSTAGRAM_PACK_NAME = "com.instagram.android"
    }

    /*
        These are the return codes we use in the method which intercepts
        the notifications, to decide whether we should do something or not
     */
        class InterceptedNotificationCode {
        companion object {

            const val FACEBOOK_CODE: Int = 1
            const val WHATSAPP_CODE: Int = 2
            const val INSTAGRAM_CODE: Int = 3
            const val OTHER_NOTIFICATIONS_CODE: Int = 4


        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return super.onBind(intent)
    }


    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        val notificationCode = matchNotificationCode(sbn!!)


       // if (notificationCode != InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE) {
            val intent = Intent("sistemasfireg.igp.org.sismosperu")
            intent.putExtra("Notification Code", notificationCode)
            sendBroadcast(intent)
       // }

        /*
        Log.i("Msg","Notification Removed");
        // Send a braoadcast with some data


        val notificationMessage = Intent("sistemasfireg.igp.org.sismosperu.Ultimosismo4")
        notificationMessage.putExtra("Hello", "World")
        LocalBroadcastManager.getInstance(this).sendBroadcast(notificationMessage)
        sendBroadcast(notificationMessage)
        */
    }




    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        val notificationCode = matchNotificationCode(sbn)

        if (notificationCode != InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE) {

            val activeNotifications = this.activeNotifications

            if (activeNotifications != null && activeNotifications.size > 0) {
                for (i in activeNotifications.indices) {
                    if (notificationCode == matchNotificationCode(activeNotifications[i])) {
                        val intent = Intent("sistemasfireg.igp.org.sismosperu")
                        intent.putExtra("Notification Code", notificationCode)
                        sendBroadcast(intent)
                        break
                    }
                }
            }
        }
    }

    private fun matchNotificationCode(sbn: StatusBarNotification): Int {
        val packageName = sbn.packageName

        return if (packageName == ApplicationPackageNames.FACEBOOK_PACK_NAME || packageName == ApplicationPackageNames.FACEBOOK_MESSENGER_PACK_NAME) {
            InterceptedNotificationCode.FACEBOOK_CODE
        } else if (packageName == ApplicationPackageNames.INSTAGRAM_PACK_NAME) {
            InterceptedNotificationCode.INSTAGRAM_CODE
        } else if (packageName == ApplicationPackageNames.WHATSAPP_PACK_NAME) {
            InterceptedNotificationCode.WHATSAPP_CODE
        } else {
            InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE
        }
    }


}