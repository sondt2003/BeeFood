package android.BeeFood.master.view.chot_don;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.BeeFood.master.R;
import android.BeeFood.master.model.BuyFood;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity_shipper extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Toolbar shipper_toolbar;
    private RecyclerView recyclerView_shipper_donHang;
    private Adapter_shipper adapter_shipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper);

        anhXa();

        setSupportActionBar(shipper_toolbar);
        getSupportActionBar().setTitle("Các đơn hàng (shipper)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter_shipper = new Adapter_shipper(Activity_shipper.this);
        db.collection("buyfood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        SharedPreferences sharedPref = getApplication().getSharedPreferences("USER", Context.MODE_PRIVATE);
                        String email = sharedPref.getString("email", "");
                        ArrayList<BuyFood> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("status").toString().equalsIgnoreCase("choVanChuyen")){
                                    list.add(new BuyFood(
                                            document.getId(),
                                            document.getData().get("idfood").toString(),
                                            document.getData().get("emailuser").toString(),
                                            document.getData().get("emailfood").toString(),
                                            document.getData().get("amountofood").toString(),
                                            document.getData().get("priceOderFood").toString(),
                                            "1.8",  //chưa có khoảng cách
                                            document.getData().get("status").toString()));
                                }

                            }
                        }
                        adapter_shipper.setData(list);

                        LinearLayoutManager manager = new LinearLayoutManager(getApplication(),LinearLayoutManager.VERTICAL,false);
                        recyclerView_shipper_donHang.setLayoutManager(manager);
                        recyclerView_shipper_donHang.setAdapter(adapter_shipper);

//                        if (list.isEmpty()){
//                            empty_fragment_oder_active_empty.setVisibility(View.VISIBLE);
//                        }else{
//                            empty_fragment_oder_active_empty.setVisibility(View.INVISIBLE);
//                        }
                    }
                });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void anhXa(){
        shipper_toolbar = findViewById(R.id.shipper_toolbar);
        recyclerView_shipper_donHang = findViewById(R.id.shipper_donHang);
    }


}