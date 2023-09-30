package android.BeeFood.master.view.accountSetup;

import androidx.appcompat.app.AppCompatActivity;

import android.BeeFood.master.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Screen_Fingerprint extends AppCompatActivity {
Button btn_accountsetup_fingerprint_skip,btn_accountsetup_fingerprint_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_fingerprint);
        btn_accountsetup_fingerprint_skip=findViewById(R.id.btn_accountsetup_fingerprint_skip);
        btn_accountsetup_fingerprint_continue=findViewById(R.id.btn_accountsetup_fingerprint_continue);

        btn_accountsetup_fingerprint_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Fingerprint.this,Screen_Recognition.class));
            }
        });
        btn_accountsetup_fingerprint_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Fingerprint.this,Screen_Recognition.class));
            }
        });
    }
}