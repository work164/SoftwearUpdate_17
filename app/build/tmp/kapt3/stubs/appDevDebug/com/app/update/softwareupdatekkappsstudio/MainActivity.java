package com.app.update.softwareupdatekkappsstudio;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 22\u00020\u0001:\u00012B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0014H\u0002J\u0012\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020\'H\u0002J\b\u0010+\u001a\u00020\'H\u0003J\b\u0010,\u001a\u00020\'H\u0002J\b\u0010-\u001a\u00020\'H\u0002J\u000e\u0010.\u001a\u00020\'2\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u00020\'2\u0006\u0010/\u001a\u000200R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001f\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u00140\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\b\u00a8\u00063"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "day", "", "getDay", "()I", "setDay", "(I)V", "hour", "getHour", "setHour", "minute", "getMinute", "setMinute", "month", "getMonth", "setMonth", "resultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getResultLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "wordViewModel", "Lcom/app/update/softwareupdatekkappsstudio/database/WordViewModel;", "getWordViewModel", "()Lcom/app/update/softwareupdatekkappsstudio/database/WordViewModel;", "wordViewModel$delegate", "Lkotlin/Lazy;", "year", "getYear", "setYear", "checkIntentAndStart", "", "context", "Landroid/content/Context;", "intent", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "requestAlertPermission", "requestBatteryOptimizationPermission", "requestDrawOverlay", "setAlarm", "showDatePickerDialog", "v", "Landroid/view/View;", "showTimePickerDialog", "Companion", "app_appDevDebug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "MainActivity";
    private int hour = -1;
    private int minute = -1;
    private int year = -1;
    private int month = -1;
    private int day = -1;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultLauncher = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy wordViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.app.update.softwareupdatekkappsstudio.MainActivity.Companion Companion = null;
    
    public MainActivity() {
        super();
    }
    
    public final int getHour() {
        return 0;
    }
    
    public final void setHour(int p0) {
    }
    
    public final int getMinute() {
        return 0;
    }
    
    public final void setMinute(int p0) {
    }
    
    public final int getYear() {
        return 0;
    }
    
    public final void setYear(int p0) {
    }
    
    public final int getMonth() {
        return 0;
    }
    
    public final void setMonth(int p0) {
    }
    
    public final int getDay() {
        return 0;
    }
    
    public final void setDay(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.activity.result.ActivityResultLauncher<android.content.Intent> getResultLauncher() {
        return null;
    }
    
    private final com.app.update.softwareupdatekkappsstudio.database.WordViewModel getWordViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public final void showDatePickerDialog(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    public final void showTimePickerDialog(@org.jetbrains.annotations.NotNull
    android.view.View v) {
    }
    
    private final void setAlarm() {
    }
    
    private final void requestAlertPermission() {
    }
    
    private final void requestDrawOverlay() {
    }
    
    @android.annotation.SuppressLint(value = {"BatteryLife"})
    private final void requestBatteryOptimizationPermission() {
    }
    
    private final boolean checkIntentAndStart(android.content.Context context, android.content.Intent intent) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/MainActivity$Companion;", "", "()V", "TAG", "", "app_appDevDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}