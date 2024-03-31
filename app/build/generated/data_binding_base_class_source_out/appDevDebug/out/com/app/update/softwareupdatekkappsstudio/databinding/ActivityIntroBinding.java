// Generated by view binder compiler. Do not edit!
package com.app.update.softwareupdatekkappsstudio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.app.update.softwareupdatekkappsstudio.R;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityIntroBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnNext;

  @NonNull
  public final TextView btnSkip;

  @NonNull
  public final FrameLayout flNativeContainer;

  @NonNull
  public final SpringDotsIndicator indicatorLayout;

  @NonNull
  public final ViewPager viewPager;

  private ActivityIntroBinding(@NonNull ConstraintLayout rootView, @NonNull AppCompatButton btnNext,
      @NonNull TextView btnSkip, @NonNull FrameLayout flNativeContainer,
      @NonNull SpringDotsIndicator indicatorLayout, @NonNull ViewPager viewPager) {
    this.rootView = rootView;
    this.btnNext = btnNext;
    this.btnSkip = btnSkip;
    this.flNativeContainer = flNativeContainer;
    this.indicatorLayout = indicatorLayout;
    this.viewPager = viewPager;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityIntroBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityIntroBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_intro, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityIntroBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnNext;
      AppCompatButton btnNext = ViewBindings.findChildViewById(rootView, id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.btnSkip;
      TextView btnSkip = ViewBindings.findChildViewById(rootView, id);
      if (btnSkip == null) {
        break missingId;
      }

      id = R.id.flNativeContainer;
      FrameLayout flNativeContainer = ViewBindings.findChildViewById(rootView, id);
      if (flNativeContainer == null) {
        break missingId;
      }

      id = R.id.indicatorLayout;
      SpringDotsIndicator indicatorLayout = ViewBindings.findChildViewById(rootView, id);
      if (indicatorLayout == null) {
        break missingId;
      }

      id = R.id.viewPager;
      ViewPager viewPager = ViewBindings.findChildViewById(rootView, id);
      if (viewPager == null) {
        break missingId;
      }

      return new ActivityIntroBinding((ConstraintLayout) rootView, btnNext, btnSkip,
          flNativeContainer, indicatorLayout, viewPager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}