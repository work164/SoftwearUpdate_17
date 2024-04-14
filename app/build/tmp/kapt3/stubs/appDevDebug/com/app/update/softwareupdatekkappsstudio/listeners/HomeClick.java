package com.app.update.softwareupdatekkappsstudio.listeners;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016\u00a8\u0006\n"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/listeners/HomeClick;", "", "onItemClick", "", "name", "", "position", "", "onUninstallItemClick", "packegeName", "app_appDevDebug"})
public abstract interface HomeClick {
    
    public abstract void onItemClick(@org.jetbrains.annotations.NotNull
    java.lang.String name, int position);
    
    public abstract void onUninstallItemClick(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String packegeName, int position);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        public static void onItemClick(@org.jetbrains.annotations.NotNull
        com.app.update.softwareupdatekkappsstudio.listeners.HomeClick $this, @org.jetbrains.annotations.NotNull
        java.lang.String name, int position) {
        }
        
        public static void onUninstallItemClick(@org.jetbrains.annotations.NotNull
        com.app.update.softwareupdatekkappsstudio.listeners.HomeClick $this, @org.jetbrains.annotations.NotNull
        java.lang.String name, @org.jetbrains.annotations.NotNull
        java.lang.String packegeName, int position) {
        }
    }
}