package com.exam.foodit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.foodit.Helper.CommonSharedPreference;
import com.exam.foodit.Model.User;
import com.exam.foodit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.kaopiz.kprogresshud.KProgressHUD;

import static com.exam.foodit.Activity.LoginPage.mAuth;

public class SignIn extends AppCompatActivity {

    TextView login;
    Button button;
    EditText edtemail,edtpass;
    private String email,pass,sessionmobile;
    private CommonSharedPreference commonSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        commonSharedPreference = new CommonSharedPreference();
        login = findViewById(R.id.login);
        edtemail= findViewById(R.id.email);
        edtpass= findViewById(R.id.password);
        button=findViewById(R.id.login_button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        email=edtemail.getText().toString();
        pass=edtpass.getText().toString();

        if (email.isEmpty()){

            Toast.makeText(SignIn.this, "Please Input Email", Toast.LENGTH_SHORT).show();
            edtemail.requestFocus();

        }else if (pass.isEmpty()){

            Toast.makeText(SignIn.this, "Please Input Password", Toast.LENGTH_SHORT).show();
            edtpass.requestFocus();
        }else {


            if (validateUsername(email) && validatePassword(pass)) {

                final KProgressHUD progressDialog = KProgressHUD.create(SignIn.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();

                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //checking if success
                                if (task.isSuccessful()) {

                                    progressDialog.dismiss();
                                    User user = new User();
                                    user.setEmail(email);
                                    user.setPasswrod(pass);
                                    commonSharedPreference.setUserLoginSharedPref(SignIn.this, user);
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                } else {
                                    //display some message here
                                    progressDialog.dismiss();
                                    Toast.makeText(SignIn.this, "Registration Error", Toast.LENGTH_LONG).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
            }
        }

    }

    private boolean validatePassword(String pass) {


        if (pass.length() < 4 || pass.length() > 20) {
            edtpass.setError("Password Must consist of 4 to 20 characters");
            return false;
        }
        return true;
    }

    private boolean validateUsername(String email) {

        if (email.length() < 4 || email.length() > 30) {
            edtemail.setError("Email Must consist of 4 to 30 characters");
            return false;
        } else if (!email.matches("^[A-za-z0-9.@]+")) {
            edtemail.setError("Only . and @ characters allowed");
            return false;
        } else if (!email.contains("@") || !email.contains(".")) {
            edtemail.setError("Email must contain @ and .");
            return false;
        }
        return true;
    }
}