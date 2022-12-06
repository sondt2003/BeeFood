package android.BeeFood.master.view.home_action_menu.home.food;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.home_action_menu.home.Adapter_Recommended;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity_food extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ImageView img_home_ActionMenu_foodType_Back;
    private LinearLayout LineaLayout_home_ActionMenu_foodType_Empty;
    private TextView tv_home_ActionMenu_foodType_title;

    private ArrayList<Food> lis_food = new ArrayList<>();
    private Adapter_Recommended adapter_recommended;
    private RecyclerView recyclerView_home_ActionMenu_foodType_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        AnhXa();

        Intent intent = getIntent();
        String sTitle = intent.getStringExtra("key_FoodType").trim();
        boolean chkSpecialMore = intent.getBooleanExtra("key_chkSpecialMore",false);

        tv_home_ActionMenu_foodType_title.setText(sTitle);

        adapter_recommended = new Adapter_Recommended(Activity_food.this);
        ArrayList<Food> listgetfood = new ArrayList<>();

        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Food> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (sTitle.equalsIgnoreCase(document.getData().get("tenloai").toString().trim())){
                                    list.add(new Food(document.getId(),document.getData().get("namefood").toString(),
                                            document.getData().get("price").toString(),
                                            document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(),
                                            document.getData().get("email").toString(),
                                            "Chưa có id",
                                            document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(),
                                            document.getData().get("describle").toString()));
                                }
                            }
                        }

                        if (list.isEmpty()){
                            LineaLayout_home_ActionMenu_foodType_Empty.setVisibility(View.VISIBLE);
                        }else{
                            LineaLayout_home_ActionMenu_foodType_Empty.setVisibility(View.INVISIBLE);
                        }

                        adapter_recommended.setData(list);
                        LinearLayoutManager manager1 = new LinearLayoutManager(Activity_food.this,LinearLayoutManager.VERTICAL,false);
                        recyclerView_home_ActionMenu_foodType_list.setLayoutManager(manager1);
                        recyclerView_home_ActionMenu_foodType_list.setAdapter(adapter_recommended);

                    }
                });




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
        img_home_ActionMenu_foodType_Back = findViewById(R.id.home_ActionMenu_foodType_Back);
        tv_home_ActionMenu_foodType_title = findViewById(R.id.home_ActionMenu_foodType_title);
    }
}