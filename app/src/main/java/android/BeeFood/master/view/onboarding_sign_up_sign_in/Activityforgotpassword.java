package android.BeeFood.master.view.onboarding_sign_up_sign_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.BeeFood.master.R;
import android.BeeFood.master.view.accountSetup.Screen_Profile;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Activityforgotpassword extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Toolbar toolbar_forgotPassword_toolbar;
    EditText edt_forgotPassword_email;
    Button btn_forgotPassword_sand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityforgotpassword);
//        doimk("sondtph22824@fpt.edu.vn");

        anhXa();

        setSupportActionBar(toolbar_forgotPassword_toolbar);
        getSupportActionBar().setTitle("Fogot Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn_forgotPassword_sand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_forgotPassword_email.getText().toString().trim();
                
                if (email.isEmpty()){
                    Toast.makeText(Activityforgotpassword.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                }else{

                    db.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                                doimk(email);
                                                return;
                                            }
                                        }
                                        Toast.makeText(Activityforgotpassword.this, "Email không chính xác", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    private void doimk(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Activityforgotpassword.this, "Email sent.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void anhXa(){
        toolbar_forgotPassword_toolbar = findViewById(R.id.forgotPassword_toolbar);
        edt_forgotPassword_email = findViewById(R.id.forgotPassword_email);
        btn_forgotPassword_sand = findViewById(R.id.forgotPassword_sand);
    }

}