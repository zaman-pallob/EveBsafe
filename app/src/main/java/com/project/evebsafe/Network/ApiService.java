package com.project.evebsafe.Network;

import com.project.evebsafe.Model.Restore;
import com.project.evebsafe.Model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("recieveData.php")
    Call<Result> register(@Field("Number") String number , @Field("Name") String name,@Field("Email") String email , @Field("Address") String address);

    @FormUrlEncoded
    @POST("dataRetrive.php")
    Call<ArrayList<Restore>> registrationCheck(@Field("Number") String number );


}
