package com.app.update.softwareupdatekkappsstudio.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0004j\b\u0012\u0004\u0012\u00020\u0019`\u0006H\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010&\u001a\u00020\u001bH\u0002R*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R!\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R!\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\b\u00a8\u0006\'"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/SystemAppFragment;", "Landroidx/fragment/app/Fragment;", "()V", "appList", "Ljava/util/ArrayList;", "Landroid/content/pm/ApplicationInfo;", "Lkotlin/collections/ArrayList;", "getAppList", "()Ljava/util/ArrayList;", "setAppList", "(Ljava/util/ArrayList;)V", "binding", "Lcom/app/update/softwareupdatekkappsstudio/databinding/FragmentSystemAppBinding;", "code", "", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "installAppInfo", "getInstallAppInfo", "listOfApps", "systemAppFragment", "getSystemAppFragment", "getMainModel", "Lcom/app/update/softwareupdatekkappsstudio/model/SystemModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "systemApp", "app_appDevDebug"})
public final class SystemAppFragment extends androidx.fragment.app.Fragment {
    private com.app.update.softwareupdatekkappsstudio.databinding.FragmentSystemAppBinding binding;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<android.content.pm.ApplicationInfo> appList;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<android.content.pm.ApplicationInfo> installAppInfo = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<android.content.pm.ApplicationInfo> systemAppFragment = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String code = "";
    private java.util.ArrayList<android.content.pm.ApplicationInfo> listOfApps;
    
    public SystemAppFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<android.content.pm.ApplicationInfo> getAppList() {
        return null;
    }
    
    public final void setAppList(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<android.content.pm.ApplicationInfo> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<android.content.pm.ApplicationInfo> getInstallAppInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<android.content.pm.ApplicationInfo> getSystemAppFragment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCode() {
        return null;
    }
    
    public final void setCode(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
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
    
    private final void systemApp() {
    }
    
    private final java.util.ArrayList<com.app.update.softwareupdatekkappsstudio.model.SystemModel> getMainModel() {
        return null;
    }
}