package com.exam.foodit.Helper;

import com.exam.foodit.Model.Responce_Model;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.exam.foodit.Helper.Constant.Category;
import static com.exam.foodit.Helper.Constant.Item;
import static com.exam.foodit.Helper.Constant.Order;

public interface ApiService {
    @POST(Category)
    Call<Responce_Model> requestCategory();

    @POST(Item)
    Call<Responce_Model> requestItem();

    @FormUrlEncoded
    @POST(Order)
    Call<Responce_Model> requestOrder(@FieldMap HashMap<String, String> params);
}
