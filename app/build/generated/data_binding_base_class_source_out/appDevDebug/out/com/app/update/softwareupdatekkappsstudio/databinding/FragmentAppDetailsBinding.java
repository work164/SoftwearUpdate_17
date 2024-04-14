// Generated by view binder compiler. Do not edit!
package com.app.update.softwareupdatekkappsstudio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
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

public final class FragmentAppDetailsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppBarLayout actionBar;

  @NonNull
  public final FrameLayout appDetailsNativeAdOrBanner;

  @NonNull
  public final ImageFilterView backDevice;

  @NonNull
  public final ImageView btnInAppForwardDevice;

  @NonNull
  public final ConstraintLayout clAppVersion;

  @NonNull
  public final ConstraintLayout clLastUpdate;

  @NonNull
  public final ImageFilterView ifvAppDetailLaunch;

  @NonNull
  public final ImageFilterView ifvAppDetailShare;

  @NonNull
  public final ImageFilterView ifvAppDetailUninstall;

  @NonNull
  public final ImageFilterView ifvAppIcon;

  @NonNull
  public final TextView tvAppName;

  @NonNull
  public final TextView tvAppSize;

  @NonNull
  public final TextView tvAppVersion;

  @NonNull
  public final TextView tvLastUpdateValue;

  @NonNull
  public final TextView tvTextLastUpdate;

  @NonNull
  public final TextView tvVersion;

  @NonNull
  public final TextView tvViewOnPlayStore;

  private FragmentAppDetailsBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppBarLayout actionBar, @NonNull FrameLayout appDetailsNativeAdOrBanner,
      @NonNull ImageFilterView backDevice, @NonNull ImageView btnInAppForwardDevice,
      @NonNull ConstraintLayout clAppVersion, @NonNull ConstraintLayout clLastUpdate,
      @NonNull ImageFilterView ifvAppDetailLaunch, @NonNull ImageFilterView ifvAppDetailShare,
      @NonNull ImageFilterView ifvAppDetailUninstall, @NonNull ImageFilterView ifvAppIcon,
      @NonNull TextView tvAppName, @NonNull TextView tvAppSize, @NonNull TextView tvAppVersion,
      @NonNull TextView tvLastUpdateValue, @NonNull TextView tvTextLastUpdate,
      @NonNull TextView tvVersion, @NonNull TextView tvViewOnPlayStore) {
    this.rootView = rootView;
    this.actionBar = actionBar;
    this.appDetailsNativeAdOrBanner = appDetailsNativeAdOrBanner;
    this.backDevice = backDevice;
    this.btnInAppForwardDevice = btnInAppForwardDevice;
    this.clAppVersion = clAppVersion;
    this.clLastUpdate = clLastUpdate;
    this.ifvAppDetailLaunch = ifvAppDetailLaunch;
    this.ifvAppDetailShare = ifvAppDetailShare;
    this.ifvAppDetailUninstall = ifvAppDetailUninstall;
    this.ifvAppIcon = ifvAppIcon;
    this.tvAppName = tvAppName;
    this.tvAppSize = tvAppSize;
    this.tvAppVersion = tvAppVersion;
    this.tvLastUpdateValue = tvLastUpdateValue;
    this.tvTextLastUpdate = tvTextLastUpdate;
    this.tvVersion = tvVersion;
    this.tvViewOnPlayStore = tvViewOnPlayStore;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAppDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAppDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_app_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAppDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.action_bar;
      AppBarLayout actionBar = ViewBindings.findChildViewById(rootView, id);
      if (actionBar == null) {
        break missingId;
      }

      id = R.id.appDetailsNativeAdOrBanner;
      FrameLayout appDetailsNativeAdOrBanner = ViewBindings.findChildViewById(rootView, id);
      if (appDetailsNativeAdOrBanner == null) {
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

      id = R.id.clAppVersion;
      ConstraintLayout clAppVersion = ViewBindings.findChildViewById(rootView, id);
      if (clAppVersion == null) {
        break missingId;
      }

      id = R.id.clLastUpdate;
      ConstraintLayout clLastUpdate = ViewBindings.findChildViewById(rootView, id);
      if (clLastUpdate == null) {
        break missingId;
      }

      id = R.id.ifvAppDetailLaunch;
      ImageFilterView ifvAppDetailLaunch = ViewBindings.findChildViewById(rootView, id);
      if (ifvAppDetailLaunch == null) {
        break missingId;
      }

      id = R.id.ifvAppDetailShare;
      ImageFilterView ifvAppDetailShare = ViewBindings.findChildViewById(rootView, id);
      if (ifvAppDetailShare == null) {
        break missingId;
      }

      id = R.id.ifvAppDetailUninstall;
      ImageFilterView ifvAppDetailUninstall = ViewBindings.findChildViewById(rootView, id);
      if (ifvAppDetailUninstall == null) {
        break missingId;
      }

      id = R.id.ifvAppIcon;
      ImageFilterView ifvAppIcon = ViewBindings.findChildViewById(rootView, id);
      if (ifvAppIcon == null) {
        break missingId;
      }

      id = R.id.tvAppName;
      TextView tvAppName = ViewBindings.findChildViewById(rootView, id);
      if (tvAppName == null) {
        break missingId;
      }

      id = R.id.tvAppSize;
      TextView tvAppSize = ViewBindings.findChildViewById(rootView, id);
      if (tvAppSize == null) {
        break missingId;
      }

      id = R.id.tvAppVersion;
      TextView tvAppVersion = ViewBindings.findChildViewById(rootView, id);
      if (tvAppVersion == null) {
        break missingId;
      }

      id = R.id.tvLastUpdateValue;
      TextView tvLastUpdateValue = ViewBindings.findChildViewById(rootView, id);
      if (tvLastUpdateValue == null) {
        break missingId;
      }

      id = R.id.tvTextLastUpdate;
      TextView tvTextLastUpdate = ViewBindings.findChildViewById(rootView, id);
      if (tvTextLastUpdate == null) {
        break missingId;
      }

      id = R.id.tvVersion;
      TextView tvVersion = ViewBindings.findChildViewById(rootView, id);
      if (tvVersion == null) {
        break missingId;
      }

      id = R.id.tvViewOnPlayStore;
      TextView tvViewOnPlayStore = ViewBindings.findChildViewById(rootView, id);
      if (tvViewOnPlayStore == null) {
        break missingId;
      }

      return new FragmentAppDetailsBinding((ConstraintLayout) rootView, actionBar,
          appDetailsNativeAdOrBanner, backDevice, btnInAppForwardDevice, clAppVersion, clLastUpdate,
          ifvAppDetailLaunch, ifvAppDetailShare, ifvAppDetailUninstall, ifvAppIcon, tvAppName,
          tvAppSize, tvAppVersion, tvLastUpdateValue, tvTextLastUpdate, tvVersion,
          tvViewOnPlayStore);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
