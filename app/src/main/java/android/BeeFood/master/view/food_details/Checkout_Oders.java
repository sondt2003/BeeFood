package android.BeeFood.master.view.food_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.BuyFoodDao;
import android.BeeFood.master.model.BuyFood;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.food_details.adapter.Adapter_RecyclerView_CheckOut_OrderSummary;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Checkout_Oders extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Toolbar toolbar_checkout_Oders_Toolbar;
    private TextView tv_checkout_Oders_address, tv_checkout_Oders_Subtotal_Money, tv_checkout_Oders_Delivery_Money, tv_checkout_Oders_Total_Money,
    tv_checkout_Oders_soTienGiam,tv_checkout_Oders_Min,tv_checkout_Oders_Max,tv_checkout_Oders_soLuong,tv_checkout_Oders_home,tv_checkout_Oders_gia_discount;
    private Button btn_checkout_Oders_PlaceOrder, btn_checkout_Oders_AddItem;
    private RecyclerView recyclerView_checkout_Oders_RecyclerView;
    private RelativeLayout discount_checkout_Oders_discout_show,relativeLayout_checkout_Discount;

    private Adapter_RecyclerView_CheckOut_OrderSummary adapter_recyclerView_checkOut_orderSummary;
    private ArrayList<Food> mArrayList = new ArrayList<>();
    private int checkDiscount = 0;
    String idorderFood=null;
    String idFood=null;
    BuyFood buyFood;
    String idOrderFood;
    double Total = 0;
    int soluongSP = 0;
    double soTienGiam = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_oders);

        anhXa();

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

        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Intent intent = getIntent();
                        soluongSP = intent.getIntExtra("key_soluongSP",0);
                        idFood = intent.getStringExtra("key_idFood");
                        idOrderFood = intent.getStringExtra("key_orderFood");
                        soTienGiam = intent.getDoubleExtra("key_soTienGiam",0);
//                        Toast.makeText(Checkout_Oders.this, "idfood:"+idFood + ":" +"idorderfood:"+ idOrderFood, Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPref = getApplication().getSharedPreferences("USER", Context.MODE_PRIVATE);
                        String email = sharedPref.getString("email", "");

                        Food food = new Food();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(idFood.equalsIgnoreCase(document.getId())){
                                    food = new Food(document.getData().get("namefood").toString(),
                                            document.getData().get("price").toString(),
                                            document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(),
                                            document.getData().get("email").toString(),
                                            "Chưa có id",
                                            document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(),
                                            document.getData().get("describle").toString());
                                }
                            }
                        }

                        try {
                            double priceFood = Integer.parseInt(food.getPrice());
                            double subtotal = soluongSP * priceFood;

                            double delivery = 0;
                            if (subtotal >= 500000){
                                delivery =0;
                            }else if(subtotal >= 300000){
                                delivery = 10000;
                            }else if (subtotal >= 100000){
                                delivery = 15000;
                            }else {
                                delivery = 20000;
                            }


                            Total = subtotal + delivery - soTienGiam;

                            tv_checkout_Oders_Subtotal_Money.setText(subtotal + " vnd");
                            tv_checkout_Oders_Delivery_Money.setText(delivery + " vnd");
                            tv_checkout_Oders_Total_Money.setText(Total + " vnd");
                            tv_checkout_Oders_gia_discount.setText("- " + soTienGiam + " vnd");

                            btn_checkout_Oders_PlaceOrder.setText("Place Order - " + Total + " vnd");

                            buyFood = new BuyFood(idFood,email,food.getEmail(),soluongSP+"",Total+"",null,"dangDatHang");

                            discount_checkout_Oders_discout_show.setOnClickListener(Checkout_Oders.this);
                        }catch (Exception e){

                        }

                        //

                    }
                });


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
        tv_checkout_Oders_home = findViewById(R.id.checkout_Oders_Discount);
        tv_checkout_Oders_gia_discount =findViewById(R.id.checkout_Oders_gia_discount);

        btn_checkout_Oders_PlaceOrder = findViewById(R.id.checkout_Oders_PlaceOrder);
        btn_checkout_Oders_AddItem = findViewById(R.id.checkout_Oders_AddItem);

        discount_checkout_Oders_discout_show = findViewById(R.id.checkout_Oders_discout_show);
        relativeLayout_checkout_Discount = findViewById(R.id.checkout_Discount);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkout_Oders_PlaceOrder:
                BuyFoodDao buyFoodDao = new BuyFoodDao();
                buyFoodDao.AddBuyFood(buyFood,this,idOrderFood);
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkout_Oders_AddItem:
                Intent intent = new Intent(Checkout_Oders.this,Food_Details_Menu.class);
                startActivity(intent);
                break;
            case R.id.checkout_Oders_discout_show:
                Intent intent1 = new Intent(Checkout_Oders.this,Food_Detail_Discount.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("key_Total",Total);
                bundle.putInt("key_soluongSP",soluongSP);
                bundle.putString("key_idFood",idFood);
                bundle.putString("key_orderFood",idOrderFood);

                intent1.putExtra("key_bundle",bundle);
                startActivity(intent1);
                break;
        }
    }
}