package android.BeeFood.master.view.accountSetup;

import androidx.appcompat.app.AppCompatActivity;

import android.BeeFood.master.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Screen_Recognition extends AppCompatActivity {
    Button btn_accountsetup_recognition_skip,btn_accountsetup_recognition_continue;
    ImageView img_accountsetup_recognition_previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_recognition);
        btn_accountsetup_recognition_skip=findViewById(R.id.btn_accountsetup_recognition_skip);
        btn_accountsetup_recognition_continue=findViewById(R.id.btn_accountsetup_recognition_continue);
        btn_accountsetup_recognition_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Recognition.this,Screen_Recognition.class));
            }
        });
        btn_accountsetup_recognition_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Screen_Recognition.this,Screen_Recognition.class));
            }
        });
    }
}