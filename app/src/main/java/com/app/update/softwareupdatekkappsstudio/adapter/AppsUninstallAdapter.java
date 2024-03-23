package com.app.update.softwareupdatekkappsstudio.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.app.update.softwareupdatekkappsstudio.AppInfo;
import com.app.update.softwareupdatekkappsstudio.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AppsUninstallAdapter extends RecyclerView.Adapter<AppsUninstallAdapter.ViewHolder> {

    private List<AppInfo> appsList;
    private Context context;

    public AppsUninstallAdapter(List<AppInfo> appsList, Context context) {
        this.appsList = appsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app_uninsatll_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppInfo currentApp = appsList.get(position);
        holder.appName.setText(currentApp.getAppName());
        Glide.with(context).load(currentApp.getAppIcon()).into(holder.appIcon);



        holder.checkButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Uninstall App");
            builder.setMessage("Do you want to uninstall " + currentApp.getAppName() + "?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Uri packageUri = Uri.parse("package:" + currentApp.getPackageName());
                    Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
                    context.startActivity(uninstallIntent);
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
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

