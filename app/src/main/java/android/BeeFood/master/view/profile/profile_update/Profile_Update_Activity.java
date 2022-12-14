package android.BeeFood.master.view.profile.profile_update;

import static java.lang.Thread.sleep;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.UserDao;
import android.BeeFood.master.model.User;
import android.BeeFood.master.model.UserChiTiet;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Profile_Update_Activity extends AppCompatActivity implements LocationListener{
    private ImageView img_back,img_accountsetup_profile;
    private ImageView img_profile_update_MyLaction;
    private EditText profile_update_Phone,edt_profile_update_FullName,edt_profile_Update_NickName,edt_profile_update_DateOfBirth,edt_profile_update_Email,edt_profile_update_location;
    private Spinner spn_profile_spinner_gender;
    private Button btn_continue;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference reference = storage.getReference();
    LocationManager locationManager;
    double latitude = 0, longitude = 0;
    Uri uri;
    String url_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        AnhXa();
        setSelect_edt();
//        getLocation();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Gender");
        spinnerArray.add("Nữ");
        spinnerArray.add("Nam");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spn_profile_spinner_gender.setAdapter(adapter);


        //lấy địa chỉ người dùng 0.5
        img_profile_update_MyLaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
                Toast.makeText(Profile_Update_Activity.this, "Lấy vị trí GPS Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
        evenCLick();
        upLoadURL();

    }

    private void getLocation() {
        Toast.makeText(this, "Thông báo 1", Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        //goi ham update
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Toast.makeText(getApplication(), "Đang lấy vị trí", Toast.LENGTH_SHORT).show();
                    if (longitude == 0 && latitude == 0) {
                        try {
                            Toast.makeText(getApplication(), "lấy vị trí thành công", Toast.LENGTH_SHORT).show();
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });
    }

    private void evenCLick() {
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_profile_update_FullName.getText().toString().equals("")) {
                    Toast.makeText(Profile_Update_Activity.this, "Chưa Nhập Full Name", Toast.LENGTH_SHORT).show();
                } else if (edt_profile_Update_NickName.getText().toString().equals("")) {
                    Toast.makeText(Profile_Update_Activity.this, "Chưa Nhập Nick Name", Toast.LENGTH_SHORT).show();
                } else if (edt_profile_update_DateOfBirth.getText().toString().equals("")) {
                    Toast.makeText(Profile_Update_Activity.this, "Chưa Nhập Ngày Tháng Năm Sinh", Toast.LENGTH_SHORT).show();
                } else if (edt_profile_update_Email.getText().toString().equals("")) {
                    Toast.makeText(Profile_Update_Activity.this, "Chưa Nhập Email", Toast.LENGTH_SHORT).show();
                }else if (profile_update_Phone.getText().toString().equals("")) {
                    Toast.makeText(Profile_Update_Activity.this, "Chưa Nhập Số Điện Thoại", Toast.LENGTH_SHORT).show();
                } else if (spn_profile_spinner_gender.getSelectedItemPosition() == 0) {
                    Toast.makeText(Profile_Update_Activity.this, "Chưa Nhập Giới Tính", Toast.LENGTH_SHORT).show();
                } else {
                    if (uri != null) {
                        SharedPreferences sharedPref = getSharedPreferences("USER", MODE_PRIVATE);
                        String email = sharedPref.getString("email", "");
                        StorageReference demoRef = reference.child(email+"profile.png");
                        demoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplication(), "Uploaded", Toast.LENGTH_SHORT).show();
                                demoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri DownloadUri) {
                                        db.collection("users")
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                              if(email.equalsIgnoreCase(document.getData().get("email").toString())){
                                                                  url_profile = DownloadUri.toString();
                                                                  UserChiTiet userChiTiet=new UserChiTiet(email,
                                                                          edt_profile_update_FullName.getText().toString(),
                                                                          spn_profile_spinner_gender.getSelectedItem().toString(),
                                                                          edt_profile_update_DateOfBirth.getText().toString(),
                                                                          url_profile, edt_profile_update_location.getText().toString(),
                                                                          document.getData().get("pin").toString());
                                                                  User user=new User(email, edt_profile_Update_NickName.getText().toString(), "admin",userChiTiet,profile_update_Phone.getText().toString());
                                                                  UserDao userDao =new UserDao();
                                                                  boolean check = userDao.updateUser(user,getApplication(),
                                                                          userDao.getEmail(getApplication()),
                                                                          document.getData().get("pin").toString());
                                                              }
                                                            }
                                                        } else {
                                                            Toast.makeText(Profile_Update_Activity.this, "Error getting documents.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                });
                            }
                        });
                    } else {
                        Toast.makeText(Profile_Update_Activity.this, "Vui lòng Chọn Ảnh Profile", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void upLoadURL() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SharedPreferences sharedPref = getSharedPreferences("USER", MODE_PRIVATE);
                                String email = sharedPref.getString("email", "");
                                try {
                                    if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                        url_profile = document.getData().get("ImageUrl").toString();
                                        Glide.with(getApplication()).load(url_profile).into(img_accountsetup_profile);
                                        edt_profile_update_FullName.setText(document.getData().get("fullname").toString());
                                        edt_profile_Update_NickName.setText(document.getData().get("nickname").toString());
                                        edt_profile_update_DateOfBirth.setText(document.getData().get("dateofbirth").toString());
                                        edt_profile_update_Email.setText(document.getData().get("email").toString());
                                        profile_update_Phone.setText(document.getData().get("phone").toString());
                                        boolean check=document.getData().get("gender").equals("Nam");
                                        if(check){
                                            spn_profile_spinner_gender.setSelection(2);
                                        } else {
                                            spn_profile_spinner_gender.setSelection(1);
                                        }
//                                        Toast.makeText(Profile_Update_Activity.this, document.getData().get("address").toString()+"", Toast.LENGTH_SHORT).show();
//                                        edt_profile_update_location.setText(document.getData().get("address").toString());
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(Profile_Update_Activity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
        img_accountsetup_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 174);
            }
        });
    }
    public void AnhXa(){
        profile_update_Phone=findViewById(R.id.profile_update_Phone);
        img_accountsetup_profile=findViewById(R.id.img_accountsetup_profile);
        btn_continue=findViewById(R.id.profile_continue);
        img_back = findViewById(R.id.img_accountsetup_profile_previous);
        edt_profile_update_FullName = findViewById(R.id.profile_update_FullName);
        edt_profile_Update_NickName = findViewById(R.id.profile_Update_NickName);
        edt_profile_update_DateOfBirth = findViewById(R.id.profile_update_DateOfBirth);
        edt_profile_update_Email = findViewById(R.id.profile_update_Email);
        edt_profile_update_location = findViewById(R.id.profile_update_location);
        spn_profile_spinner_gender = findViewById(R.id.profile_spinner_gender);
        img_profile_update_MyLaction = findViewById(R.id.profile_update_MyLaction);
    }

    public void setSelect_edt(){
        edt_profile_update_FullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_update_FullName.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_update_FullName.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edt_profile_Update_NickName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_Update_NickName.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_Update_NickName.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edt_profile_update_DateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_update_DateOfBirth.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_update_DateOfBirth.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edt_profile_update_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_update_Email.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_update_Email.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edt_profile_update_location.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_update_location.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_update_location.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 174) {
            uri = data.getData();
            if (uri != null) {
                Glide.with(getApplication()).load(uri).into(img_accountsetup_profile);
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            edt_profile_update_location.setText(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}