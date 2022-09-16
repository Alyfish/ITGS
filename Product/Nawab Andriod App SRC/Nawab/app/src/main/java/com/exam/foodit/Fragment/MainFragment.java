package com.exam.foodit.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.exam.foodit.Activity.LoginPage;
import com.exam.foodit.Activity.MainActivity;
import com.exam.foodit.Activity.ProductListActivity;
import com.exam.foodit.Helper.CallApi;
import com.exam.foodit.Helper.Constant;
import com.exam.foodit.Model.Category;
import com.exam.foodit.Model.Item;
import com.exam.foodit.Model.Responce_Model;
import com.exam.foodit.R;
import com.exam.foodit.Sqlite.DatabaseHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class MainFragment extends Fragment {

    RecyclerView recycle_View;
    CallApi callApi = new CallApi();
    private Gson gson = new Gson();
    ArrayList<Item> AppItem = new ArrayList<>();
    Item_Adapter item_adapter;
    String strtext;
    DatabaseHelper mydb;
    View view;
    SliderLayout sliderShow;
    Context context;
    String TAG = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        this.mydb = new DatabaseHelper(getActivity());
        sliderShow =view.findViewById(R.id.slider);

        inflateImageSlider();
        init();
    }

    private void inflateImageSlider() {


        //populating Image slider
        ArrayList<String> sliderImages = new ArrayList<>();
        sliderImages.add("https://media.istockphoto.com/photos/assorted-indian-recipes-food-various-picture-id922783734?k=6&m=922783734&s=612x612&w=0&h=hljWwPRzq8Cc_PHbx693FPnQ_39yNqD4BUjtMbgASlM=");
        sliderImages.add("https://media.istockphoto.com/photos/assorted-indian-recipes-food-various-picture-id922783734?k=6&m=922783734&s=612x612&w=0&h=hljWwPRzq8Cc_PHbx693FPnQ_39yNqD4BUjtMbgASlM=");
//        sliderImages.add(String.valueOf(R.drawable.background));
//        sliderImages.add(String.valueOf(R.drawable.sspp));

        for (String s : sliderImages) {
            DefaultSliderView sliderView = new DefaultSliderView(getActivity());
            sliderView.image(s);
            sliderShow.addSlider(sliderView);
        }

     //   sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
    }

    public void init(){
        strtext = getArguments().getString("ID");


        Log.e(TAG, "init: " + strtext );
        recycle_View = view.findViewById(R.id.recycle_View);
        recycle_View.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycle_View.setLayoutManager(linearLayoutManager);
        CallApi callApi = new CallApi();
        callApi.getitemList(MainFragment.this);
    }

    public void customToast(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
    }

    public void responseItem(Responce_Model body) {
        Log.e("strtext",strtext);

        try {
            customToast(body.getMessage());
            JSONObject jsonObject = new JSONObject(gson.toJson(body));
            if (body.getStatus().equals("1")){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if(strtext.equals("0")){
                    Type type1=new TypeToken<ArrayList<Item>>(){}.getType();
                    AppItem = gson.fromJson(jsonArray.toString(), type1);
                    item_adapter = new Item_Adapter(getActivity(), AppItem);
                    recycle_View.setAdapter(item_adapter);
                    recycle_View.setNestedScrollingEnabled(false);
                    recycle_View.setFocusable(false);
                    item_adapter.notifyDataSetChanged();
                }else {
                    for(int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        if(jsonObject1.getString("catid").equalsIgnoreCase(strtext)){
                            Item step = new Item();
                            step.setCid(jsonObject1.getString("id"));
                            step.setName(jsonObject1.getString("name"));
                            step.setPrice(jsonObject1.getString("price"));
                            step.setUrl(jsonObject1.getString("url"));
                            step.setCatid(jsonObject1.getString("catid"));
                            step.setImage(jsonObject1.getString("image"));
                            step.setDesc(jsonObject1.getString("desc"));

                            AppItem.add(step);
                            item_adapter = new Item_Adapter(getActivity(), AppItem);
                            recycle_View.setAdapter(item_adapter);
                            recycle_View.setNestedScrollingEnabled(false);
                            recycle_View.setFocusable(false);
                            item_adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }catch (Exception e){
            customToast(e.getMessage());
        }
    }




    public class Item_Adapter extends RecyclerView.Adapter<Item_Adapter.MyViewHolder>  implements Filterable {
        ArrayList<Item> Mydata;
        ArrayList<Item> MyItem;
        Context context;
        int num = 0;
        int number =0;

        public Item_Adapter(Context context, ArrayList<Item> data) {
            this.context = context;
            this.Mydata = data;
            this.MyItem = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_item, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final Item item = Mydata.get(position);
            holder.item_name.setText(item.getName());
            holder.item_price.setText("Rs. " + item.getPrice());

            Picasso.with(context)
                    .load(Constant.ImageUrl +  item.getImage())
                    .error(R.drawable.ic_no_photos )
                    .placeholder(R.drawable.ic_no_photos)
                    .into(holder.item_image);


           holder.incrementQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number>=0){
                        number = number + 1;
                       holder.quantityProductPage.setText(String.valueOf(number));
                    }
                }
            });

          holder.decrementQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number>0){
                        number = number - 1;
                      holder.quantityProductPage.setText(String.valueOf(number));
                    }
                }
            });

            holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt(holder.quantityProductPage.getText().toString().trim()) == 0){

                        Toast.makeText(getContext(), "Select Quantity", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean isinserted = mydb.Add_to_Cart(item.getid(),item.getName(),holder.quantityProductPage.getText().toString().trim(),String.valueOf(Integer.parseInt(item.getPrice())*Integer.parseInt(holder.quantityProductPage.getText().toString().trim())));
                        if (isinserted) {
                            holder.quantityProductPage.setText("0");
                            Toast.makeText(getContext(), "Order Added Successfully !", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Please, Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


            holder.constraint_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ProductListActivity.class);
                    intent.putExtra("arraylist",  gson.toJson(item));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return Mydata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView item_name, item_price, quantity, quantityProductPage;
            ImageView item_image;
            ConstraintLayout constraint_layout;
            Button incrementQuantity, decrementQuantity, add_to_cart;

            public MyViewHolder(View itemView) {
                super(itemView);
                item_image = (ImageView)  itemView.findViewById(R.id.getImage);
                item_name =  (TextView) itemView.findViewById(R.id.name);
                item_price = itemView.findViewById(R.id.price);
                constraint_layout =  itemView.findViewById(R.id.constraint_layout);
                incrementQuantity =itemView. findViewById(R.id.incrementQuantity);
                decrementQuantity = itemView.findViewById(R.id.decrementQuantity);
                quantityProductPage = itemView.findViewById(R.id.quantityProductPage);
                add_to_cart = itemView.findViewById(R.id.add_to);

            }
        }


        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        Mydata = MyItem;
                    } else {
                        ArrayList<Item> filteredList = new ArrayList<>();
                        for (Item row : MyItem) {
                            if (row.getCatid().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                        Mydata = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = Mydata;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    Mydata = (ArrayList<Item>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }
    }

}