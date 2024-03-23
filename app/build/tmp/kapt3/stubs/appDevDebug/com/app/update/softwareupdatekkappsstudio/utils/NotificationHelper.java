package com.app.update.softwareupdatekkappsstudio.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ \u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/utils/NotificationHelper;", "", "()V", "NotificationID", "", "mBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "notification", "Landroid/app/Notification;", "notificationManager", "Landroid/app/NotificationManager;", "cancelNotification", "", "runNotification", "context", "Landroid/content/Context;", "body", "", "alarmID", "app_appDevDebug"})
public final class NotificationHelper {
    private static int NotificationID = 1005;
    @org.jetbrains.annotations.Nullable
    private static android.app.Notification notification;
    @org.jetbrains.annotations.Nullable
    private static android.app.NotificationManager notificationManager;
    @org.jetbrains.annotations.Nullable
    private static androidx.core.app.NotificationCompat.Builder mBuilder;
    @org.jetbrains.annotations.NotNull
    public static final com.app.update.softwareupdatekkappsstudio.utils.NotificationHelper INSTANCE = null;
    
    private NotificationHelper() {
        super();
    }
    
    public final void runNotification(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String body, int alarmID) {
    }
    
    public final void cancelNotification() {
    }
}