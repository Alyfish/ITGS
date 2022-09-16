package com.exam.foodit.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.foodit.Helper.CallApi;
import com.exam.foodit.Helper.CommonSharedPreference;
import com.exam.foodit.Helper.PayPalConfig;
import com.exam.foodit.Model.Category;
import com.exam.foodit.Model.OrderClass;
import com.exam.foodit.Model.Responce_Model;
import com.exam.foodit.Model.User;
import com.exam.foodit.R;
import com.exam.foodit.Sqlite.DatabaseHelper;
import com.google.gson.reflect.TypeToken;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderPage extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper mydb;
    RecyclerView recycle_View;
    Button cart_btn;
    JSONArray SaveArray = new JSONArray();
    User user;
    EditText email, mobile, add;
    private CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    public static final int PAYPAL_REQUEST_CODE = 123;
    private static PayPalConfiguration configpaypal;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        user = commonSharedPreference.getUserLoginSharedPref(this);

        recycle_View = findViewById(R.id.recycle_View);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycle_View.setLayoutManager(linearLayoutManager);
        mydb = new DatabaseHelper(this);

        cart_btn = findViewById(R.id.cart_btn);
        email =  findViewById(R.id.email);
        mobile =  findViewById(R.id.mobile);
        add =  findViewById(R.id.add);


        cart_btn.setOnClickListener(this);

        getdate();
        /*ArrayList<OrderClass> list = new ArrayList<OrderClass>();
        Cursor data = mydb.Get_OrderDetails();  //contain all data
        if (data.getCount() == 0) {

        } else {
            while (data.moveToNext()) {
                list.add(new OrderClass(data.getString(0), data.getString(1), data.getString(2), data.getString(3)));
                JSONObject contact = new JSONObject();
                try {
                    contact.put("name",data.getString(1));
                    contact.put("quantity",data.getString(2));
                    contact.put("price",data.getString(3));
                    SaveArray.put(contact);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        OrderAdapter orderAdapter = new OrderAdapter(getApplicationContext(), list);
        recycle_View.setAdapter(orderAdapter);*/
    }

    public void getdate(){
        ArrayList<OrderClass> list = new ArrayList<OrderClass>();
        Cursor data = mydb.Get_OrderDetails();  //contain all data
        if (data.getCount() == 0) {

        } else {
            while (data.moveToNext()) {
                list.add(new OrderClass(data.getString(1), data.getString(2), data.getString(3), data.getString(4)));
                JSONObject contact = new JSONObject();
                try {
                    contact.put("Myid",data.getString(1));
                    contact.put("name",data.getString(2));
                    contact.put("quantity",data.getString(3));
                    contact.put("price",data.getString(4));
                    SaveArray.put(contact);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        OrderAdapter orderAdapter = new OrderAdapter(getApplicationContext(), list);
        recycle_View.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cart_btn:
                String useremail = email.getText().toString().trim();
                String usermbile = mobile.getText().toString().trim();
                String useradd = add.getText().toString().trim();

                if(useremail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Email Address",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(usermbile.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Mobile Number",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(useradd.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter full address",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    configurepaypal();
                    getPayment();
                }

                CallApi callApi = new CallApi();
                HashMap<String, String> map = new HashMap<>();
                map.put("name", user.getFnaem()+" "+user.getLastnme());
                map.put("id", user.getUserId());
                map.put("Json", String.valueOf(SaveArray));
                map.put("email ", useremail);
                map.put("mobile  ", usermbile);
                map.put("address ", useradd);
                callApi.getSubmit(OrderPage.this, map);
                break;
        }
    }

    private void configurepaypal() {
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
    }

    private void getPayment() {


      //  paymentAmount = editTextAmount.getText().toString();
        String paymentAmount = "10";

        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)), "USD",  getString(R.string.app_name),
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }
    

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                            Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                       // startActivity(new Intent(this, ConfirmationActivity.class)
                           //     .putExtra("PaymentDetails", paymentDetails)
                               // .putExtra("PaymentAmount", paymentAmount));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }


    public void responseItem(Responce_Model body) {
        try {
            customToast(body.getMessage());
            //JSONObject jsonObject = new JSONObject(gson.toJson(body));
            if (body.getStatus().equals("1")){
                mydb.delete_all();
                startActivity(new Intent(this, MainActivity.class));
            }
        }catch (Exception e){
            customToast(e.getMessage());
        }
    }

    public void customToast(String message) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
        ArrayList<OrderClass> Mydata;
        Context context;
        public OrderAdapter(Context context, ArrayList<OrderClass> data) {
            this.context = context;
            this.Mydata = data;
        }

        @Override
        public OrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_layout, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            final OrderClass data_item = Mydata.get(position);

            holder.idTextView.setText("Id : " + data_item.getItemId());
            holder.nameTextView.setText("Name : " + data_item.getItemName());
            holder.quantityTextView.setText("Quantity : " +data_item.getItemquantity());
            holder.priceTextView.setText("Price : "+data_item.getItemPrice() + "Rs");

            holder.cart_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int isinserted = mydb.delete_one(data_item.getItemId());
                    getdate();
                }
            });

        }

        @Override
        public int getItemCount() {
            return Mydata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView idTextView, nameTextView, quantityTextView, priceTextView;
            ImageView cart_delete;
            RelativeLayout relative;
            public MyViewHolder(View itemView) {
                super(itemView);
                idTextView =  (TextView) itemView.findViewById(R.id.id_item);
                nameTextView =  (TextView) itemView.findViewById(R.id.cart_prtitle);
                quantityTextView =  (TextView) itemView.findViewById(R.id.cart_prcount);
                priceTextView =  (TextView) itemView.findViewById(R.id.cart_prprice);
                cart_delete =  itemView.findViewById(R.id.deletecard);
            }
        }
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}