package android.BeeFood.master.View.Home_Action_Menu;

import android.BeeFood.master.R;
import android.BeeFood.master.View.history.History_Fragment;
import android.BeeFood.master.View.Home_Action_Menu.home.Fragment_home;
import android.BeeFood.master.View.Orders.OrdersFragment;
import android.BeeFood.master.View.Profile.Profile_Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView btnNavigation_home_ActionMenu_Main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AnhXa();

        Intent intent = getIntent();

        int activity_back = 0;
        activity_back = intent.getIntExtra("key_back",0);
        switch (activity_back){
            case 0:
                loadFragment(Fragment_home.newInstance());
                break;
            case 1:
                loadFragment(OrdersFragment.newInstance());
                break;
            case 2:
                loadFragment(History_Fragment.newInstance());
                break;
            case 3:
                loadFragment(Profile_Fragment.newInstance());
                break;
        }



        btnNavigation_home_ActionMenu_Main_layout.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_menu_Home:
                    loadFragment(Fragment_home.newInstance());
                    break;
                case R.id.home_menu_Orders:
                    loadFragment(OrdersFragment.newInstance());
                    break;
                case R.id.home_menu_Wallet:
                    loadFragment(History_Fragment.newInstance());
                    break;
                case R.id.home_menu_Profile:
                    loadFragment(Profile_Fragment.newInstance());
                    break;
            }
            return true;
        });

    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_ActionMenu_Main_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Thoát");
        builder.setMessage("Bạn có muốn thoát ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        builder.setNegativeButton("No",null);

        builder.show();
    }

    public void AnhXa(){
        btnNavigation_home_ActionMenu_Main_layout = findViewById(R.id.home_ActionMenu_Main_Menu);
    }

}