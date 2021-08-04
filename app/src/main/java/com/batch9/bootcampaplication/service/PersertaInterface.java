package com.batch9.bootcampaplication.service;

import com.batch9.bootcampaplication.entity.Perserta;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PersertaInterface {
    @GET("/perserta/")
    Call<ArrayList<Perserta>> getall();

    @Multipart
    @POST("/perserta/add")
    Call<String> addPerserta(@Part MultipartBody.Part file, @Part("data") RequestBody data);

    @GET("/perserta/name/{name}")
    Call<ArrayList<Perserta>> getAllByName(@Path("name") String name);

    @GET("/perserta/id/{id}")
    Call<Perserta> getById(@Path("id") String id);

    @DELETE("/perserta/delete/{id}")
    Call<String> deleteById(@Path("id")String id);
}
