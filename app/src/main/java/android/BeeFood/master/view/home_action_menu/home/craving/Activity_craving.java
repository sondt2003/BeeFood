package android.BeeFood.master.view.home_action_menu.home.craving;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.home_action_menu.home.Adapter_Recommended;
import android.BeeFood.master.view.home_action_menu.home.food.Adapter_foodSort;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

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


public class Activity_craving extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ImageView img_home_ActionMenu_Craving_back;

    private ArrayList<String> lis_foodSort = new ArrayList<>();
    private RecyclerView recyclerView_home_ActionMenu_Craving_list;
    private LinearLayout layout_home_ActionMenu_Craving_Empty;
    private EditText edt_home_ActionMenu_Craving_Search;
    Adapter_Recommended adapter_recommended;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craving);

        AnhXa();


        LinearLayoutManager manager = new LinearLayoutManager(Activity_craving.this,LinearLayoutManager.HORIZONTAL,false);

        adapter_recommended = new Adapter_Recommended(Activity_craving.this);

        edt_home_ActionMenu_Craving_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loadList(edt_home_ActionMenu_Craving_Search.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        loadList("");



        img_home_ActionMenu_Craving_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void AnhXa(){
        recyclerView_home_ActionMenu_Craving_list = findViewById(R.id.home_ActionMenu_Craving_list);
        layout_home_ActionMenu_Craving_Empty = findViewById(R.id.layout_home_ActionMenu_Craving_Empty);
        img_home_ActionMenu_Craving_back = findViewById(R.id.home_ActionMenu_Craving_back);
        edt_home_ActionMenu_Craving_Search = findViewById(R.id.home_ActionMenu_Craving_Search);
    }

    public void loadList(String nameFood){
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Food> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("namefood").toString().trim().contains(nameFood)){
                                    list.add(new Food(document.getData().get("namefood").toString(),
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
                            layout_home_ActionMenu_Craving_Empty.setVisibility(View.VISIBLE);
                        }else{
                            layout_home_ActionMenu_Craving_Empty.setVisibility(View.INVISIBLE);
                        }

                        adapter_recommended.setData(list);
                        LinearLayoutManager manager1 = new LinearLayoutManager(Activity_craving.this,LinearLayoutManager.VERTICAL,false);
                        recyclerView_home_ActionMenu_Craving_list.setLayoutManager(manager1);
                        recyclerView_home_ActionMenu_Craving_list.setAdapter(adapter_recommended);

                    }
                });
    }
}