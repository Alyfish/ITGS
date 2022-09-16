package com.exam.foodit.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.exam.foodit.Activity.LoginPage;
import com.exam.foodit.Fragment.MainFragment;
import com.exam.foodit.Fragment.Submit_Order;
import com.exam.foodit.Helper.CommonSharedPreference;
import com.exam.foodit.Model.Category;
import com.exam.foodit.Model.User;
import com.exam.foodit.R;
import com.exam.foodit.Sqlite.DatabaseHelper;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    Button logout,cancel;
    DrawerLayout drawer;
    DatabaseHelper mydb;
    Fragment argumentFragment;
    RelativeLayout logout_rty, order_details_rty, submit_ryt;
    RecyclerView recycle_View;
    String flitervalue = "";
    ImageView profile_image;
    TextView username;
    User user;
    public static ArrayList<Category> addItem = new ArrayList<>();
    private CommonSharedPreference commonSharedPreference = new CommonSharedPreference();
    private static FragmentManager fragmentManager;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        fragmentManager = getSupportFragmentManager();
        mAuth = FirebaseAuth.getInstance();

        init();
        GotoMain("0");
    }


    public void init(){
        user = commonSharedPreference.getUserLoginSharedPref(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profile_image = findViewById(R.id.profileImage);
        username  = findViewById(R.id.tvProfileName);


        username.setText(user.getFnaem() +" "+ user.getLastnme());
        Picasso.with(this)
                .load(user.getImage())
                .error(R.drawable.personpng )
                .placeholder(R.drawable.personpng)
                .into(profile_image);

        logout_rty = findViewById(R.id.logout_rty);
        order_details_rty = findViewById(R.id.order_details_rty);
        submit_ryt = findViewById(R.id.submit_ryt);

        if (commonSharedPreference.getUserLoginSharedPref(this) != null) {
            addItem = commonSharedPreference.getCategoryPref(MainActivity.this);
        }

        recycle_View = findViewById(R.id.recycle_View);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycle_View.setLayoutManager(linearLayoutManager);

        Category_Adapter category_adapter = new Category_Adapter(getApplicationContext(), addItem);
        recycle_View.setAdapter(category_adapter);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        logout_rty.setOnClickListener(this);
        submit_ryt.setOnClickListener(this);
        order_details_rty.setOnClickListener(this);
    }

    public void GotoMain(String id){
        argumentFragment = new MainFragment();
        FragmentTransaction f = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("ID", id);
        argumentFragment.setArguments(bundle);
        f.setCustomAnimations(R.anim.enter, R.anim.exit);
        f.replace(R.id.Fragment_container, argumentFragment);
        f.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.order_details_drawer ){
           /* Cursor check ;
            check = mydb.Get_OrderDetails() ;
            if(check!=null && check.getCount()>0) {
                Intent intent = new Intent(getApplicationContext(), OrderPage.class);
                startActivity(intent);
            } else  {
                Toast.makeText(getApplicationContext(),"No details found because you didn't order something...",Toast.LENGTH_SHORT).show();}*/
        }
        else if(id == R.id.submit_order ){
            //Cursor check ;
           /* check = mydb.Get_OrderDetails() ;

            if(check!=null && check.getCount()>0) {
                Submit_Order fragment = new Submit_Order();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Fragment_container, fragment);
                fragmentTransaction.commit();
            }*/
            //else  {Toast.makeText(getApplicationContext(),"Sorry, You don't order anything...",Toast.LENGTH_SHORT).show();}
        }

        else if(id == R.id.log_out ){
            openDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openDialog() {
        final Dialog builder = new Dialog(this); // Context, this, etc.
        builder.setContentView(R.layout.dialogdesign);
        builder.setTitle(R.string.dialog_popup);
        builder.show();
        logout = (Button) builder.findViewById(R.id.dialog_ok);
        cancel = (Button) builder.findViewById(R.id.dialog_cancel);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.delete_all();
                commonSharedPreference.setUserLoginSharedPref(MainActivity.this, null);
                Toast.makeText(getApplicationContext(),"Hope you like our service, Have a good day !!!",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"So you don't want to, Logout !!!",Toast.LENGTH_SHORT).show();
                builder.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logout_rty://submit_ryt
                openDialog();
                break;


            case R.id.order_details_rty:
                Cursor check ;
                check = mydb.Get_OrderDetails() ;
                if(check!=null && check.getCount()>0) {
                    drawer.closeDrawer(GravityCompat.START);
                    Intent intent = new Intent(getApplicationContext(), OrderPage.class);
                    startActivity(intent);
                } else  {
                    Toast.makeText(getApplicationContext(),"No details found because you didn't order something...",Toast.LENGTH_SHORT).show();}
                break;

            case R.id.submit_ryt:
                Cursor check2;
                check2 = mydb.Get_OrderDetails() ;
                if(check2!=null && check2.getCount()>0) {
                    drawer.closeDrawer(GravityCompat.START);
                    argumentFragment = new Submit_Order();
                    FragmentTransaction f = fragmentManager.beginTransaction();
                    f.setCustomAnimations(R.anim.enter, R.anim.exit);
                    f.replace(R.id.Fragment_container, argumentFragment);
                    f.commit();
                } else {
                    Toast.makeText(getApplicationContext(),"Sorry, You don't order anything...",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.MyViewHolder> {
        ArrayList<Category> Mydata;
        Context context;
        public Category_Adapter(Context context, ArrayList<Category> data) {
            this.context = context;
            this.Mydata = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adaptercategory, parent, false);

            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            final Category category = Mydata.get(position);
            holder.name.setText(category.getCname());

            holder.relative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GotoMain(category.getCname());
                    drawer.closeDrawer(GravityCompat.START);
                }
            });
        }

        @Override
        public int getItemCount() {
            return Mydata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            RelativeLayout relative;
            public MyViewHolder(View itemView) {
                super(itemView);
                name =  (TextView) itemView.findViewById(R.id.name);
                relative = (RelativeLayout) itemView.findViewById(R.id.relative);
            }
        }
    }
}