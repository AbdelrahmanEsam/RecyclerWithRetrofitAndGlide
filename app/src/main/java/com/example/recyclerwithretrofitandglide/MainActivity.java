package com.example.recyclerwithretrofitandglide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator{

    FragmentContainerView containerView,containerViewTwo;
    private MyFragment fragmentOne ;
    private DetailsFragment fragmentTwo ;
    FragmentManager manager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        manager = getSupportFragmentManager();
        fragmentOne =  (MyFragment) manager.findFragmentById(R.id.fragmentContainerOne);
        fragmentTwo = (DetailsFragment) manager.findFragmentById(R.id.fragmentContainerTwo);
        containerView = findViewById(R.id.fragmentContainerOne);

        if(fragmentOne == null) {

            fragmentOne = new MyFragment();
            manager.beginTransaction().add(R.id.fragmentContainerOne,fragmentOne,"fragmentOne")
                    .addToBackStack("fragmentOne").commit();

        }



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            containerViewTwo = findViewById(R.id.fragmentContainerTwo);
            if (fragmentTwo == null) {
                fragmentTwo = new DetailsFragment();

                manager.beginTransaction().add(R.id.fragmentContainerTwo, fragmentTwo, "fragmentTwo")
                        .addToBackStack("fragmentTwo").commit();

            }
        }









    }




    @Override
    public void sendData(Product product) {


            ((DetailsFragment)fragmentTwo).setMessageText(product);

    }



}