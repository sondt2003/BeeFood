package android.BeeFood.master.view.home_action_menu.home.mycart;

import android.BeeFood.master.R;
import android.BeeFood.master.model.OderFood;
import android.BeeFood.master.view.object.MyCart;
import android.os.Bundle;
import android.view.View;
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

public class Activity_MyCart extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView img_home_ActionMenu_MyCart_Back;
    private RecyclerView recyclerView_home_ActionMenu_MyCart_listCart;
    private LinearLayout layout_home_ActionMenu_MyCart_Empty;
ImageView myCartFood;
    private ArrayList<MyCart> lis_myCart = new ArrayList<>();
    private Adapter_MyCart adapter_myCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        AnhXa();
        myCartFood=findViewById(R.id.myCartFood);
        adapter_myCart = new Adapter_MyCart(Activity_MyCart.this);
        db.collection("oderfood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<OderFood> oderFoods = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                oderFoods.add(new OderFood(
                                        document.getData().get("idfood").toString(),
                                        document.getData().get("emailfood").toString(),
                                        document.getData().get("priceoderfood").toString(),
                                        document.getData().get("soluong").toString(),
                                        document.getData().get("khoangcach").toString()));
                            }
                            if(oderFoods.isEmpty()){
                                myCartFood.setVisibility(View.VISIBLE);
                            } else {
                                myCartFood.setVisibility(View.INVISIBLE);
                            }
                            adapter_myCart.setData(oderFoods);
                        }
                    }
                });
        LinearLayoutManager manager = new LinearLayoutManager(Activity_MyCart.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_home_ActionMenu_MyCart_listCart.setLayoutManager(manager);
        recyclerView_home_ActionMenu_MyCart_listCart.setAdapter(adapter_myCart);

        img_home_ActionMenu_MyCart_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Activity_MyCart.this, HomeActivity.class);
//                startActivity(intent);
//                finish();

                onBackPressed();
            }
        });
        evenCLick();
    }

    private void evenCLick() {

    }

    public  void AnhXa(){
        img_home_ActionMenu_MyCart_Back = findViewById(R.id.home_ActionMenu_MyCart_Back);
        recyclerView_home_ActionMenu_MyCart_listCart = findViewById(R.id.home_ActionMenu_MyCart_listCart);
        layout_home_ActionMenu_MyCart_Empty = findViewById(R.id.home_ActionMenu_MyCart_Empty);
    }
}