package android.BeeFood.master.view.home_action_menu.home.special_offers;

import android.BeeFood.master.R;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_Special_offers extends AppCompatActivity {

    private ImageView img_home_ActionMenu_SpecialOffers_back;

    private ArrayList<Integer> lis_banner = new ArrayList<>();
    private Adapter_Special_offers adapter_special_offers;
    private RecyclerView recyclerView_home_ActionMenu_SpecialOffers_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_offers);

        AnhXa();


        lis_banner.add(R.drawable.avt_test);
        lis_banner.add(R.drawable.avt_test);
        lis_banner.add(R.drawable.avt_test);
        lis_banner.add(R.drawable.avt_test);
        lis_banner.add(R.drawable.avt_test);


        adapter_special_offers = new Adapter_Special_offers(Activity_Special_offers.this);
        adapter_special_offers.setData(lis_banner);

        LinearLayoutManager manager = new LinearLayoutManager(Activity_Special_offers.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_home_ActionMenu_SpecialOffers_list.setLayoutManager(manager);
        recyclerView_home_ActionMenu_SpecialOffers_list.setAdapter(adapter_special_offers);

        img_home_ActionMenu_SpecialOffers_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Activity_Special_offers.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
                onBackPressed();
            }
        });

    }

    public void AnhXa(){
        recyclerView_home_ActionMenu_SpecialOffers_list = findViewById(R.id.home_ActionMenu_SpecialOffers_list);

        img_home_ActionMenu_SpecialOffers_back = findViewById(R.id.home_ActionMenu_SpecialOffers_back);
    }
}