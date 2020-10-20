package com.example.diagnostico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        setupOnboardingItems();

        final ViewPager2 onboardingViewPager = findViewById(R.id.buttonOnboardingViewPager);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        onboardingViewPager.setAdapter(onboardingAdapter);
        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() + 1  < onboardingAdapter.getItemCount())
                {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
            }
        });

    }

    private void setupOnboardingItems()
    {
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem screen1 = new OnboardingItem();
        screen1.setTitle("Title 1");
        screen1.setDesciption("Description 1");
        screen1.setImage(R.drawable.on1);

        OnboardingItem screen2 = new OnboardingItem();
        screen2.setTitle("Title 2");
        screen2.setDesciption("Description 2");
        screen2.setImage(R.drawable.on2);

        OnboardingItem screen3 = new OnboardingItem();
        screen3.setTitle("Title 3");
        screen3.setDesciption("Description 3");
        screen3.setImage(R.drawable.on3);

        onboardingItems.add(screen1);
        onboardingItems.add(screen2);
        onboardingItems.add(screen3);

        onboardingAdapter =  new OnboardingAdapter(onboardingItems);

    }

    private void setupOnboardingIndicators()
    {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0;i<indicators.length;i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicators_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index)
    {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i = 0;i<childCount;i++)
        {
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if(i == index)
            {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicators_active)
                );
            }
            else
            {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicators_inactive)
                );
            }
        }
        if(index == onboardingAdapter.getItemCount()-1)
        {
            buttonOnboardingAction.setText("Start");
        }
        else
        {
            buttonOnboardingAction.setText("Next");
        }
    }

}