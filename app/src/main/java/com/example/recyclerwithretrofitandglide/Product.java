package com.example.recyclerwithretrofitandglide;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Product implements Parcelable {
    private String id;
    private String title;
    private String description;
    private String price;
    private String discountPercentage;
    private String rating;
    private String stock;
    private String brand;
    private String category;
    private String thumbnail;
    ArrayList< String > images = new ArrayList<>();

    public Product(String id, String title, String description, String price, String discountPercentage, String rating, String stock, String brand, String category, String thumbnail, ArrayList<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
        this.images = images;
    }

    // Getter Methods

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.price);
        dest.writeString(this.discountPercentage);
        dest.writeString(this.rating);
        dest.writeString(this.stock);
        dest.writeString(this.brand);
        dest.writeString(this.category);
        dest.writeString(this.thumbnail);
        dest.writeStringList(this.images);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readString();
        this.title = source.readString();
        this.description = source.readString();
        this.price = source.readString();
        this.discountPercentage = source.readString();
        this.rating = source.readString();
        this.stock = source.readString();
        this.brand = source.readString();
        this.category = source.readString();
        this.thumbnail = source.readString();
        this.images = source.createStringArrayList();
    }

    protected Product(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.price = in.readString();
        this.discountPercentage = in.readString();
        this.rating = in.readString();
        this.stock = in.readString();
        this.brand = in.readString();
        this.category = in.readString();
        this.thumbnail = in.readString();
        this.images = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
