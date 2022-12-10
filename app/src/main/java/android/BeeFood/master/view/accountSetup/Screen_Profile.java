package android.BeeFood.master.view.accountSetup;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.UserDao;
import android.BeeFood.master.model.User;
import android.BeeFood.master.model.UserChiTiet;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Screen_Profile extends AppCompatActivity {
    Button btn_continue;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference reference = storage.getReference();

    EditText profile_edt_fullname, profile_edt_nickname, profile_edt_date_of_birth, profile_edt_email,profile_update_Phone;
    TextInputLayout layout_profile_update_location;
    Spinner profile_spinner_gender;
    ImageView img_profile_update_MyLaction, img_accountsetup_profile_previous, img_accountsetup_profile;
    Uri uri;
    String url_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        AnhXa();
        AdapterProfile();
        evenCLick();
        upLoadURL();
    }

    private void evenCLick() {
        layout_profile_update_location.setVisibility(View.INVISIBLE);
        img_profile_update_MyLaction.setVisibility(View.INVISIBLE);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profile_edt_fullname.getText().toString().equals("")) {
                    Toast.makeText(Screen_Profile.this, "Chưa Nhập Full Name", Toast.LENGTH_SHORT).show();
                } else if (profile_edt_nickname.getText().toString().equals("")) {
                    Toast.makeText(Screen_Profile.this, "Chưa Nhập Nick Name", Toast.LENGTH_SHORT).show();
                } else if (profile_edt_date_of_birth.getText().toString().equals("")) {
                    Toast.makeText(Screen_Profile.this, "Chưa Nhập Ngày Tháng Năm Sinh", Toast.LENGTH_SHORT).show();
                } else if (profile_edt_email.getText().toString().equals("")) {
                    Toast.makeText(Screen_Profile.this, "Chưa Nhập Email", Toast.LENGTH_SHORT).show();
                } else if (profile_update_Phone.getText().toString().equals("")) {
                    Toast.makeText(Screen_Profile.this, "Chưa Nhập Số Điện Thoại", Toast.LENGTH_SHORT).show();
                }  else if (profile_spinner_gender.getSelectedItemPosition() == 0) {
                    Toast.makeText(Screen_Profile.this, "Chưa Nhập Giới Tính", Toast.LENGTH_SHORT).show();
                } else {
                    if (uri != null) {
                        SharedPreferences sharedPref = getSharedPreferences("USER", MODE_PRIVATE);
                        String email = sharedPref.getString("email", "");
                        StorageReference demoRef = reference.child(email+".profile.png");
                        demoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplication(), "Uploaded", Toast.LENGTH_SHORT).show();
                                demoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri DownloadUri) {
                                        url_profile = DownloadUri.toString();
                                        UserChiTiet userChiTiet=new UserChiTiet(email,
                                                profile_edt_fullname.getText().toString(),
                                                profile_spinner_gender.getSelectedItem().toString(),
                                                profile_edt_date_of_birth.getText().toString(),
                                                url_profile, "", null);
                                        User user=new User(email, profile_edt_nickname.getText().toString(), "admin",userChiTiet,profile_update_Phone.getText().toString());
                                        UserDao userDao =new UserDao();
                                        boolean check = userDao.AddDataUser(user,Screen_Profile.this);
                                        Toast.makeText(Screen_Profile.this, check+"", Toast.LENGTH_SHORT).show();
                                        if(check){
                                            startActivity(new Intent(Screen_Profile.this,Screen_Pin_Code.class));
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            }
        });
        img_accountsetup_profile_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void AdapterProfile() {
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Gender");
        spinnerArray.add("Nữ");
        spinnerArray.add("Nam");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profile_spinner_gender.setAdapter(adapter);
    }

    public void AnhXa() {
        profile_update_Phone=findViewById(R.id.profile_update_Phone);
        profile_edt_fullname = findViewById(R.id.profile_update_FullName);
        profile_edt_nickname = findViewById(R.id.profile_Update_NickName);
        profile_edt_date_of_birth = findViewById(R.id.profile_update_DateOfBirth);
        profile_edt_email = findViewById(R.id.profile_update_Email);
        profile_spinner_gender = findViewById(R.id.profile_spinner_gender);
        img_accountsetup_profile_previous = findViewById(R.id.img_accountsetup_profile_previous);
        img_profile_update_MyLaction = findViewById(R.id.profile_update_MyLaction);
        layout_profile_update_location = findViewById(R.id.layout_profile_update_location);
        img_accountsetup_profile = findViewById(R.id.img_accountsetup_profile);
        btn_continue = findViewById(R.id.profile_continue);
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
                                        profile_edt_fullname.setText(document.getData().get("fullname").toString());
                                        profile_edt_nickname.setText(document.getData().get("nickname").toString());
                                        profile_edt_date_of_birth.setText(document.getData().get("dateofbirth").toString());
                                        profile_edt_email.setText(document.getData().get("email").toString());
                                        if(document.getData().get("gender").equals("Nam")){
                                            profile_spinner_gender.setSelection(2);
                                        } else {
                                            profile_spinner_gender.setSelection(1);
                                        }
                                        Glide.with(getApplication()).load(url_profile).into(img_accountsetup_profile);
                                        startActivity(new  Intent(Screen_Profile.this, Screen_Pin_Code.class));
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