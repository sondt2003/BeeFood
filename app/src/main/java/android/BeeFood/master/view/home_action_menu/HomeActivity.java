package android.BeeFood.master.view.home_action_menu;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.InternetCheckService;
import android.BeeFood.master.view.add_san_pham.Fragment_QuanLy;
import android.BeeFood.master.view.history.History_Fragment;
import android.BeeFood.master.view.home_action_menu.home.Fragment_home;
import android.BeeFood.master.view.orders.OrdersFragment;
import android.BeeFood.master.view.profile.Profile_Fragment;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class HomeActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private BottomNavigationView btnNavigation_home_ActionMenu_Main_layout;
    BroadcastReceiver  broadcastReceiver = new InternetCheckService(getApplication(),"HomeActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        checkInternet();
        AnhXa();


        loadFragment(Fragment_home.newInstance());

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
                case R.id.home_menu_addSanPham:
                db.collection("users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        SharedPreferences sharedPref = getSharedPreferences("USER",MODE_PRIVATE);
                                        String email = sharedPref.getString("email", "");
                                        try {
                                            if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                                if (document.getData().get("admin").toString().trim().equals("user")) {
                                                    Toast.makeText(HomeActivity.this, "Ban khong co  quyen", Toast.LENGTH_SHORT).show();
                                                } else if (document.getData().get("admin").toString().trim().equals("shipper")) {
                                                    Toast.makeText(HomeActivity.this, "Ban khong co  quyen", Toast.LENGTH_SHORT).show();
                                                } else if (document.getData().get("admin").toString().trim().equals("admin")) {
                                                    loadFragment(Fragment_QuanLy.newInstance());
                                                    break;
                                                }
                                            }
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                            }
                        });

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
                finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No",null);

        builder.show();
    }

    public void AnhXa(){
        btnNavigation_home_ActionMenu_Main_layout = findViewById(R.id.home_ActionMenu_Main_Menu);
    }
    private void checkInternet() {
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(broadcastReceiver);
//    }


    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        int activity_back = 0;
        activity_back = intent.getIntExtra("key_back",0);
        switch (activity_back){
            case 0:
                break;
            case 1:
                loadFragment(OrdersFragment.newInstance());
                break;
        }
    }
}