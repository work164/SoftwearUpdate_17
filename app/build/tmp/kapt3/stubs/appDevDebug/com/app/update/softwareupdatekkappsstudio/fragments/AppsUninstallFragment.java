package com.app.update.softwareupdatekkappsstudio.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 $2\u00020\u00012\u00020\u0002:\u0001$B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J&\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J \u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!H\u0016J\u001a\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/AppsUninstallFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/app/update/softwareupdatekkappsstudio/listeners/HomeClick;", "()V", "appsAdapter", "Lcom/app/update/softwareupdatekkappsstudio/adapter/AppsUninstallAdapter;", "appsList", "", "Lcom/app/update/softwareupdatekkappsstudio/AppInfo;", "appsRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "loadingApps", "Landroid/widget/ProgressBar;", "textView", "Landroid/widget/TextView;", "isAvailableOnPlayStore", "", "packageName", "", "loadInstalledApps", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onUninstallItemClick", "name", "packegeName", "position", "", "onViewCreated", "view", "Companion", "app_appDevDebug"})
public final class AppsUninstallFragment extends androidx.fragment.app.Fragment implements com.app.update.softwareupdatekkappsstudio.listeners.HomeClick {
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView textView;
    @org.jetbrains.annotations.Nullable
    private android.widget.ProgressBar loadingApps;
    @org.jetbrains.annotations.Nullable
    private androidx.recyclerview.widget.RecyclerView appsRecyclerView;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.app.update.softwareupdatekkappsstudio.AppInfo> appsList = null;
    @org.jetbrains.annotations.Nullable
    private com.app.update.softwareupdatekkappsstudio.adapter.AppsUninstallAdapter appsAdapter;
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<java.lang.String> DESIRED_SYSTEM_APPS = null;
    @org.jetbrains.annotations.NotNull
    public static final com.app.update.softwareupdatekkappsstudio.fragments.AppsUninstallFragment.Companion Companion = null;
    
    public AppsUninstallFragment() {
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
    
    private final void loadInstalledApps() {
    }
    
    private final boolean isAvailableOnPlayStore(java.lang.String packageName) {
        return false;
    }
    
    @java.lang.Override
    public void onUninstallItemClick(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String packegeName, int position) {
    }
    
    @java.lang.Override
    public void onItemClick(@org.jetbrains.annotations.NotNull
    java.lang.String name, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/AppsUninstallFragment$Companion;", "", "()V", "DESIRED_SYSTEM_APPS", "", "", "getDESIRED_SYSTEM_APPS", "()Ljava/util/List;", "app_appDevDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<java.lang.String> getDESIRED_SYSTEM_APPS() {
            return null;
        }
    }
}