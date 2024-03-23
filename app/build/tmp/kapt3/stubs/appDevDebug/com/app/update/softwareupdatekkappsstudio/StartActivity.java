package com.app.update.softwareupdatekkappsstudio;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/StartActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "animation_view", "Landroid/widget/ImageView;", "btnStart", "Landroidx/appcompat/widget/AppCompatButton;", "getBtnStart", "()Landroidx/appcompat/widget/AppCompatButton;", "setBtnStart", "(Landroidx/appcompat/widget/AppCompatButton;)V", "googleMobileAdsConsentManager", "Lcom/app/update/softwareupdatekkappsstudio/ads/consent_message/GoogleMobileAdsConsentManager;", "intentGo", "Landroid/content/Intent;", "isFirstTime", "", "()Z", "progressbarlottie", "Lcom/airbnb/lottie/LottieAnimationView;", "termsTextView", "Landroid/widget/TextView;", "loadAds", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showAds", "Companion", "app_appDevDebug"})
public final class StartActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.Nullable
    private androidx.appcompat.widget.AppCompatButton btnStart;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView termsTextView;
    @org.jetbrains.annotations.Nullable
    private android.widget.ImageView animation_view;
    @org.jetbrains.annotations.Nullable
    private com.airbnb.lottie.LottieAnimationView progressbarlottie;
    @org.jetbrains.annotations.Nullable
    private android.content.Intent intentGo;
    private com.app.update.softwareupdatekkappsstudio.ads.consent_message.GoogleMobileAdsConsentManager googleMobileAdsConsentManager;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREFERENCES_FILE = "onboarding_preferences";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ONBOARDING_COMPLETE = "onboarding_complete";
    @org.jetbrains.annotations.NotNull
    public static final com.app.update.softwareupdatekkappsstudio.StartActivity.Companion Companion = null;
    
    public StartActivity() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final androidx.appcompat.widget.AppCompatButton getBtnStart() {
        return null;
    }
    
    public final void setBtnStart(@org.jetbrains.annotations.Nullable
    androidx.appcompat.widget.AppCompatButton p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadAds() {
    }
    
    private final void showAds() {
    }
    
    private final boolean isFirstTime() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/StartActivity$Companion;", "", "()V", "ONBOARDING_COMPLETE", "", "PREFERENCES_FILE", "app_appDevDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}