// Generated by view binder compiler. Do not edit!
package com.app.update.softwareupdatekkappsstudio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.update.softwareupdatekkappsstudio.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NavHeaderDrawerBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout backbtnDrawer;

  private NavHeaderDrawerBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout backbtnDrawer) {
    this.rootView = rootView;
    this.backbtnDrawer = backbtnDrawer;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NavHeaderDrawerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NavHeaderDrawerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.nav_header_drawer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NavHeaderDrawerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backbtnDrawer;
      LinearLayout backbtnDrawer = ViewBindings.findChildViewById(rootView, id);
      if (backbtnDrawer == null) {
        break missingId;
      }

      return new NavHeaderDrawerBinding((LinearLayout) rootView, backbtnDrawer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}