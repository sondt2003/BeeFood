package android.BeeFood.master.View.AccountSetup;

import android.BeeFood.master.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;

public class Screen_Pin_Code extends AppCompatActivity {
    Button btn_accountsetup_pincode_continue;
     PinView pinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_pin_code);
        pinView = findViewById(R.id.firstPinView);
        btn_accountsetup_pincode_continue=findViewById(R.id.btn_accountsetup_pincode_continue);
        btn_accountsetup_pincode_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Screen_Pin_Code.this, pinView.getText(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Screen_Pin_Code.this,Screen_Fingerprint.class));
            }
        });
    }
}