package android.BeeFood.master.View.Home_Action_Menu.home.Craving;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Home_Action_Menu.HomeActivity;
import android.BeeFood.master.View.Home_Action_Menu.home.Food.Adapter_foodSort;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Activity_craving extends AppCompatActivity {

    private ImageView img_home_ActionMenu_Craving_back;

    private ArrayList<String> lis_foodSort = new ArrayList<>();
    private Adapter_foodSort adapter_foodSort;
    private RecyclerView recyclerView_home_ActionMenu_Craving_sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craving);

        AnhXa();

        lis_foodSort.add("sao");
        lis_foodSort.add("gia");
        lis_foodSort.add("khoang cach");
        lis_foodSort.add("so luot ban");

        adapter_foodSort = new Adapter_foodSort(Activity_craving.this);
        adapter_foodSort.setData(lis_foodSort);

        LinearLayoutManager manager = new LinearLayoutManager(Activity_craving.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_home_ActionMenu_Craving_sort.setLayoutManager(manager);
        recyclerView_home_ActionMenu_Craving_sort.setAdapter(adapter_foodSort);

        img_home_ActionMenu_Craving_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_craving.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void AnhXa(){
        recyclerView_home_ActionMenu_Craving_sort = findViewById(R.id.home_ActionMenu_Craving_sort);
        img_home_ActionMenu_Craving_back = findViewById(R.id.home_ActionMenu_Craving_back);
    }
}