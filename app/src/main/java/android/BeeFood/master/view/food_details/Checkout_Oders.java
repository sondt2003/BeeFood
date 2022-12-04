package android.BeeFood.master.view.food_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.BeeFood.master.R;
import android.BeeFood.master.view.object.adapter.Adapter_RecyclerView_CheckOut_OrderSummary;
import android.BeeFood.master.view.object.Food;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Checkout_Oders extends AppCompatActivity {

    private Toolbar toolbar_checkout_Oders_Toolbar;
    private TextView tv_checkout_Oders_address,tv_checkout_Oders_Subtotal_Money,tv_checkout_Oders_Delivery_Money,tv_checkout_Oders_Total_Money;
    private Button btn_checkout_Oders_PlaceOrder;
    private RecyclerView recyclerView_checkout_Oders_RecyclerView;

    private Adapter_RecyclerView_CheckOut_OrderSummary adapter_recyclerView_checkOut_orderSummary;
    private ArrayList<Food> mArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_oders);

        anhXa();
//
        setSupportActionBar(toolbar_checkout_Oders_Toolbar);
        getSupportActionBar().setTitle("Checkout Orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adapter_recyclerView_checkOut_orderSummary = new Adapter_RecyclerView_CheckOut_OrderSummary(Checkout_Oders.this);
        adapter_recyclerView_checkOut_orderSummary.setData(mArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Checkout_Oders.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_checkout_Oders_RecyclerView.setLayoutManager(layoutManager);
        recyclerView_checkout_Oders_RecyclerView.setAdapter(adapter_recyclerView_checkOut_orderSummary);

    }

    public void anhXa() {

        toolbar_checkout_Oders_Toolbar = findViewById(R.id.checkout_Oders_Toolbar);

        recyclerView_checkout_Oders_RecyclerView = findViewById(R.id.checkout_Oders_RecyclerView);

        tv_checkout_Oders_address = findViewById(R.id.checkout_Oders_address);
        tv_checkout_Oders_Subtotal_Money = findViewById(R.id.checkout_Oders_Subtotal_Money);
        tv_checkout_Oders_Delivery_Money = findViewById(R.id.checkout_Oders_Delivery_Money);
        tv_checkout_Oders_Total_Money = findViewById(R.id.checkout_Oders_Total_Money);

        btn_checkout_Oders_PlaceOrder = findViewById(R.id.checkout_Oders_PlaceOrder);


    }
}