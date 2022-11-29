package android.BeeFood.master.View.Profile.address;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Home_Action_Menu.HomeActivity;
import android.BeeFood.master.View.Home_Action_Menu.home.Craving.Activity_craving;
import android.BeeFood.master.View.Profile.Profile_Fragment;
import android.content.Intent;
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
                Intent intent = new Intent(Address_Activity.this, Profile_Fragment.class);
                startActivity(intent);
                finish();
            }
        });
    }
}