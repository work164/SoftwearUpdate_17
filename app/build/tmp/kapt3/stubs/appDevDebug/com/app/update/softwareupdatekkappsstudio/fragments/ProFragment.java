package com.app.update.softwareupdatekkappsstudio.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\rH\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\rH\u0002J\b\u0010$\u001a\u00020\u0016H\u0016J\u0018\u0010%\u001a\u00020\u00162\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\'H\u0016J\u0018\u0010)\u001a\u00020\u00162\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\'H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0012j\b\u0012\u0004\u0012\u00020\r`\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0012j\b\u0012\u0004\u0012\u00020\r`\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/fragments/ProFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/example/adssdk/billing/Billing6Listener;", "()V", "billing6Play", "Lcom/example/adssdk/billing/Billing6Play;", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "binding", "Lcom/app/update/softwareupdatekkappsstudio/databinding/FragmentProBinding;", "isMonthly", "", "isSelected", "", "isWeekly", "isYearly", "logTag", "productIds", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "subscriptionIds", "agreementText", "", "changeDesignNow", "type", "checkSubscription", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "openUrl", "url", "purchaseOrSubDone", "purchasesList", "productDetailsList", "", "Lcom/android/billingclient/api/ProductDetails;", "subList", "subscriptionDetailsList", "app_appDevDebug"})
public final class ProFragment extends androidx.fragment.app.Fragment implements com.example.adssdk.billing.Billing6Listener {
    @org.jetbrains.annotations.Nullable
    private com.app.update.softwareupdatekkappsstudio.databinding.FragmentProBinding binding;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<java.lang.String> productIds;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<java.lang.String> subscriptionIds;
    @org.jetbrains.annotations.Nullable
    private com.android.billingclient.api.BillingClient billingClient;
    @org.jetbrains.annotations.Nullable
    private com.example.adssdk.billing.Billing6Play billing6Play;
    @org.jetbrains.annotations.NotNull
    private java.lang.String logTag = "billingInAppClass";
    @org.jetbrains.annotations.NotNull
    private java.lang.String isSelected = "sub_weekly";
    private boolean isMonthly = false;
    private boolean isYearly = false;
    private boolean isWeekly = false;
    
    public ProFragment() {
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
    public void subList(@org.jetbrains.annotations.Nullable
    java.util.List<com.android.billingclient.api.ProductDetails> subscriptionDetailsList) {
    }
    
    @java.lang.Override
    public void purchasesList(@org.jetbrains.annotations.Nullable
    java.util.List<com.android.billingclient.api.ProductDetails> productDetailsList) {
    }
    
    @java.lang.Override
    public void purchaseOrSubDone() {
    }
    
    private final void agreementText() {
    }
    
    private final void openUrl(java.lang.String url) {
    }
    
    private final void changeDesignNow(java.lang.String type) {
    }
    
    private final void checkSubscription() {
    }
    
    @java.lang.Override
    public void isAnyOldDone(boolean isFoundProd, boolean isFoundSub) {
    }
    
    @java.lang.Override
    public void isAnyOldDoneSub(boolean isWeekly, boolean isMonthly, boolean isYearly) {
    }
}