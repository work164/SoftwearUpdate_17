// Generated by view binder compiler. Do not edit!
package com.app.update.softwareupdatekkappsstudio.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.app.update.softwareupdatekkappsstudio.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LoadingAdBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView loadingAdCard;

  @NonNull
  public final ProgressBar loadingAdProgress;

  @NonNull
  public final TextView loadingAdText;

  private LoadingAdBinding(@NonNull ConstraintLayout rootView, @NonNull CardView loadingAdCard,
      @NonNull ProgressBar loadingAdProgress, @NonNull TextView loadingAdText) {
    this.rootView = rootView;
    this.loadingAdCard = loadingAdCard;
    this.loadingAdProgress = loadingAdProgress;
    this.loadingAdText = loadingAdText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LoadingAdBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LoadingAdBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.loading_ad, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LoadingAdBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.loadingAdCard;
      CardView loadingAdCard = ViewBindings.findChildViewById(rootView, id);
      if (loadingAdCard == null) {
        break missingId;
      }

      id = R.id.loadingAdProgress;
      ProgressBar loadingAdProgress = ViewBindings.findChildViewById(rootView, id);
      if (loadingAdProgress == null) {
        break missingId;
      }

      id = R.id.loadingAdText;
      TextView loadingAdText = ViewBindings.findChildViewById(rootView, id);
      if (loadingAdText == null) {
        break missingId;
      }

      return new LoadingAdBinding((ConstraintLayout) rootView, loadingAdCard, loadingAdProgress,
          loadingAdText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}