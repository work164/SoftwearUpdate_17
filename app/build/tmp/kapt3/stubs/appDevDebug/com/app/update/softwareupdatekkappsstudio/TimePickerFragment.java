package com.app.update.softwareupdatekkappsstudio;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/TimePickerFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/app/TimePickerDialog$OnTimeSetListener;", "timeListener", "Lcom/app/update/softwareupdatekkappsstudio/listeners/OnTimeSet;", "(Lcom/app/update/softwareupdatekkappsstudio/listeners/OnTimeSet;)V", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onTimeSet", "", "view", "Landroid/widget/TimePicker;", "hourOfDay", "", "minute", "app_appDevDebug"})
public final class TimePickerFragment extends androidx.fragment.app.DialogFragment implements android.app.TimePickerDialog.OnTimeSetListener {
    @org.jetbrains.annotations.Nullable
    private final com.app.update.softwareupdatekkappsstudio.listeners.OnTimeSet timeListener = null;
    
    public TimePickerFragment(@org.jetbrains.annotations.Nullable
    com.app.update.softwareupdatekkappsstudio.listeners.OnTimeSet timeListener) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onTimeSet(@org.jetbrains.annotations.NotNull
    android.widget.TimePicker view, int hourOfDay, int minute) {
    }
    
    public TimePickerFragment() {
        super();
    }
}