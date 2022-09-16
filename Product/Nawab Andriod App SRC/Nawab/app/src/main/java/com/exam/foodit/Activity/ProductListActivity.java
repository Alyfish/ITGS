package com.exam.foodit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.foodit.Helper.Constant;
import com.exam.foodit.Model.Item;
import com.exam.foodit.R;
import com.exam.foodit.Sqlite.DatabaseHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ProductListActivity extends AppCompatActivity {

    ImageView productimage;
    TextView productname, productprice , productdesc, quantityProductPage ;
    Item item = new Item();
    private Gson gson = new Gson();
    Button incrementQuantity, decrementQuantity;
    int number =1;
    TextView add_to_cart;
    DatabaseHelper mydb;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        if (getIntent().hasExtra("arraylist")) {

            String temp = getIntent().getStringExtra("arraylist");
            Type type = new TypeToken<Item>(){}.getType();
            item = gson.fromJson(temp, type);
        }
        this.mydb = new DatabaseHelper(ProductListActivity.this);

        productimage = findViewById(R.id.productimage);
        productname = findViewById(R.id.productname);
        productprice = findViewById(R.id.productprice);
        productdesc = findViewById(R.id.productdesc);
        incrementQuantity = findViewById(R.id.incrementQuantity);
        decrementQuantity = findViewById(R.id.decrementQuantity);
        quantityProductPage = findViewById(R.id.quantityProductPage);
        add_to_cart = findViewById(R.id.add_to_cart);
        back = findViewById(R.id.back);

        Picasso.with(ProductListActivity.this)
                .load(Constant.ImageUrl + item.getImage())
                .placeholder(R.drawable.ic_no_photos)
                .error(R.drawable.ic_no_photos)
                .into(productimage);

        productname.setText(item.getName());
        productprice.setText("KSH. " + item.getPrice());
        productdesc.setText(item.getDesc());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        incrementQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number>=1){
                    number = number + 1;
                    quantityProductPage.setText(String.valueOf(number));
                }
            }
        });

        decrementQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number>1){
                    number = number - 1;
                    quantityProductPage.setText(String.valueOf(number));
                }
            }
        });

                    add_to_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       // Log.d(TAG, "onClick: " + item.getid() + item.getName() + String.valueOf(number) + item.getPrice());
//
                        boolean isinserted = mydb.Add_to_Cart(item.getid(),item.getName(), String.valueOf(number),String.valueOf(Integer.parseInt(item.getPrice()) * number));
                            if (isinserted) {
                                Toast.makeText(ProductListActivity.this, "Order Added Successfully !", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(ProductListActivity.this, MainActivity.class);
//                                startActivity(intent);
                            } else {
                                Toast.makeText(ProductListActivity.this, "Please, Try again", Toast.LENGTH_SHORT).show();
                            }

                    }
                });



    }
}