package com.example.recyclerwithretrofitandglide;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Product> products = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(products.get(position).getTitle());
     holder.price.setText(context.getString(R.string.price)+products.get(position).getPrice()+"  $");
     holder.brand.setText(context.getString(R.string.brand)+products.get(position).getBrand());
       holder.desc.setText(products.get(position).getDescription());
        Log.d("onBindViewHolder",products.get(position).getDescription());
        Glide.with(context)
                .load(products.get(position).getImages().get(0))
                .override(300, 200).downsample(DownsampleStrategy.CENTER_INSIDE)
                .into(holder.image);
        holder.layout.setOnClickListener(view -> {

            Toast.makeText(view.getContext(), products.get(position).getTitle(),Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public void setData(List<Product> products,Context context)
    {

        this.products = products;
        this.context = context;
        Log.d("recyclerMy","yes" + products.size());
        notifyDataSetChanged();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView price ;
        TextView brand ;
        TextView desc ;

        ImageView image;
        View layout ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.layout = itemView;
            title = layout.findViewById(R.id.titleTextView);
            price = layout.findViewById(R.id.priceTextView);
            brand = layout.findViewById(R.id.brandTextView);
            desc = layout.findViewById(R.id.descTextView);
            image = layout.findViewById(R.id.product_image);



        }
    }
}
