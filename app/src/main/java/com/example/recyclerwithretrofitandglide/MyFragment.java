package com.example.recyclerwithretrofitandglide;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MyFragment extends Fragment {


    private  RecyclerView recyclerView;

    private AppCompatButton details;

     private int lastScrollPosition;
    private FragmentCommunicator communicator;
    private Product product ;



  final private List<Product> products = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

       recyclerView = view.findViewById(R.id.recycler);

           details = view.findViewById(R.id.details_button);

        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        communicator = (MainActivity) getActivity();
        Retrofit client = APIClient.getClient();

        if (savedInstanceState != null)
        {

            lastScrollPosition = savedInstanceState.getInt("index");
        }

        RecyclerAdapter adapter  = new RecyclerAdapter();
        client.create(ApiProvider.class).getProducts().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                products.addAll(response.body().getProducts());
                adapter.setData(products,getContext());
                if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                    product = products.get(lastScrollPosition);
                    communicator.sendData(product);

                }
                recyclerView.smoothScrollToPosition(lastScrollPosition);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

        int oreintation = RecyclerView.HORIZONTAL ;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            oreintation = RecyclerView.VERTICAL;

        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), oreintation, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        Log.d("index onView",String.valueOf(lastScrollPosition));




        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                   lastScrollPosition = ((LinearLayoutManager)recyclerView.getLayoutManager())
                           .findFirstVisibleItemPosition();

                    product =products.get(lastScrollPosition);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE )
                    {
                        communicator.sendData(product);
                    }


                }


            }
        });



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            details.setOnClickListener(view1 -> {
                if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                    Intent intent = new Intent(getActivity(), Details.class);
                    intent.putExtra("product", product);
                    startActivity(intent);

                } else {
                    communicator.sendData(product);
                }
            });
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index",lastScrollPosition);
    }
}