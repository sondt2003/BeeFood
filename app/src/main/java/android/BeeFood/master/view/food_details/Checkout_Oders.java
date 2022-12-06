package android.BeeFood.master.view.food_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.food_details.adapter.Adapter_RecyclerView_CheckOut_OrderSummary;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Checkout_Oders extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar_checkout_Oders_Toolbar;
    private TextView tv_checkout_Oders_address, tv_checkout_Oders_Subtotal_Money, tv_checkout_Oders_Delivery_Money, tv_checkout_Oders_Total_Money,
    tv_checkout_Oders_soTienGiam,tv_checkout_Oders_Min,tv_checkout_Oders_Max,tv_checkout_Oders_soLuong,tv_checkout_Oders_home;
    private Button btn_checkout_Oders_PlaceOrder, btn_checkout_Oders_AddItem;
    private RecyclerView recyclerView_checkout_Oders_RecyclerView;
    private RelativeLayout discount_checkout_Oders_discout_show,relativeLayout_checkout_Discount;

    private Adapter_RecyclerView_CheckOut_OrderSummary adapter_recyclerView_checkOut_orderSummary;
    private ArrayList<Food> mArrayList = new ArrayList<>();
    private int checkDiscount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_oders);


        anhXa();
        Bundle bundle = getIntent().getExtras();
        if (checkDiscount == 0 ){
            checkDiscount =  bundle.getInt("checkVisible");
            if (checkDiscount == 1){
                tv_checkout_Oders_home.setVisibility(View.INVISIBLE);
                relativeLayout_checkout_Discount.setVisibility(View.VISIBLE);
                tv_checkout_Oders_soTienGiam.setText(bundle.getString("soTienGiam"));
                tv_checkout_Oders_Min.setText(bundle.getString("min"));
                tv_checkout_Oders_Max.setText(bundle.getString("max"));
                tv_checkout_Oders_soLuong.setText(bundle.getString("soLuong"));
            }
        }


        setSupportActionBar(toolbar_checkout_Oders_Toolbar);
        getSupportActionBar().setTitle("Checkout Orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adapter_recyclerView_checkOut_orderSummary = new Adapter_RecyclerView_CheckOut_OrderSummary(Checkout_Oders.this);
        adapter_recyclerView_checkOut_orderSummary.setData(mArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Checkout_Oders.this, LinearLayoutManager.VERTICAL, false);
        recyclerView_checkout_Oders_RecyclerView.setLayoutManager(layoutManager);
        recyclerView_checkout_Oders_RecyclerView.setAdapter(adapter_recyclerView_checkOut_orderSummary);

        btn_checkout_Oders_AddItem.setOnClickListener(this);
        btn_checkout_Oders_PlaceOrder.setOnClickListener(this);
        discount_checkout_Oders_discout_show.setOnClickListener(this);


    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void anhXa() {

        toolbar_checkout_Oders_Toolbar = findViewById(R.id.checkout_Oders_Toolbar);

        recyclerView_checkout_Oders_RecyclerView = findViewById(R.id.checkout_Oders_RecyclerView);

        tv_checkout_Oders_address = findViewById(R.id.checkout_Oders_address);
        tv_checkout_Oders_Subtotal_Money = findViewById(R.id.checkout_Oders_Subtotal_Money);
        tv_checkout_Oders_Delivery_Money = findViewById(R.id.checkout_Oders_Delivery_Money);
        tv_checkout_Oders_Total_Money = findViewById(R.id.checkout_Oders_Total_Money);
        tv_checkout_Oders_soTienGiam = findViewById(R.id.checkout_Oders_soTienGiam);
        tv_checkout_Oders_Min = findViewById(R.id.checkout_Oders_Min);
        tv_checkout_Oders_Max = findViewById(R.id.checkout_Oders_Max);
        tv_checkout_Oders_soLuong = findViewById(R.id.checkout_Oders_soLuong);
        tv_checkout_Oders_home = findViewById(R.id.checkout_Oders_home);

        btn_checkout_Oders_PlaceOrder = findViewById(R.id.checkout_Oders_PlaceOrder);
        btn_checkout_Oders_AddItem = findViewById(R.id.checkout_Oders_AddItem);

        discount_checkout_Oders_discout_show = findViewById(R.id.checkout_Oders_discout_show);
        relativeLayout_checkout_Discount = findViewById(R.id.checkout_Discount);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkout_Oders_PlaceOrder:

                break;
            case R.id.checkout_Oders_AddItem:
                Intent intent = new Intent(Checkout_Oders.this,Food_Details_Menu.class);
                startActivity(intent);
                break;
            case R.id.checkout_Oders_discout_show:
                Intent intent1 = new Intent(Checkout_Oders.this,Food_Detail_Discount.class);
                startActivity(intent1);
                break;
        }
    }
}