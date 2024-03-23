package com.app.update.softwareupdatekkappsstudio.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u001a\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010#\u001a\u00020\u0018H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/AppCountFragment;", "Landroidx/fragment/app/Fragment;", "()V", "appsAdapter", "Lcom/app/update/softwareupdatekkappsstudio/adapter/AppsAdapter;", "appsList", "", "Lcom/app/update/softwareupdatekkappsstudio/AppInfo;", "appsRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "executorService", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "loadingApps", "Landroid/widget/ProgressBar;", "textView", "Landroid/widget/TextView;", "isAvailableOnPlayStore", "", "packageName", "", "loadInstalledApps", "", "loadInstalledAppsInBackground", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "showNumberOfInstalledApps", "Companion", "app_appDevDebug"})
public final class AppCountFragment extends androidx.fragment.app.Fragment {
    private final java.util.concurrent.ExecutorService executorService = null;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView textView;
    @org.jetbrains.annotations.Nullable
    private android.widget.ProgressBar loadingApps;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView appsRecyclerView;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.app.update.softwareupdatekkappsstudio.AppInfo> appsList = null;
    @org.jetbrains.annotations.Nullable
    private com.app.update.softwareupdatekkappsstudio.adapter.AppsAdapter appsAdapter;
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<java.lang.String> DESIRED_SYSTEM_APPS = null;
    @org.jetbrains.annotations.NotNull
    public static final com.app.update.softwareupdatekkappsstudio.fragments.AppCountFragment.Companion Companion = null;
    
    public AppCountFragment() {
        super();
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
    
    private final void loadInstalledAppsInBackground() {
    }
    
    private final java.util.List<com.app.update.softwareupdatekkappsstudio.AppInfo> loadInstalledApps() {
        return null;
    }
    
    private final boolean isAvailableOnPlayStore(java.lang.String packageName) {
        return false;
    }
    
    private final void showNumberOfInstalledApps() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/AppCountFragment$Companion;", "", "()V", "DESIRED_SYSTEM_APPS", "", "", "app_appDevDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}