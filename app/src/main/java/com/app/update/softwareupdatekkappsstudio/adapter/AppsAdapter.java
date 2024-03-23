package com.app.update.softwareupdatekkappsstudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.app.update.softwareupdatekkappsstudio.AppInfo;
import com.app.update.softwareupdatekkappsstudio.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private List<AppInfo> appsList;
    private Context context;

    public AppsAdapter(List<AppInfo> appsList, Context context) {
        this.appsList = appsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppInfo currentApp = appsList.get(position);
        holder.appName.setText(currentApp.getAppName());
        Glide.with(context).load(currentApp.getAppIcon()).into(holder.appIcon);
//        holder.appIcon.setImageDrawable(currentApp.getAppIcon());

        holder.checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=" + currentApp.getPackageName()));
                Log.d("yoyuit", "onClick: "+Uri.parse("market://details?id=" + currentApp.getPackageName()));
                intent.setPackage("com.android.vending"); // Explicitly set the Play Store app to handle the intent
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appIcon;
        TextView appName;
        AppCompatButton checkButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.appIcon);
            appName = itemView.findViewById(R.id.appName);
            checkButton = itemView.findViewById(R.id.checkButton);
        }
    }
}

