package com.app.update.softwareupdatekkappsstudio;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/AlarmActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "alarm_title", "Landroid/widget/TextView;", "closeBtn", "Landroid/widget/ImageView;", "remain_to_stop", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "onAttachedToWindow", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "app_appDevDebug"})
public final class AlarmActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.os.PowerManager.WakeLock wakeLock;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView alarm_title;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView closeBtn;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView remain_to_stop;
    
    public AlarmActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onAttachedToWindow() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
}