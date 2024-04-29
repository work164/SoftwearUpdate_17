package com.app.update.softwareupdatekkappsstudio.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J \u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\"\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0018\u0010*\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\b\u0010+\u001a\u00020\u0014H\u0002J\u0018\u0010,\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\b\u0010-\u001a\u00020\u0014H\u0002J\u0010\u0010.\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0002R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\b\u001a\u0004\b\r\u0010\u000e\u00a8\u0006/"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/AppDetailsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "appPackageName", "", "getAppPackageName", "()Ljava/lang/String;", "appPackageName$delegate", "Lkotlin/Lazy;", "binding", "Lcom/app/update/softwareupdatekkappsstudio/databinding/FragmentAppDetailsBinding;", "wordViewModel", "Lcom/app/update/softwareupdatekkappsstudio/database/WordViewModel;", "getWordViewModel", "()Lcom/app/update/softwareupdatekkappsstudio/database/WordViewModel;", "wordViewModel$delegate", "formatDate", "date", "Ljava/util/Date;", "getAppDetailsByPackageName", "", "packageName", "packageManager", "Landroid/content/pm/PackageManager;", "launchApp", "context", "Landroid/content/Context;", "loadAds", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "openPlayStore", "setUpClickListeners", "shareAppDetails", "showAd", "uninstallApp", "app_appDevDebug"})
public final class AppDetailsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.app.update.softwareupdatekkappsstudio.databinding.FragmentAppDetailsBinding binding;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy wordViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy appPackageName$delegate = null;
    
    public AppDetailsFragment() {
        super();
    }
    
    private final com.app.update.softwareupdatekkappsstudio.database.WordViewModel getWordViewModel() {
        return null;
    }
    
    private final java.lang.String getAppPackageName() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setUpClickListeners() {
    }
    
    private final void openPlayStore(android.content.Context context, java.lang.String packageName) {
    }
    
    private final void getAppDetailsByPackageName(java.lang.String packageName, android.content.pm.PackageManager packageManager) {
    }
    
    private final void launchApp(java.lang.String packageName, android.content.pm.PackageManager packageManager, android.content.Context context) {
    }
    
    private final void shareAppDetails(android.content.Context context, java.lang.String packageName) {
    }
    
    private final void uninstallApp(java.lang.String packageName) {
    }
    
    @java.lang.Override
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    private final java.lang.String formatDate(java.util.Date date) {
        return null;
    }
    
    private final void loadAds() {
    }
    
    private final void showAd() {
    }
}