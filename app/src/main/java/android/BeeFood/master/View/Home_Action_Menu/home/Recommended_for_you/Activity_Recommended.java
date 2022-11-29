package android.BeeFood.master.View.Home_Action_Menu.home.Recommended_for_you;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Home_Action_Menu.HomeActivity;
import android.BeeFood.master.View.Home_Action_Menu.home.Adapter_Recommended;
import android.BeeFood.master.View.Object.Food;
import android.BeeFood.master.View.Object.Loai_food;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Activity_Recommended extends AppCompatActivity {

    private ArrayList<Loai_food> lis_FoodType = new ArrayList<>();
    private ArrayList<Food> lis_Food = new ArrayList<>();

    private Adapter_Recommended adapter_recommended;
    private Adapter_recommended_foodSort mAdapter_recommended_foodSort;
    private ImageView img_home_ActionMenu_Recommended_back;
    private RecyclerView recyclerView_home_ActionMenu_Recommended_sortList;
    private RecyclerView recyclerView_home_ActionMenu_Recommended_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);

        AnhXa();

        lis_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        lis_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        lis_FoodType.add(new Loai_food(R.drawable.avt_test,"banh miw"));
        lis_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        lis_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mie"));
        lis_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        lis_FoodType.add(new Loai_food(R.drawable.avt_test,"banh m"));

        lis_Food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_Food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_Food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_Food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_Food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_Food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));

        mAdapter_recommended_foodSort = new Adapter_recommended_foodSort(Activity_Recommended.this);
        mAdapter_recommended_foodSort.setData(lis_FoodType);

        LinearLayoutManager manager = new LinearLayoutManager(Activity_Recommended.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_home_ActionMenu_Recommended_sortList.setLayoutManager(manager);
        recyclerView_home_ActionMenu_Recommended_sortList.setAdapter(mAdapter_recommended_foodSort);

        adapter_recommended = new Adapter_Recommended(Activity_Recommended.this);
        adapter_recommended.setData(lis_Food);

        LinearLayoutManager manager1 = new LinearLayoutManager(Activity_Recommended.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_home_ActionMenu_Recommended_list.setLayoutManager(manager1);
        recyclerView_home_ActionMenu_Recommended_list.setAdapter(adapter_recommended);


        img_home_ActionMenu_Recommended_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Recommended.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void AnhXa(){
        img_home_ActionMenu_Recommended_back = findViewById(R.id.home_ActionMenu_Recommended_back);
        recyclerView_home_ActionMenu_Recommended_sortList = findViewById(R.id.home_ActionMenu_Recommended_sortList);
        recyclerView_home_ActionMenu_Recommended_list = findViewById(R.id.home_ActionMenu_Recommended_list);
    }
}