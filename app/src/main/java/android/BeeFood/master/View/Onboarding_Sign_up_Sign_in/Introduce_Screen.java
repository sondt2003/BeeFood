package android.BeeFood.master.View.Onboarding_Sign_up_Sign_in;

import android.BeeFood.master.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Introduce_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Introduce_Screen.this, Onboarding_Screen.class));
            }
        },5000);
    }
}