package android.BeeFood.master.View.Home_Action_Menu.home.Food;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Home_Action_Menu.HomeActivity;
import android.BeeFood.master.View.Home_Action_Menu.home.Adapter_Recommended;
import android.BeeFood.master.View.Home_Action_Menu.home.More_Category.Activity_MoreCategory;
import android.BeeFood.master.View.Object.Food;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_food extends AppCompatActivity {

    private ImageView img_home_ActionMenu_foodType_Back;
    private LinearLayout LineaLayout_home_ActionMenu_foodType_Empty;
    private TextView tv_home_ActionMenu_foodType_title;

    private ArrayList<Food> lis_food = new ArrayList<>();
    private Adapter_Recommended adapter_recommended;
    private RecyclerView recyclerView_home_ActionMenu_foodType_list;

    private ArrayList<String> lis_foodSort = new ArrayList<>();
    private Adapter_foodSort adapter_foodSort;
    private RecyclerView recyclerView_home_ActionMenu_foodType_sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        AnhXa();

        Intent intent = getIntent();
        String sTitle = intent.getStringExtra("key_FoodType");
        boolean chkSpecialMore = intent.getBooleanExtra("key_chkSpecialMore",false);

        tv_home_ActionMenu_foodType_title.setText(sTitle);

        lis_foodSort.add("sao");
        lis_foodSort.add("gia");
        lis_foodSort.add("khoang cach");
        lis_foodSort.add("so luot ban");


        adapter_foodSort = new Adapter_foodSort(Activity_food.this);
        adapter_foodSort.setData(lis_foodSort);

        LinearLayoutManager manager = new LinearLayoutManager(Activity_food.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_home_ActionMenu_foodType_sort.setLayoutManager(manager);
        recyclerView_home_ActionMenu_foodType_sort.setAdapter(adapter_foodSort);


        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));


        adapter_recommended = new Adapter_Recommended(Activity_food.this);
        adapter_recommended.setData(lis_food);

        LinearLayoutManager manager1 = new LinearLayoutManager(Activity_food.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_home_ActionMenu_foodType_list.setLayoutManager(manager1);
        recyclerView_home_ActionMenu_foodType_list.setAdapter(adapter_recommended);

        if (lis_food.isEmpty()){
            LineaLayout_home_ActionMenu_foodType_Empty.setVisibility(View.VISIBLE);
        }else{
            LineaLayout_home_ActionMenu_foodType_Empty.setVisibility(View.INVISIBLE);
        }


        img_home_ActionMenu_foodType_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (chkSpecialMore){
//                    Intent intent = new Intent(Activity_food.this, Activity_MoreCategory.class);
//                    startActivity(intent);
//                    finish();
//                    return;
//                }
//                Intent intent = new Intent(Activity_food.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
                onBackPressed();
            }
        });

    }

    public void AnhXa(){
        LineaLayout_home_ActionMenu_foodType_Empty = findViewById(R.id.home_ActionMenu_foodType_Empty);
        recyclerView_home_ActionMenu_foodType_list = findViewById(R.id.home_ActionMenu_foodType_list);
        recyclerView_home_ActionMenu_foodType_sort = findViewById(R.id.home_ActionMenu_foodType_Sort);
        img_home_ActionMenu_foodType_Back = findViewById(R.id.home_ActionMenu_foodType_Back);
        tv_home_ActionMenu_foodType_title = findViewById(R.id.home_ActionMenu_foodType_title);
    }
}