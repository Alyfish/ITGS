package com.exam.foodit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.foodit.Helper.CallApi;
import com.exam.foodit.Helper.CommonSharedPreference;
import com.exam.foodit.Helper.Utils;
import com.exam.foodit.Model.Category;
import com.exam.foodit.Model.Responce_Model;
import com.exam.foodit.Model.User;
import com.exam.foodit.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    Button google, facebook;
    String firebasetoken = "";
    GoogleSignInClient mGoogleSignInClient;
    public static FirebaseAuth mAuth;
    int RC_SIGN_IN = 9001;
    private Gson gson = new Gson();
    ProgressDialog mProgressDialog;
    CallbackManager mCallbackManager;
    ArrayList<Category> arrpast = new ArrayList<>();
    private CommonSharedPreference commonSharedPreference;
    Button button;
    private EditText edtemail,edtpass;
    private String email,pass,sessionmobile;
    private ProgressDialog progressDialog;
    TextView register_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        printKeyHash();
        getFirebaseToken();

        commonSharedPreference = new CommonSharedPreference();
        if(commonSharedPreference.getUserLoginSharedPref(this) != null){
            Intent intent = new Intent(LoginPage.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            CallApi callApi = new CallApi();
            HashMap<String, String> map = new HashMap<>();
            callApi.getCategoryList(LoginPage.this);
        }

        init();
    }

    public void init(){
        google = findViewById(R.id.google);
        facebook = findViewById(R.id.facebook);
        edtemail= findViewById(R.id.email);
        edtpass= findViewById(R.id.password);
        button=findViewById(R.id.login_button);
        register_now=findViewById(R.id.register_now);
        getCategory();

        google.setOnClickListener(this);
        facebook.setOnClickListener(this);
        button.setOnClickListener(this);
        register_now.setOnClickListener(this);
    }

    private void getCategory(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.google:
                if (Utils.isNetworkAvailable(LoginPage.this))
                    googleLogin();
                else
                    setSnackBar("No internet connection!!", "Retry");
                break;

            case R.id.facebook:
                if (Utils.isNetworkAvailable(LoginPage.this)) {
                    LoginManager.getInstance().logOut();
                    FacebookLogin();
                } else
                    setSnackBar("No internet connection!!", "Retry");
                break;

            case R.id.login_button:
                login();
                break;

            case R.id.register_now:

                Intent intent = new Intent(LoginPage.this, SignIn.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email=edtemail.getText().toString();
                pass=edtpass.getText().toString();

                if (email.isEmpty()){

                    Toast.makeText(LoginPage.this, "Please Input Email", Toast.LENGTH_SHORT).show();
                    edtemail.requestFocus();

                }else if (pass.isEmpty()){

                    Toast.makeText(LoginPage.this, "Please Input Password", Toast.LENGTH_SHORT).show();
                    edtpass.requestFocus();
                }else {


                if (validateUsername(email) && validatePassword(pass)) {

                    final KProgressHUD progressDialog = KProgressHUD.create(LoginPage.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setLabel("Please wait")
                            .setCancellable(false)
                            .setAnimationSpeed(2)
                            .setDimAmount(0.5f)
                            .show();

                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //checking if success
                                    if (task.isSuccessful()) {

                                        progressDialog.dismiss();
                                        User user = new User();
                                        user.setEmail(email);
                                        user.setPasswrod(pass);
                                        commonSharedPreference.setUserLoginSharedPref(LoginPage.this, user);
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                    } else {
                                        //display some message here
                                        progressDialog.dismiss();
                                        Toast.makeText(LoginPage.this, "Registration Error", Toast.LENGTH_LONG).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                }
                }

            }
        });

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

    public void googleLogin(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(LoginPage.this);
        if (account != null) {
            String Tokenid=account.getIdToken();
            String id=account.getId();
            String fname=""+account.getGivenName();
            String lname=""+account.getFamilyName();

            String pic_url;
            if(account.getPhotoUrl()!=null) {
                pic_url = account.getPhotoUrl().toString();
            }else {
                pic_url="null";
            }

            if(fname.equals("") || fname.equals("null"))
                fname=getResources().getString(R.string.app_name);

            if(lname.equals("") || lname.equals("null"))
                lname="User";

            gotologin(id, fname, lname, pic_url);
        }
        else {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
            showProgressDialog();
        }
    }

    public void FacebookLogin(){
        LoginManager.getInstance().logInWithReadPermissions(LoginPage.this, Arrays.asList("public_profile","email"));
        // initialze the facebook sdk and request to facebook for login
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>()  {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Log.d("resp_token",loginResult.getAccessToken()+"");
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginPage.this, "Login Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("resp",""+error.toString());
                Toast.makeText(LoginPage.this, "Login Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }

        });
    }




    private void handleFacebookAccessToken(final AccessToken token) {
        // if user is login then this method will call and
        // facebook will return us a token which will user for get the info of user
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Log.d("resp_token",token.getToken()+"");
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showProgressDialog();
                            final String id = Profile.getCurrentProfile().getId();
                            GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject user, GraphResponse graphResponse) {

                                    Log.d("resp",user.toString());
                                    String fname=""+user.optString("first_name");
                                    String lname=""+user.optString("last_name");

                                    if(fname.equals("") || fname.equals("null"))
                                        fname=getResources().getString(R.string.app_name);

                                    if(lname.equals("") || lname.equals("null"))
                                        lname="";

                                    String imge = "https://graph.facebook.com/"+id+"/picture?width=500&width=500";

                                    gotologin(id,fname,lname, imge);

                                }
                            });

                            // here is the request to facebook sdk for which type of info we have required
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "last_name,first_name,email");
                            request.setParameters(parameters);
                            request.executeAsync();
                        } else {
                            Toast.makeText(LoginPage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        else if(mCallbackManager!=null)
            mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                String token=account.getIdToken();
                String id=account.getId();
                String fname=""+account.getGivenName();
                String lname=""+account.getFamilyName();

                String pic_url;
                if(account.getPhotoUrl()!=null) {
                    pic_url = account.getPhotoUrl().toString();
                }else {
                    pic_url="null";
                }


                if(fname.equals("") || fname.equals("null"))
                    fname=getResources().getString(R.string.app_name);

                if(lname.equals("") || lname.equals("null"))
                    lname="";


                gotologin(id, fname, lname, pic_url);
            }
        } catch (ApiException e) {
            Log.w("Error message", "signInResult:failed code=" + e.getStatusCode());
        }

    }

    public void gotologin(String id, String fname, String lname, String picirl){
        User user = new User();
        user.setUserId(id);
        user.setFnaem(fname);
        user.setLastnme(lname);
        user.setImage(lname);

        commonSharedPreference.setUserLoginSharedPref(LoginPage.this, user);
        startActivity(new Intent(this, MainActivity.class));
    }


    public void setSnackBar(String message, String action) {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(action, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(LoginPage.this)) {
                    snackbar.dismiss();
                } else {
                    snackbar.show();
                }
            }
        });
        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setMaxLines(5);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public void getFirebaseToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("Token",MODE_PRIVATE);
        firebasetoken = sharedPreferences.getString("token","");
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginPage.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newtoken = instanceIdResult.getToken();
                SharedPreferences.Editor editor = getSharedPreferences("Token",MODE_PRIVATE).edit();
                if(firebasetoken!= null){
                    editor.putString("token",newtoken);
                    editor.apply();
                }
            }
        });
    }

    public void printKeyHash()  {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName() , PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("keyhash" , Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void customToast(String message) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    public void responseCategory(Responce_Model body) {
        try {
            customToast(body.getMessage());
            JSONObject jsonObject = new JSONObject(gson.toJson(body));
            if (body.getStatus().equals("1")){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Type type1=new TypeToken<ArrayList<Category>>(){}.getType();
                arrpast = gson.fromJson(jsonArray.toString(), type1);
                commonSharedPreference.setCategoryPref(this, arrpast);
            } else {
                //  callLogout();
            }
        }catch (Exception e){
            customToast(e.getMessage());
        }
    }
}