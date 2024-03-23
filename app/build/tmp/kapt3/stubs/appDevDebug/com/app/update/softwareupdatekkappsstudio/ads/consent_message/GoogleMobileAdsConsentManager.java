package com.app.update.softwareupdatekkappsstudio.ads.consent_message;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014R\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\b\u00a8\u0006\u0017"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/ads/consent_message/GoogleMobileAdsConsentManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "canRequestAds", "", "getCanRequestAds", "()Z", "consentInformation", "Lcom/google/android/ump/ConsentInformation;", "isPrivacyOptionsRequired", "gatherConsent", "", "activity", "Landroid/app/Activity;", "onConsentGatheringCompleteListener", "Lcom/app/update/softwareupdatekkappsstudio/ads/consent_message/GoogleMobileAdsConsentManager$OnConsentGatheringCompleteListener;", "showPrivacyOptionsForm", "onConsentFormDismissedListener", "Lcom/google/android/ump/ConsentForm$OnConsentFormDismissedListener;", "Companion", "OnConsentGatheringCompleteListener", "app_appDevDebug"})
public final class GoogleMobileAdsConsentManager {
    @org.jetbrains.annotations.NotNull
    private final com.google.android.ump.ConsentInformation consentInformation = null;
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.app.update.softwareupdatekkappsstudio.ads.consent_message.GoogleMobileAdsConsentManager instance;
    @org.jetbrains.annotations.NotNull
    public static final com.app.update.softwareupdatekkappsstudio.ads.consent_message.GoogleMobileAdsConsentManager.Companion Companion = null;
    
    private GoogleMobileAdsConsentManager(android.content.Context context) {
        super();
    }
    
    public final boolean getCanRequestAds() {
        return false;
    }
    
    public final boolean isPrivacyOptionsRequired() {
        return false;
    }
    
    /**
     * Helper method to call the UMP SDK methods to request consent information and load/show a
     * consent form if necessary.
     */
    public final void gatherConsent(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    com.app.update.softwareupdatekkappsstudio.ads.consent_message.GoogleMobileAdsConsentManager.OnConsentGatheringCompleteListener onConsentGatheringCompleteListener) {
    }
    
    /**
     * Helper method to call the UMP SDK method to show the privacy options form.
     */
    public final void showPrivacyOptionsForm(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    com.google.android.ump.ConsentForm.OnConsentFormDismissedListener onConsentFormDismissedListener) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/ads/consent_message/GoogleMobileAdsConsentManager$Companion;", "", "()V", "instance", "Lcom/app/update/softwareupdatekkappsstudio/ads/consent_message/GoogleMobileAdsConsentManager;", "getInstance", "context", "Landroid/content/Context;", "app_appDevDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.app.update.softwareupdatekkappsstudio.ads.consent_message.GoogleMobileAdsConsentManager getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
    
    /**
     * Interface definition for a callback to be invoked when consent gathering is complete.
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/ads/consent_message/GoogleMobileAdsConsentManager$OnConsentGatheringCompleteListener;", "", "consentGatheringComplete", "", "error", "Lcom/google/android/ump/FormError;", "app_appDevDebug"})
    public static abstract interface OnConsentGatheringCompleteListener {
        
        public abstract void consentGatheringComplete(@org.jetbrains.annotations.Nullable
        com.google.android.ump.FormError error);
    }
}