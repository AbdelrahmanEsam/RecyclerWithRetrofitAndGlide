package com.example.recyclerwithretrofitandglide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class Details extends AppCompatActivity {

    FragmentContainerView containerView;
    private DetailsFragment fragmentTwo ;
    FragmentManager manager ;

    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            finish();
        }
        product =  getIntent().getExtras().getParcelable("product");


        manager = getSupportFragmentManager();
        fragmentTwo =  (DetailsFragment) manager.findFragmentById(R.id.fragmentContainerTwo);
        containerView = findViewById(R.id.fragmentContainerTwo);
        if(fragmentTwo == null) {
            fragmentTwo = new DetailsFragment();

            manager.beginTransaction().add(R.id.fragmentContainerTwo,fragmentTwo,"fragmentTwo")
                    .addToBackStack("fragmentTwo").commit();

        }


    }

    @Override
    public void onBackPressed() {
    //    super.onBackPressed();
        finish();
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}

