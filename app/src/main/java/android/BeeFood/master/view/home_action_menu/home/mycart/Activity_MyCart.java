package android.BeeFood.master.view.home_action_menu.home.mycart;

import android.BeeFood.master.R;
import android.BeeFood.master.view.object.MyCart;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Activity_MyCart extends AppCompatActivity {

    private ImageView img_home_ActionMenu_MyCart_Back;
    private RecyclerView recyclerView_home_ActionMenu_MyCart_listCart;
    private LinearLayout layout_home_ActionMenu_MyCart_Empty;

    private ArrayList<MyCart> lis_myCart = new ArrayList<>();
    private Adapter_MyCart adapter_myCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        AnhXa();

//        lis_myCart.add(new MyCart(R.drawable.avt_test,"Name","2 item","800m","$4.00"));
//        lis_myCart.add(new MyCart(R.drawable.avt_test,"Name","2 item","800m","$4.00"));
//        lis_myCart.add(new MyCart(R.drawable.avt_test,"Name","2 item","800m","$4.00"));
//        lis_myCart.add(new MyCart(R.drawable.avt_test,"Name","2 item","800m","$4.00"));
//        lis_myCart.add(new MyCart(R.drawable.avt_test,"Name","2 item","800m","$4.00"));
//        lis_myCart.add(new MyCart(R.drawable.avt_test,"Name","2 item","800m","$4.00"));
//        lis_myCart.add(new MyCart(R.drawable.avt_test,"Name","2 item","800m","$4.00"));
//        lis_myCart.add(new MyCart(R.drawable.avt_test,"Name","2 item","800m","$4.00"));

        if (lis_myCart.isEmpty()){
            layout_home_ActionMenu_MyCart_Empty.setVisibility(View.VISIBLE);
        }else{
            layout_home_ActionMenu_MyCart_Empty.setVisibility(View.INVISIBLE);
        }

        adapter_myCart = new Adapter_MyCart(Activity_MyCart.this);
        adapter_myCart.setData(lis_myCart);

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
    }

    public  void AnhXa(){
        img_home_ActionMenu_MyCart_Back = findViewById(R.id.home_ActionMenu_MyCart_Back);
        recyclerView_home_ActionMenu_MyCart_listCart = findViewById(R.id.home_ActionMenu_MyCart_listCart);
        layout_home_ActionMenu_MyCart_Empty = findViewById(R.id.home_ActionMenu_MyCart_Empty);
    }
}