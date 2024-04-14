// Generated by view binder compiler. Do not edit!
package com.app.update.softwareupdatekkappsstudio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.update.softwareupdatekkappsstudio.R;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRestoreAppsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppBarLayout actionBar;

  @NonNull
  public final ImageFilterView backDevice;

  @NonNull
  public final ImageView btnInAppForwardDevice;

  private FragmentRestoreAppsBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppBarLayout actionBar, @NonNull ImageFilterView backDevice,
      @NonNull ImageView btnInAppForwardDevice) {
    this.rootView = rootView;
    this.actionBar = actionBar;
    this.backDevice = backDevice;
    this.btnInAppForwardDevice = btnInAppForwardDevice;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRestoreAppsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRestoreAppsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_restore_apps, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRestoreAppsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.action_bar;
      AppBarLayout actionBar = ViewBindings.findChildViewById(rootView, id);
      if (actionBar == null) {
        break missingId;
      }

      id = R.id.backDevice;
      ImageFilterView backDevice = ViewBindings.findChildViewById(rootView, id);
      if (backDevice == null) {
        break missingId;
      }

      id = R.id.btn_inAppForwardDevice;
      ImageView btnInAppForwardDevice = ViewBindings.findChildViewById(rootView, id);
      if (btnInAppForwardDevice == null) {
        break missingId;
      }

      return new FragmentRestoreAppsBinding((ConstraintLayout) rootView, actionBar, backDevice,
          btnInAppForwardDevice);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}