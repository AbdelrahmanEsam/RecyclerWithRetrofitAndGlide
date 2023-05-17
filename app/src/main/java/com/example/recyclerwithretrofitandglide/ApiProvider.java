package com.example.recyclerwithretrofitandglide;


import retrofit2.Call;
import retrofit2.http.GET;


interface ApiProvider {

    @GET("product")
    Call<Response> getProducts();

}
