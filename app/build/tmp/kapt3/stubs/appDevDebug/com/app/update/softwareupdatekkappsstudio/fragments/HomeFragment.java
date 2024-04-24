package com.app.update.softwareupdatekkappsstudio.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J$\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001a\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/HomeFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/app/update/softwareupdatekkappsstudio/listeners/HomeClick;", "()V", "binding", "Lcom/app/update/softwareupdatekkappsstudio/databinding/FragmentHomeBinding;", "mList", "Ljava/util/ArrayList;", "Lcom/app/update/softwareupdatekkappsstudio/model/HomeViewModel;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "", "name", "", "position", "", "onViewCreated", "view", "showAd", "remoteConf", "", "app_appDevDebug"})
public final class HomeFragment extends androidx.fragment.app.Fragment implements com.app.update.softwareupdatekkappsstudio.listeners.HomeClick {
    private com.app.update.softwareupdatekkappsstudio.databinding.FragmentHomeBinding binding;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<com.app.update.softwareupdatekkappsstudio.model.HomeViewModel> mList;
    
    public HomeFragment() {
        super();
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
    
    @java.lang.Override
    public void onItemClick(@org.jetbrains.annotations.NotNull
    java.lang.String name, int position) {
    }
    
    private final void showAd(boolean remoteConf) {
    }
    
    @java.lang.Override
    public void onUninstallItemClick(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String packegeName, int position) {
    }
}