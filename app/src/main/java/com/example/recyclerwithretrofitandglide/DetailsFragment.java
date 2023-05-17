package com.example.recyclerwithretrofitandglide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

import org.w3c.dom.Text;


public class DetailsFragment extends Fragment {

    TextView titleTextView;
    TextView priceTextView;
    TextView descTextView;
    TextView brandTextView;
    RatingBar ratingBar;
    ImageView image;
    private Product product;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_details, container, false);

        titleTextView = view.findViewById(R.id.titleTextView);
        priceTextView = view.findViewById(R.id.priceTextView);
        descTextView = view.findViewById(R.id.descTextView);
        brandTextView = view.findViewById(R.id.brandTextView);
        ratingBar = view.findViewById(R.id.ratingBar2);
        ratingBar.setIsIndicator(true);
        image = view.findViewById(R.id.product_image);

        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getActivity().getClass().getSimpleName().equals("Details")) {

            product = ((Details) getActivity()).product;


        }

        if (savedInstanceState != null)
        {

            product = savedInstanceState.getParcelable("product");

        }
        if(product != null)
        {
            setProductData();
        }


    }

    public void setMessageText(Product product)
    {
        this.product = product;
        setProductData();
    }


    private void setProductData()
    {




        Glide.with(getContext())
                .load(product.getImages().get(0))
                .override(300, 200).downsample(DownsampleStrategy.CENTER_INSIDE)
                .into(image);
        titleTextView.setText(product.getTitle());
        priceTextView.setText(getString(R.string.price)+product.getPrice()+getString(R.string.dollar));
        descTextView.setText(getString(R.string.brand)+product.getDescription());
        brandTextView.setText(product.getBrand());
        ratingBar.setRating(Float.parseFloat(product.getRating()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("product",product);
    }
}

