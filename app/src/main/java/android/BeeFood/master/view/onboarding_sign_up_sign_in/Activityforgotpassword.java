package android.BeeFood.master.view.onboarding_sign_up_sign_in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.BeeFood.master.R;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Activityforgotpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityforgotpassword);
        doimk("sondtph22824@fpt.edu.vn");
    }

    private void doimk(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Activityforgotpassword.this, "Email sent.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}