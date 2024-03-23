package com.app.update.softwareupdatekkappsstudio.ads.billing;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ,2\u00020\u0001:\u0001,BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0006\u0010\u001f\u001a\u00020\u001eJ\u000e\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u00020\u001eH\u0002J\u0010\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001bH\u0002J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001bH\u0002J\u0006\u0010\'\u001a\u00020\u001eJ\b\u0010(\u001a\u00020\u001eH\u0002J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020+H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/ads/billing/Billing6Play;", "", "activity", "Landroid/app/Activity;", "productIds", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "subscriptionIds", "billing6Listener", "Lcom/app/update/softwareupdatekkappsstudio/ads/billing/Billing6Listener;", "(Landroid/app/Activity;Ljava/util/ArrayList;Ljava/util/ArrayList;Lcom/app/update/softwareupdatekkappsstudio/ads/billing/Billing6Listener;)V", "getActivity", "()Landroid/app/Activity;", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "connectivityLiveData", "Lcom/app/update/softwareupdatekkappsstudio/ads/ConnectivityStatus;", "handler", "Landroid/os/Handler;", "isMonthly", "", "isWeekly", "isYearly", "logTag", "productDetailsList", "", "Lcom/android/billingclient/api/ProductDetails;", "subscriptionDetailsList", "billingSetup", "", "checkOldProductSubscription", "checkPurchased", "context", "Landroid/content/Context;", "establishConnection", "launchProdFlow", "productDetails", "launchSubFlow", "oneTimeProduct", "showProducts", "verifySubPurchase", "purchases", "Lcom/android/billingclient/api/Purchase;", "Companion", "app_appDevDebug"})
public final class Billing6Play {
    @org.jetbrains.annotations.NotNull
    private final android.app.Activity activity = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<java.lang.String> productIds = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.ArrayList<java.lang.String> subscriptionIds = null;
    @org.jetbrains.annotations.NotNull
    private final com.app.update.softwareupdatekkappsstudio.ads.billing.Billing6Listener billing6Listener = null;
    @org.jetbrains.annotations.Nullable
    private com.android.billingclient.api.BillingClient billingClient;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.android.billingclient.api.ProductDetails> productDetailsList;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.android.billingclient.api.ProductDetails> subscriptionDetailsList;
    @org.jetbrains.annotations.Nullable
    private com.app.update.softwareupdatekkappsstudio.ads.ConnectivityStatus connectivityLiveData;
    @org.jetbrains.annotations.Nullable
    private android.os.Handler handler;
    @org.jetbrains.annotations.NotNull
    private java.lang.String logTag = "billingInApp";
    private static boolean isAtLeastOneSub = false;
    private static boolean isAtLeastOneProd = false;
    private boolean isWeekly = false;
    private boolean isMonthly = false;
    private boolean isYearly = false;
    @org.jetbrains.annotations.NotNull
    public static final com.app.update.softwareupdatekkappsstudio.ads.billing.Billing6Play.Companion Companion = null;
    
    public Billing6Play(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.String> productIds, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.String> subscriptionIds, @org.jetbrains.annotations.NotNull
    com.app.update.softwareupdatekkappsstudio.ads.billing.Billing6Listener billing6Listener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.app.Activity getActivity() {
        return null;
    }
    
    public final void oneTimeProduct() {
    }
    
    private final void establishConnection() {
    }
    
    private final void showProducts() {
    }
    
    private final void billingSetup() {
    }
    
    private final void verifySubPurchase(com.android.billingclient.api.Purchase purchases) {
    }
    
    private final void launchSubFlow(com.android.billingclient.api.ProductDetails productDetails) {
    }
    
    private final void launchProdFlow(com.android.billingclient.api.ProductDetails productDetails) {
    }
    
    public final void checkOldProductSubscription() {
    }
    
    public final boolean checkPurchased(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0005\"\u0004\b\t\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/ads/billing/Billing6Play$Companion;", "", "()V", "isAtLeastOneProd", "", "()Z", "setAtLeastOneProd", "(Z)V", "isAtLeastOneSub", "setAtLeastOneSub", "app_appDevDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isAtLeastOneSub() {
            return false;
        }
        
        public final void setAtLeastOneSub(boolean p0) {
        }
        
        public final boolean isAtLeastOneProd() {
            return false;
        }
        
        public final void setAtLeastOneProd(boolean p0) {
        }
    }
}