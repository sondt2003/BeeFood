package android.BeeFood.master.view.accountSetup;

import android.BeeFood.master.R;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Screen_Pin_Code extends AppCompatActivity {
    Button btn_accountsetup_pincode_continue;
    PinView pinView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_pin_code);
        SharedPreferences sharedPref = getSharedPreferences("USER", MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        pinView = findViewById(R.id.firstPinView);
        pinView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (pinView.getText().length() >= 5 && check) {
                    updateCourses(email,pinView.getText().toString());
                    check = false;
                    startActivity(new Intent(Screen_Pin_Code.this, HomeActivity.class));
                    finishAffinity();
                }
                return false;
            }
        });
        btn_accountsetup_pincode_continue = findViewById(R.id.btn_accountsetup_pincode_continue);
        btn_accountsetup_pincode_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Pin_Code.this, HomeActivity.class));
            }
        });
    }


    private void updateCourses(String email,String pin) {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (email.equalsIgnoreCase(document.getData().get("email").toString())){
                                    DocumentReference washingtonRef = db.collection("users").document(document.getId());
                                    washingtonRef
                                            .update("pin", pin)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(Screen_Pin_Code.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(Screen_Pin_Code.this, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                            });
                                }
                            }
                        }
                    }
                });
    }
}