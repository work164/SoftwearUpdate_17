package com.app.update.softwareupdatekkappsstudio.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.update.softwareupdatekkappsstudio.R;

public class AndroidUpdateFragment extends Fragment {

    public AndroidUpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnOSNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    openSystemUpdateSettings();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openSystemUpdateSettings() {
        try {
            requireActivity().startActivityForResult(new Intent("android.settings.SYSTEM_UPDATE_SETTINGS"), 0);
        } catch (SecurityException | ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }
}

