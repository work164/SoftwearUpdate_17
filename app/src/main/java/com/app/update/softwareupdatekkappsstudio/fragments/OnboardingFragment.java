package com.app.update.softwareupdatekkappsstudio.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.update.softwareupdatekkappsstudio.R;
import com.app.update.softwareupdatekkappsstudio.model.OnboardingItem;

import java.util.Arrays;
import java.util.List;

public class OnboardingFragment extends Fragment {

    private ImageView imageView;
    private TextView textView;
    private OnboardingItem onboardingItem;

    public OnboardingFragment(OnboardingItem item) {
        this.onboardingItem = item;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_onboarding, container, false);

        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.textView);

        imageView.setImageResource(onboardingItem.getImage());

        // List of words to highlight in blue
        List<String> wordsToHighlight = Arrays.asList("Update", "Installing", "System");

        SpannableString coloredText = colorSpecificTexts(onboardingItem.getDescription(), wordsToHighlight, R.color.link_color_frag);
        textView.setText(coloredText);



        return view;
    }

    public static SpannableString colorSpecificTexts(String text, List<String> targetWords, int color) {
        SpannableString spannable = new SpannableString(text);

        for (String targetWord : targetWords) {
            int startIndex = text.indexOf(targetWord);
            while (startIndex != -1) {
                int endIndex = startIndex + targetWord.length();
                spannable.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                startIndex = text.indexOf(targetWord, endIndex);
            }
        }
        return spannable;
    }


}
