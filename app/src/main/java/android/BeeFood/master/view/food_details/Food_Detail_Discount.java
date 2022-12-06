package android.BeeFood.master.view.food_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.BeeFood.master.R;
import android.BeeFood.master.model.khuyenMai;
import android.BeeFood.master.view.food_details.adapter.Adapter_RecyclerView_Discount;
import android.os.Bundle;

import java.util.ArrayList;

public class Food_Detail_Discount extends AppCompatActivity {

    private Toolbar toolbar_food_detail_discount_toolbar;
    private RecyclerView recyclerView_food_detail_discount_recyclerView;
    private ArrayList<khuyenMai> mArrayList = new ArrayList<>();
    private Adapter_RecyclerView_Discount adapter_recyclerView_discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail_discount);
        
        anhXa();

        adapter_recyclerView_discount = new Adapter_RecyclerView_Discount(Food_Detail_Discount.this);
        adapter_recyclerView_discount.setData(mArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Food_Detail_Discount.this, LinearLayoutManager.VERTICAL, false);
        recyclerView_food_detail_discount_recyclerView.setLayoutManager(layoutManager);
        recyclerView_food_detail_discount_recyclerView.setAdapter(adapter_recyclerView_discount);


    }

    private void anhXa() {
        toolbar_food_detail_discount_toolbar = findViewById(R.id.food_detail_discount_toolbar);
        recyclerView_food_detail_discount_recyclerView = findViewById(R.id.food_detail_discount_recyclerView);
    }

    public void DataAo(){
        mArrayList.add(new khuyenMai("10.000","1000","20.000","5"));
        mArrayList.add(new khuyenMai("20.000","2000","30.000","4"));
        mArrayList.add(new khuyenMai("30.000","3000","40.000","3"));
        mArrayList.add(new khuyenMai("40.000","4000","50.000","2"));
    }
}