package android.BeeFood.master.View.AccountSetup;

import android.BeeFood.master.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Screen_Profile extends AppCompatActivity {
    Button btn_continue;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText profile_edt_fullname, profile_edt_nickname, profile_edt_date_of_birth, profile_edt_email;
    Spinner profile_spinner_gender;
    int count;
    boolean kiemtra=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_profile);
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Gender");
        spinnerArray.add("Nữ");
        spinnerArray.add("Nam");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        profile_edt_fullname = findViewById(R.id.profile_edt_fullname);
        profile_edt_nickname = findViewById(R.id.profile_edt_nickname);
        profile_edt_date_of_birth = findViewById(R.id.profile_edt_date_of_birth);
        profile_edt_email = findViewById(R.id.profile_edt_email);
        profile_spinner_gender = findViewById(R.id.profile_spinner_gender);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        profile_spinner_gender.setAdapter(adapter);

        btn_continue = findViewById(R.id.profile_continue);
        count=0;
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
                } else if (profile_spinner_gender.getSelectedItemPosition() == 0) {
                    Toast.makeText(Screen_Profile.this, "Chưa Nhập Giới Tính", Toast.LENGTH_SHORT).show();
                } else {
                    db.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            SharedPreferences sharedPref = getSharedPreferences("USER", MODE_PRIVATE);
                                            String email = sharedPref.getString("email", "");
                                            if(email.equalsIgnoreCase(document.getData().get("email").toString())){
                                                Log.d("GETUSER", document.getId() + " => " + document.getData().get("middle"));
                                            }
                                            Log.d("GETUSER", document.getId() + " => " + document.getData().get("middle"));
                                        }
                                    } else {
                                        Log.w("GETUSER", "Error getting documents.", task.getException());
                                    }
                                }
                            });
                    if(count!=0){
                        return;
                    }
                    count++;
                    SharedPreferences sharedPref = getSharedPreferences("USER", MODE_PRIVATE);
                    String email = sharedPref.getString("email", "");
                    Map<String, Object> user = new HashMap<>();
                    user.put("email", email);
                    user.put("fullname", profile_edt_fullname.getText().toString());
                    user.put("nickname", profile_edt_nickname.getText().toString());
                    user.put("dateofbirth", profile_edt_date_of_birth.getText().toString());
                    user.put("gender", profile_spinner_gender.getSelectedItem().toString());
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Screen_Profile.this, "Cập Nhật Thông Tin Thành Công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Screen_Profile.this, MapsActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Screen_Profile.this, "Cập Nhật Thông Tin Thất Bại", Toast.LENGTH_SHORT).show();
                                    count=0;
                                }
                            });

                }
            }
        });

    }
}