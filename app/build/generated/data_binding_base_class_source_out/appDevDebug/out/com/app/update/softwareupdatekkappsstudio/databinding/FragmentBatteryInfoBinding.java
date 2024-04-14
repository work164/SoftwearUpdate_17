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

public final class FragmentBatteryInfoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppBarLayout actionBar;

  @NonNull
  public final ImageFilterView backDevice;

  @NonNull
  public final FrameLayout batteryNativeAdOrBanner;

  @NonNull
  public final ImageView btnInAppForwardDevice;

  @NonNull
  public final TextView device;

  @NonNull
  public final TextView deviceBoard;

  @NonNull
  public final TextView deviceHardware;

  @NonNull
  public final TextView deviceId;

  @NonNull
  public final TextView deviceManufacture;

  @NonNull
  public final TextView deviceModel;

  @NonNull
  public final TextView deviceName;

  @NonNull
  public final TextView deviceStatus;

  @NonNull
  public final TextView deviceTemperature;

  @NonNull
  public final ConstraintLayout group1;

  @NonNull
  public final ConstraintLayout group2;

  @NonNull
  public final ConstraintLayout group3;

  @NonNull
  public final ConstraintLayout group4;

  @NonNull
  public final ConstraintLayout group5;

  @NonNull
  public final ConstraintLayout group6;

  @NonNull
  public final ConstraintLayout group7;

  @NonNull
  public final ConstraintLayout group8;

  @NonNull
  public final ConstraintLayout group9;

  @NonNull
  public final View viewBattery;

  private FragmentBatteryInfoBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppBarLayout actionBar, @NonNull ImageFilterView backDevice,
      @NonNull FrameLayout batteryNativeAdOrBanner, @NonNull ImageView btnInAppForwardDevice,
      @NonNull TextView device, @NonNull TextView deviceBoard, @NonNull TextView deviceHardware,
      @NonNull TextView deviceId, @NonNull TextView deviceManufacture,
      @NonNull TextView deviceModel, @NonNull TextView deviceName, @NonNull TextView deviceStatus,
      @NonNull TextView deviceTemperature, @NonNull ConstraintLayout group1,
      @NonNull ConstraintLayout group2, @NonNull ConstraintLayout group3,
      @NonNull ConstraintLayout group4, @NonNull ConstraintLayout group5,
      @NonNull ConstraintLayout group6, @NonNull ConstraintLayout group7,
      @NonNull ConstraintLayout group8, @NonNull ConstraintLayout group9,
      @NonNull View viewBattery) {
    this.rootView = rootView;
    this.actionBar = actionBar;
    this.backDevice = backDevice;
    this.batteryNativeAdOrBanner = batteryNativeAdOrBanner;
    this.btnInAppForwardDevice = btnInAppForwardDevice;
    this.device = device;
    this.deviceBoard = deviceBoard;
    this.deviceHardware = deviceHardware;
    this.deviceId = deviceId;
    this.deviceManufacture = deviceManufacture;
    this.deviceModel = deviceModel;
    this.deviceName = deviceName;
    this.deviceStatus = deviceStatus;
    this.deviceTemperature = deviceTemperature;
    this.group1 = group1;
    this.group2 = group2;
    this.group3 = group3;
    this.group4 = group4;
    this.group5 = group5;
    this.group6 = group6;
    this.group7 = group7;
    this.group8 = group8;
    this.group9 = group9;
    this.viewBattery = viewBattery;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentBatteryInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentBatteryInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_battery_info, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentBatteryInfoBinding bind(@NonNull View rootView) {
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

      id = R.id.batteryNativeAdOrBanner;
      FrameLayout batteryNativeAdOrBanner = ViewBindings.findChildViewById(rootView, id);
      if (batteryNativeAdOrBanner == null) {
        break missingId;
      }

      id = R.id.btn_inAppForwardDevice;
      ImageView btnInAppForwardDevice = ViewBindings.findChildViewById(rootView, id);
      if (btnInAppForwardDevice == null) {
        break missingId;
      }

      id = R.id.device;
      TextView device = ViewBindings.findChildViewById(rootView, id);
      if (device == null) {
        break missingId;
      }

      id = R.id.deviceBoard;
      TextView deviceBoard = ViewBindings.findChildViewById(rootView, id);
      if (deviceBoard == null) {
        break missingId;
      }

      id = R.id.deviceHardware;
      TextView deviceHardware = ViewBindings.findChildViewById(rootView, id);
      if (deviceHardware == null) {
        break missingId;
      }

      id = R.id.deviceId;
      TextView deviceId = ViewBindings.findChildViewById(rootView, id);
      if (deviceId == null) {
        break missingId;
      }

      id = R.id.deviceManufacture;
      TextView deviceManufacture = ViewBindings.findChildViewById(rootView, id);
      if (deviceManufacture == null) {
        break missingId;
      }

      id = R.id.deviceModel;
      TextView deviceModel = ViewBindings.findChildViewById(rootView, id);
      if (deviceModel == null) {
        break missingId;
      }

      id = R.id.deviceName;
      TextView deviceName = ViewBindings.findChildViewById(rootView, id);
      if (deviceName == null) {
        break missingId;
      }

      id = R.id.deviceStatus;
      TextView deviceStatus = ViewBindings.findChildViewById(rootView, id);
      if (deviceStatus == null) {
        break missingId;
      }

      id = R.id.deviceTemperature;
      TextView deviceTemperature = ViewBindings.findChildViewById(rootView, id);
      if (deviceTemperature == null) {
        break missingId;
      }

      id = R.id.group1;
      ConstraintLayout group1 = ViewBindings.findChildViewById(rootView, id);
      if (group1 == null) {
        break missingId;
      }

      id = R.id.group2;
      ConstraintLayout group2 = ViewBindings.findChildViewById(rootView, id);
      if (group2 == null) {
        break missingId;
      }

      id = R.id.group3;
      ConstraintLayout group3 = ViewBindings.findChildViewById(rootView, id);
      if (group3 == null) {
        break missingId;
      }

      id = R.id.group4;
      ConstraintLayout group4 = ViewBindings.findChildViewById(rootView, id);
      if (group4 == null) {
        break missingId;
      }

      id = R.id.group5;
      ConstraintLayout group5 = ViewBindings.findChildViewById(rootView, id);
      if (group5 == null) {
        break missingId;
      }

      id = R.id.group6;
      ConstraintLayout group6 = ViewBindings.findChildViewById(rootView, id);
      if (group6 == null) {
        break missingId;
      }

      id = R.id.group7;
      ConstraintLayout group7 = ViewBindings.findChildViewById(rootView, id);
      if (group7 == null) {
        break missingId;
      }

      id = R.id.group8;
      ConstraintLayout group8 = ViewBindings.findChildViewById(rootView, id);
      if (group8 == null) {
        break missingId;
      }

      id = R.id.group9;
      ConstraintLayout group9 = ViewBindings.findChildViewById(rootView, id);
      if (group9 == null) {
        break missingId;
      }

      id = R.id.viewBattery;
      View viewBattery = ViewBindings.findChildViewById(rootView, id);
      if (viewBattery == null) {
        break missingId;
      }

      return new FragmentBatteryInfoBinding((ConstraintLayout) rootView, actionBar, backDevice,
          batteryNativeAdOrBanner, btnInAppForwardDevice, device, deviceBoard, deviceHardware,
          deviceId, deviceManufacture, deviceModel, deviceName, deviceStatus, deviceTemperature,
          group1, group2, group3, group4, group5, group6, group7, group8, group9, viewBattery);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
