package com.app.update.softwareupdatekkappsstudio.info;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\"\u0010\r\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0004H\u0002\u00a8\u0006\u0016"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/info/AlarmsForegroundService;", "Landroid/app/Service;", "()V", "createNotificationChannel", "", "channelId", "channelName", "getSavedAlarm", "Lcom/app/update/softwareupdatekkappsstudio/model/AlarmData;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "refreshAlarms", "", "startAlarm", "startForeground", "type", "app_appDevDebug"})
public final class AlarmsForegroundService extends android.app.Service {
    
    public AlarmsForegroundService() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override
    public int onStartCommand(@org.jetbrains.annotations.Nullable
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void refreshAlarms() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.app.update.softwareupdatekkappsstudio.model.AlarmData getSavedAlarm() {
        return null;
    }
    
    private final void startAlarm() {
    }
    
    private final void startForeground(java.lang.String type) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    private final java.lang.String createNotificationChannel(java.lang.String channelId, java.lang.String channelName) {
        return null;
    }
}