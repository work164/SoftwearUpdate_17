package com.app.update.softwareupdatekkappsstudio.ads;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\rH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/ads/ConnectivityStatus;", "Landroidx/lifecycle/LiveData;", "Lcom/app/update/softwareupdatekkappsstudio/ads/Event;", "", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "connectivityManager", "Landroid/net/ConnectivityManager;", "(Landroid/net/ConnectivityManager;)V", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "onActive", "", "onInactive", "app_appDevDebug"})
public final class ConnectivityStatus extends androidx.lifecycle.LiveData<com.app.update.softwareupdatekkappsstudio.ads.Event<? extends java.lang.Boolean>> {
    @org.jetbrains.annotations.NotNull
    private final android.net.ConnectivityManager connectivityManager = null;
    @org.jetbrains.annotations.NotNull
    private final android.net.ConnectivityManager.NetworkCallback networkCallback = null;
    
    public ConnectivityStatus(@org.jetbrains.annotations.NotNull
    android.net.ConnectivityManager connectivityManager) {
        super(null);
    }
    
    public ConnectivityStatus(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @java.lang.Override
    protected void onActive() {
    }
    
    @java.lang.Override
    protected void onInactive() {
    }
}