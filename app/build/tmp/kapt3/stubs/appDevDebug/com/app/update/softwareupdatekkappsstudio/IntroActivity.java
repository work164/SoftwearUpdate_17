package com.app.update.softwareupdatekkappsstudio;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\b\u0010\u001a\u001a\u00020\u0015H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/app/update/softwareupdatekkappsstudio/IntroActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "btnNext", "Landroidx/appcompat/widget/AppCompatButton;", "btnSkip", "Landroid/widget/TextView;", "btnStart", "dotsLayout", "Lcom/tbuonomo/viewpagerdotsindicator/SpringDotsIndicator;", "onboardingItems", "Ljava/util/ArrayList;", "Lcom/app/update/softwareupdatekkappsstudio/model/OnboardingItem;", "Lkotlin/collections/ArrayList;", "onboardingPagerAdapter", "Lcom/app/update/softwareupdatekkappsstudio/adapter/OnboardingPagerAdapter;", "progressbarlottie", "Lcom/airbnb/lottie/LottieAnimationView;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setOnboardingComplete", "showOnboarding", "showStartButton", "app_appDevDebug"})
public final class IntroActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.Nullable
    private androidx.viewpager.widget.ViewPager viewPager;
    @org.jetbrains.annotations.Nullable
    private com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator dotsLayout;
    @org.jetbrains.annotations.Nullable
    private com.app.update.softwareupdatekkappsstudio.adapter.OnboardingPagerAdapter onboardingPagerAdapter;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<com.app.update.softwareupdatekkappsstudio.model.OnboardingItem> onboardingItems;
    @org.jetbrains.annotations.Nullable
    private androidx.appcompat.widget.AppCompatButton btnStart;
    @org.jetbrains.annotations.Nullable
    private androidx.appcompat.widget.AppCompatButton btnNext;
    @org.jetbrains.annotations.Nullable
    private android.widget.TextView btnSkip;
    @org.jetbrains.annotations.Nullable
    private com.airbnb.lottie.LottieAnimationView progressbarlottie;
    
    public IntroActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showOnboarding() {
    }
    
    private final void setOnboardingComplete() {
    }
    
    private final void showStartButton() {
    }
}