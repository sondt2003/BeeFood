package android.BeeFood.master.view.food_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.BeeFood.master.R;
import android.BeeFood.master.model.khuyenMai;
import android.BeeFood.master.view.add_san_pham.Adapter_KhuyenMai;
import android.BeeFood.master.view.food_details.adapter.Adapter_RecyclerView_Discount;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Food_Detail_Discount extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Toolbar toolbar_food_detail_discount_toolbar;
    private RecyclerView recyclerView_food_detail_discount_recyclerView;
    private ArrayList<khuyenMai> mArrayList = new ArrayList<>();
    private Adapter_RecyclerView_Discount adapter_recyclerView_discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail_discount);
        
        anhXa();


        setSupportActionBar(toolbar_food_detail_discount_toolbar);
        getSupportActionBar().setTitle("Discount");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadList();


    }

    private void anhXa() {
        toolbar_food_detail_discount_toolbar = findViewById(R.id.food_detail_discount_toolbar);
        recyclerView_food_detail_discount_recyclerView = findViewById(R.id.food_detail_discount_recyclerView);
    }

    public void loadList() {
        db.collection("phieugiamgia")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<khuyenMai> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    list.add(new khuyenMai(document.getId(), document.getData().get("giam").toString(), document.getData().get("min").toString(), document.getData().get("max").toString(), document.getData().get("soluong").toString()));
                                } catch (Exception e) {

                                }
                            }
                        }
                        adapter_recyclerView_discount = new Adapter_RecyclerView_Discount(Food_Detail_Discount.this);
                        adapter_recyclerView_discount.setData(list);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(Food_Detail_Discount.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView_food_detail_discount_recyclerView.setLayoutManager(layoutManager);
                        recyclerView_food_detail_discount_recyclerView.setAdapter(adapter_recyclerView_discount);

                    }
                });
    }

}