// Generated by view binder compiler. Do not edit!
package com.app.update.softwareupdatekkappsstudio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.update.softwareupdatekkappsstudio.R;
import com.google.android.material.appbar.AppBarLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSystemAppBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppBarLayout actionBar;

  @NonNull
  public final TextView appMainText;

  @NonNull
  public final ImageFilterView backHome;

  @NonNull
  public final ImageView btnInAppForward;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final TextView totalCount;

  @NonNull
  public final View viewSystem;

  private FragmentSystemAppBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppBarLayout actionBar, @NonNull TextView appMainText,
      @NonNull ImageFilterView backHome, @NonNull ImageView btnInAppForward,
      @NonNull RecyclerView recyclerView, @NonNull TextView totalCount, @NonNull View viewSystem) {
    this.rootView = rootView;
    this.actionBar = actionBar;
    this.appMainText = appMainText;
    this.backHome = backHome;
    this.btnInAppForward = btnInAppForward;
    this.recyclerView = recyclerView;
    this.totalCount = totalCount;
    this.viewSystem = viewSystem;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSystemAppBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSystemAppBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_system_app, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSystemAppBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.action_bar;
      AppBarLayout actionBar = ViewBindings.findChildViewById(rootView, id);
      if (actionBar == null) {
        break missingId;
      }

      id = R.id.appMainText;
      TextView appMainText = ViewBindings.findChildViewById(rootView, id);
      if (appMainText == null) {
        break missingId;
      }

      id = R.id.backHome;
      ImageFilterView backHome = ViewBindings.findChildViewById(rootView, id);
      if (backHome == null) {
        break missingId;
      }

      id = R.id.btn_inAppForward;
      ImageView btnInAppForward = ViewBindings.findChildViewById(rootView, id);
      if (btnInAppForward == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.totalCount;
      TextView totalCount = ViewBindings.findChildViewById(rootView, id);
      if (totalCount == null) {
        break missingId;
      }

      id = R.id.viewSystem;
      View viewSystem = ViewBindings.findChildViewById(rootView, id);
      if (viewSystem == null) {
        break missingId;
      }

      return new FragmentSystemAppBinding((ConstraintLayout) rootView, actionBar, appMainText,
          backHome, btnInAppForward, recyclerView, totalCount, viewSystem);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
