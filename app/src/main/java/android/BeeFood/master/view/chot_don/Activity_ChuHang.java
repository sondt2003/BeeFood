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
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity_ChuHang extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private RecyclerView recyclerView_chotDon_chuHang_donHang;
    private Adapter_donHang adapter_donHang;
    private Toolbar chotDon_chuHang_toobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_hang);

        anhXa();

        setSupportActionBar(chotDon_chuHang_toobar);
        getSupportActionBar().setTitle("Các đơn hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter_donHang = new Adapter_donHang(Activity_ChuHang.this);
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
                                if (email.equalsIgnoreCase(document.getData().get("emailfood").toString()) && document.getData().get("status").toString().equalsIgnoreCase("dangDatHang"))
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
                        adapter_donHang.setData(list);

                        LinearLayoutManager manager = new LinearLayoutManager(getApplication(),LinearLayoutManager.VERTICAL,false);
                        recyclerView_chotDon_chuHang_donHang.setLayoutManager(manager);
                        recyclerView_chotDon_chuHang_donHang.setAdapter(adapter_donHang);
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
        recyclerView_chotDon_chuHang_donHang = findViewById(R.id.chotDon_chuHang_donHang);
        chotDon_chuHang_toobar = findViewById(R.id.chotDon_chuHang_toobar);
    }
}