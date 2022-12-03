package android.BeeFood.master.view.profile.profile_update;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.DataFirestore;
import android.BeeFood.master.model.User;
import android.BeeFood.master.model.UserChiTiet;
import android.BeeFood.master.view.accountSetup.Screen_Pin_Code;
import android.BeeFood.master.view.accountSetup.Screen_Profile;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.BeeFood.master.view.onboarding_sign_up_sign_in.MainActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class Profile_Update_Activity extends AppCompatActivity {
    private ImageView img_back,img_accountsetup_profile;
    private ImageView img_profile_update_MyLaction;
    private EditText edt_profile_update_FullName,edt_profile_Update_NickName,edt_profile_update_DateOfBirth,edt_profile_update_Email,edt_profile_update_location;
    private Spinner spn_profile_spinner_gender;
    private Button btn_continue;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference reference = storage.getReference();

    Uri uri;
    String url_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        AnhXa();
        setSelect_edt();

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
                edt_profile_update_location.setText("địa chỉ");
            }
        });
        evenCLick();
        upLoadURL();

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
                                                                  User user=new User(email, edt_profile_Update_NickName.getText().toString(), "admin",userChiTiet);
                                                                  DataFirestore dataFirestore=new DataFirestore();
                                                                  boolean check =dataFirestore.updateUser(user,getApplication(),
                                                                          dataFirestore.getEmail(getApplication()),
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
                                        edt_profile_update_FullName.setText(document.getData().get("fullname").toString());
                                        edt_profile_Update_NickName.setText(document.getData().get("nickname").toString());
                                        edt_profile_update_DateOfBirth.setText(document.getData().get("dateofbirth").toString());
                                        edt_profile_update_Email.setText(document.getData().get("email").toString());
                                        if(document.getData().get("gender").equals("Nam")){
                                            spn_profile_spinner_gender.setSelection(2);
                                        } else {
                                            spn_profile_spinner_gender.setSelection(1);
                                        }
                                        Glide.with(getApplication()).load(url_profile).into(img_accountsetup_profile);
                                    }
                                } catch (Exception e) {
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
}