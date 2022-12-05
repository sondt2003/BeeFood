package android.BeeFood.master.view.home_action_menu.home.more_category;

import android.BeeFood.master.R;
import android.BeeFood.master.model.loaiFood;
import android.BeeFood.master.view.home_action_menu.home.Adapter_Foodtype;
import android.BeeFood.master.view.home_action_menu.home.food.Activity_food;
import android.BeeFood.master.view.object.Loai_food;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Activity_MoreCategory extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ArrayList<Loai_food> list_FoodType = new ArrayList<>();
    private Adapter_Foodtype adapter_FoodType;
    private GridView grid_home_ActionMenu_MoreCategory_list;
    private ImageView img_home_ActionMenu_MoreCategory_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_category);

        AnhXa();

        db.collection("loaifood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<loaiFood> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    list.add(new loaiFood(document.getId(), document.getData().get("ImageUrl").toString(),document.getData().get("NameLoai").toString()));
                                } catch (Exception e) {

                                }
                            }
                        }
                        adapter_FoodType = new Adapter_Foodtype(Activity_MoreCategory.this,list,R.layout.home_item_foodtype);
                        grid_home_ActionMenu_MoreCategory_list.setAdapter(adapter_FoodType);

                        grid_home_ActionMenu_MoreCategory_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                String sItem = list.get(i).getNameloai();

                                Intent intent_toFood = new Intent(Activity_MoreCategory.this, Activity_food.class);
                                intent_toFood.putExtra("key_FoodType",sItem);
                                intent_toFood.putExtra("key_chkSpecialMore",false);
                                startActivity(intent_toFood);
                            }
                        });
                    }
                });

//        adapter_FoodType = new Adapter_Foodtype(Activity_MoreCategory.this,list_FoodType,R.layout.home_item_foodtype);
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