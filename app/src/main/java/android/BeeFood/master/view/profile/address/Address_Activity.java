package android.BeeFood.master.view.profile.address;

import android.BeeFood.master.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class Address_Activity extends AppCompatActivity {
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        img_back = findViewById(R.id.profile_ActionMenu_Back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });
    }
}