package com.app.update.softwareupdatekkappsstudio.ads.billing;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u00032\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0016\u00a8\u0006\u0012"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/ads/billing/Billing6Listener;", "", "isAnyOldDone", "", "isFoundProd", "", "isFoundSub", "isAnyOldDoneSub", "isWeekly", "isMonthly", "isYearly", "purchaseOrSubDone", "purchasesList", "productDetailsList", "", "Lcom/android/billingclient/api/ProductDetails;", "subList", "subscriptionDetailsList", "app_appDevDebug"})
public abstract interface Billing6Listener {
    
    public abstract void purchasesList(@org.jetbrains.annotations.Nullable
    java.util.List<com.android.billingclient.api.ProductDetails> productDetailsList);
    
    public abstract void subList(@org.jetbrains.annotations.Nullable
    java.util.List<com.android.billingclient.api.ProductDetails> subscriptionDetailsList);
    
    public abstract void purchaseOrSubDone();
    
    public abstract void isAnyOldDone(boolean isFoundProd, boolean isFoundSub);
    
    public abstract void isAnyOldDoneSub(boolean isWeekly, boolean isMonthly, boolean isYearly);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        public static void purchasesList(@org.jetbrains.annotations.NotNull
        com.app.update.softwareupdatekkappsstudio.ads.billing.Billing6Listener $this, @org.jetbrains.annotations.Nullable
        java.util.List<com.android.billingclient.api.ProductDetails> productDetailsList) {
        }
        
        public static void subList(@org.jetbrains.annotations.NotNull
        com.app.update.softwareupdatekkappsstudio.ads.billing.Billing6Listener $this, @org.jetbrains.annotations.Nullable
        java.util.List<com.android.billingclient.api.ProductDetails> subscriptionDetailsList) {
        }
        
        public static void purchaseOrSubDone(@org.jetbrains.annotations.NotNull
        com.app.update.softwareupdatekkappsstudio.ads.billing.Billing6Listener $this) {
        }
        
        public static void isAnyOldDone(@org.jetbrains.annotations.NotNull
        com.app.update.softwareupdatekkappsstudio.ads.billing.Billing6Listener $this, boolean isFoundProd, boolean isFoundSub) {
        }
        
        public static void isAnyOldDoneSub(@org.jetbrains.annotations.NotNull
        com.app.update.softwareupdatekkappsstudio.ads.billing.Billing6Listener $this, boolean isWeekly, boolean isMonthly, boolean isYearly) {
        }
    }
}