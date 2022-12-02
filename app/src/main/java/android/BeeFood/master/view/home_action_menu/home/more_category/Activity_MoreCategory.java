package android.BeeFood.master.view.home_action_menu.home.more_category;

import android.BeeFood.master.R;
import android.BeeFood.master.view.home_action_menu.home.Adapter_Foodtype;
import android.BeeFood.master.view.home_action_menu.home.food.Activity_food;
import android.BeeFood.master.view.object.Loai_food;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Activity_MoreCategory extends AppCompatActivity {

    private ArrayList<Loai_food> list_FoodType = new ArrayList<>();
    private Adapter_Foodtype adapter_FoodType;
    private GridView grid_home_ActionMenu_MoreCategory_list;
    private ImageView img_home_ActionMenu_MoreCategory_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_category);

        AnhXa();

        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh miw"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mie"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh m"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh miw"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mie"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh m"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh m"));

        adapter_FoodType = new Adapter_Foodtype(Activity_MoreCategory.this,list_FoodType,R.layout.home_item_foodtype);
        grid_home_ActionMenu_MoreCategory_list.setAdapter(adapter_FoodType);

        grid_home_ActionMenu_MoreCategory_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sItem = list_FoodType.get(i).getName();
                Intent intent_toFood = new Intent(Activity_MoreCategory.this, Activity_food.class);
                intent_toFood.putExtra("key_FoodType",sItem);
                intent_toFood.putExtra("key_chkSpecialMore",true);
                startActivity(intent_toFood);
                finish();
            }
        });

        img_home_ActionMenu_MoreCategory_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Activity_MoreCategory.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
                onBackPressed();
            }
        });
    }

    public void AnhXa(){
        grid_home_ActionMenu_MoreCategory_list = findViewById(R.id.home_ActionMenu_MoreCategory_list);
        img_home_ActionMenu_MoreCategory_back = findViewById(R.id.home_ActionMenu_MoreCategory_back);
    }
}