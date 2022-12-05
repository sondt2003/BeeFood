package android.BeeFood.master.view.onboarding_sign_up_sign_in;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.FoodDao;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FoodDao foodDao=new FoodDao();
        if(!foodDao.getEmail(getApplication()).isEmpty()){
            startActivity(new Intent(Splash_Screen.this, HomeActivity.class));
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splash_Screen.this, Onboarding_Screen.class));
                    finish();
                }
            },2000);
        }
        Toast.makeText(this, foodDao.getEmail(getApplication()), Toast.LENGTH_SHORT).show();
    }
}