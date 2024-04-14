package com.app.update.softwareupdatekkappsstudio.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J&\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\u000fH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\u000fJ&\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u000fH\u0016J\u001a\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010%\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u001bH\u0002J\b\u0010&\u001a\u00020\u000fH\u0002J\b\u0010\'\u001a\u00020\u000fH\u0002J\b\u0010(\u001a\u00020\u000fH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/AppUsageFragment;", "Landroidx/fragment/app/Fragment;", "Lbot/box/appusage/contract/UsageContracts$View;", "()V", "appUsageAdapter", "Lcom/app/update/softwareupdatekkappsstudio/adapter/AppUsageAdapter;", "getAppUsageAdapter", "()Lcom/app/update/softwareupdatekkappsstudio/adapter/AppUsageAdapter;", "appUsageAdapter$delegate", "Lkotlin/Lazy;", "binding", "Lcom/app/update/softwareupdatekkappsstudio/databinding/FragmentAppUsageBinding;", "onResumeCount", "", "getUsageData", "", "usageData", "", "Lbot/box/appusage/model/AppData;", "mTotalUsage", "", "duration", "hideProgress", "isAccessGranted", "", "onBackWithAd", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onViewCreated", "view", "setDuration", "setUpClickListeners", "setUpRecyclerView", "showProgress", "app_appDevDebug"})
public final class AppUsageFragment extends androidx.fragment.app.Fragment implements bot.box.appusage.contract.UsageContracts.View {
    @org.jetbrains.annotations.Nullable
    private com.app.update.softwareupdatekkappsstudio.databinding.FragmentAppUsageBinding binding;
    private int onResumeCount = 0;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy appUsageAdapter$delegate = null;
    
    public AppUsageFragment() {
        super();
    }
    
    private final com.app.update.softwareupdatekkappsstudio.adapter.AppUsageAdapter getAppUsageAdapter() {
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
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpClickListeners() {
    }
    
    private final void setUpRecyclerView() {
    }
    
    private final boolean isAccessGranted() {
        return false;
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    @java.lang.Override
    public void showProgress() {
    }
    
    @java.lang.Override
    public void hideProgress() {
    }
    
    @java.lang.Override
    public void getUsageData(@org.jetbrains.annotations.NotNull
    java.util.List<? extends bot.box.appusage.model.AppData> usageData, long mTotalUsage, int duration) {
    }
    
    private final void setDuration(android.view.View view) {
    }
    
    public final void onBackWithAd() {
    }
}