package android.BeeFood.master.view.onboarding_sign_up_sign_in;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.LoaiDao;
import android.BeeFood.master.controller.InternetCheckService;
import android.BeeFood.master.model.loaiFood;
import android.BeeFood.master.view.accountSetup.Screen_Profile;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "push notification";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button btnLoginFb, btnEmail, btnEmailTaoTK, btnLoginGoogle;
    EditText edtEmail;
    EditText edtPassWord;
    TextView textViewSignUp;
    ImageView img_login_exit;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    FirebaseAuth mAuth;
    GoogleSignInAccount account;
    CallbackManager callbackManager;
    SharedPreferences sharedPreferences;
    BroadcastReceiver  broadcastReceiver = new InternetCheckService(getApplication(),"MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PhanQuyen();
        checkInternet();
        AnhXa();
        setSelect_edt();
        SetupsLogin();
        EventClick();
        createChannelNotification();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        LayThongTinFb();

        SharedPreferences sharedPref = this.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        String pass = sharedPref.getString("passWord", "");

        edtEmail.setText(email);
        edtPassWord.setText(pass);
    }

    private void checkInternet() {
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private void LayThongTinFb() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            Set<String> a = AccessToken.getCurrentAccessToken().getPermissions();
                            Log.i("SONDTPH", "KẾT QUẢ:" + a + object.getString("name"));
                            Log.i("SONDTPH", "email:" + object.getString("email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void SetupsLogin() {
        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();
    }

    private void EventClick() {
        img_login_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack_app();
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gsc.signOut();
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = gsc.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });
        btnLoginFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
                LoginManager.getInstance().logInWithReadPermissions(
                        MainActivity.this,
                        Arrays.asList("email"));
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString(), password = edtPassWord.getText().toString();
                if (email.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
                    edtEmail.requestFocus();
                } else if (password.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Password", Toast.LENGTH_SHORT).show();
                    edtPassWord.requestFocus();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", email);
                                editor.putString("passWord", password);
                                editor.commit();
                                Toast.makeText(MainActivity.this, "Login Thành công", Toast.LENGTH_SHORT).show();
                                db.collection("users")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    boolean check = true;
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                                            check = false;
                                                        }
                                                    }
                                                    if (check) {
                                                        startActivity(new Intent(MainActivity.this, Screen_Profile.class));
                                                    }
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(MainActivity.this, "Login thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
//        account = GoogleSignIn.getLastSignedInAccount(this);
        LoaiDao loaiDao = new LoaiDao();
        loaiFood loaiFood = new loaiFood("https://firebasestorage.googleapis.com/v0/b/shoppyfood-c2281.appspot.com/o/profile.png?alt=media&token=8cdba2f9-1af0-4447-a581-ac236c6125f0", "Name");

    }

    private void AnhXa() {
        img_login_exit = findViewById(R.id.login_exit);
        btnLoginFb = findViewById(R.id.btnFacebook);
        btnLoginGoogle = findViewById(R.id.btnGoogle);
        btnEmail = findViewById(R.id.btnLoginEmail);
        edtEmail = findViewById(R.id.edt_Login_Email);
        edtPassWord = findViewById(R.id.inputPassword);
        textViewSignUp = findViewById(R.id.textViewSignUp);
        sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                account = GoogleSignIn.getLastSignedInAccount(this);
                if (account != null) {
                    db.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        boolean check = true;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            if (account.getEmail().equalsIgnoreCase(document.getData().get("email").toString())) {
                                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                                check = false;
                                            }
                                        }
                                        if (check) {
                                            Toast.makeText(MainActivity.this, "Chua co User", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                }
            } catch (ApiException e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean PhanQuyen() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    private void createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Push Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (!account.getEmail().equalsIgnoreCase("")) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", account.getEmail());
                editor.commit();
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Chưa Đăng Nhập", Toast.LENGTH_SHORT).show();
        }
    }

    //thoát app
    public void onBack_app() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Do you want to Exit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        onBack_app();
    }
    //

    //set select edt
    public void setSelect_edt() {
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    edtEmail.setBackgroundResource(R.drawable.bg_edt_login_select);
                } else {
                    edtEmail.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edtPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    edtPassWord.setBackgroundResource(R.drawable.bg_edt_login_select);
                } else {
                    edtPassWord.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });
    }
}