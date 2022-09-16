package com.exam.foodit.Helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.exam.foodit.Activity.LoginPage;
import com.exam.foodit.Activity.MainActivity;
import com.exam.foodit.Activity.OrderPage;
import com.exam.foodit.Fragment.MainFragment;
import com.exam.foodit.Model.Responce_Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallApi  {



//    public ProgressDialog progressDialog;
    Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .disableHtmlEscaping()
            .setLenient()
            .create();

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASIC_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    ApiService service = retrofit.create(ApiService.class);

//    public void dialogShow(Context context, String msg) {
//        if (progressDialog == null) {
//            progressDialog = new ProgressDialog(context);
//            progressDialog.setMessage(msg);
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//        }
//    }
//
//    public void dialogHide() {
//        if (progressDialog != null) {
//            if (progressDialog.isShowing())
//                progressDialog.dismiss();
//            progressDialog = null;
//        }
//    }

    public void getCategoryList(final LoginPage context) {
        final KProgressHUD progressDialog = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        Call<Responce_Model> call = service.requestCategory();
        call.enqueue(new Callback<Responce_Model>() {
            @Override
            public void onResponse(Call<Responce_Model> call, Response<Responce_Model> response) {
              progressDialog.dismiss();
                if (response.body() != null){
                    context.responseCategory(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<Responce_Model> call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                progressDialog.dismiss();
                context.customToast("server_not_responding..!");
            }
        });
    }

    public void getitemList(final MainFragment context) {

        final KProgressHUD progressDialog = KProgressHUD.create(context.getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        Call<Responce_Model> call = service.requestItem();
        call.enqueue(new Callback<Responce_Model>() {
            @Override
            public void onResponse(Call<Responce_Model> call, Response<Responce_Model> response) {
                progressDialog.dismiss();
                if (response.body() != null){
                    context.responseItem(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<Responce_Model> call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                progressDialog.dismiss();
                context.customToast("server_not_responding..!");
            }
        });
    }

    public void getSubmit(final OrderPage context, HashMap<String, String> map) {
        final KProgressHUD progressDialog = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        Call<Responce_Model> call = service.requestOrder(map);
        call.enqueue(new Callback<Responce_Model>() {
            @Override
            public void onResponse(Call<Responce_Model> call, Response<Responce_Model> response) {
                progressDialog.dismiss();
                if (response.body() != null){
                    context.responseItem(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<Responce_Model> call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                progressDialog.dismiss();
                context.customToast("server_not_responding..!");
            }
        });
    }
}
