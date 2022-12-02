package android.BeeFood.master.View.Food_Details;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Food_Details.Adapter.Adapter_RecyclerView_FoodDetail_ForYou;
import android.BeeFood.master.View.Food_Details.Adapter.Adapter_RecyclerView_FoodDetail_menu_and_drink;
import android.BeeFood.master.View.Object.Food;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Food_Details_Menu extends AppCompatActivity {

    private ImageView img_Food_Detail_Menu_banner;
    private TextView tv_Food_Detail_Menu_name,tv_Food_Detail_Menu_vote,tv_Food_Detail_Menu_vote_reviews
            ,tv_Food_Detail_Menu_khoangCach,tv_Food_Detail_Menu_khoangCach_soTien;
    private RecyclerView recyclerView_Food_Detail_Menu_forYou,recyclerView_Food_Detail_Menu_menu,recyclerView_Food_Detail_Menu_drink;
    private LinearLayout linearLayout_Food_Detail_Menu_vote_show,linearLayout_Food_Detail_Menu_deliveryNow_show,linearLayout_Food_Detail_Menu_offers_show;
    
    private Toolbar toolbar_Food_Detail_Menu_toolbar;

    private Button btn_Food_Detail_Menu_basket;

    private ArrayList<Food> mArrayList  = new ArrayList<>();
    private Adapter_RecyclerView_FoodDetail_ForYou adapter_recyclerView_foodDetail_forYou;
    private Adapter_RecyclerView_FoodDetail_menu_and_drink adapter_recyclerView_foodDetail_menu_and_drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details_menu);
        
        anhXa();
//        setSupportActionBar(toolbar_Food_Detail_Menu_toolbar);
//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter_recyclerView_foodDetail_forYou = new Adapter_RecyclerView_FoodDetail_ForYou(Food_Details_Menu.this);
        adapter_recyclerView_foodDetail_forYou.setData(mArrayList);
        LinearLayoutManager layoutManagerForYou = new LinearLayoutManager(Food_Details_Menu.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_Food_Detail_Menu_forYou.setLayoutManager(layoutManagerForYou);
        recyclerView_Food_Detail_Menu_forYou.setAdapter(adapter_recyclerView_foodDetail_forYou);


        adapter_recyclerView_foodDetail_menu_and_drink = new Adapter_RecyclerView_FoodDetail_menu_and_drink(Food_Details_Menu.this);
        adapter_recyclerView_foodDetail_menu_and_drink.setData(mArrayList);
        LinearLayoutManager layoutManagerMenuDrink = new LinearLayoutManager(Food_Details_Menu.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_Food_Detail_Menu_menu.setLayoutManager(layoutManagerMenuDrink);
        recyclerView_Food_Detail_Menu_menu.setAdapter(adapter_recyclerView_foodDetail_menu_and_drink);

        recyclerView_Food_Detail_Menu_drink.setLayoutManager(layoutManagerMenuDrink);
        recyclerView_Food_Detail_Menu_drink .setAdapter(adapter_recyclerView_foodDetail_menu_and_drink);



    }

    public void anhXa() {
        toolbar_Food_Detail_Menu_toolbar = findViewById(R.id.Food_Detail_Menu_toolbar);

        img_Food_Detail_Menu_banner = findViewById(R.id.Food_Detail_Menu_banner);

        tv_Food_Detail_Menu_name = findViewById(R.id.Food_Detail_Menu_name);
        tv_Food_Detail_Menu_vote = findViewById(R.id.Food_Detail_Menu_vote);
        tv_Food_Detail_Menu_vote_reviews = findViewById(R.id.Food_Detail_Menu_vote_reviews);
        tv_Food_Detail_Menu_khoangCach = findViewById(R.id.Food_Detail_Menu_khoangCach);
        tv_Food_Detail_Menu_khoangCach_soTien = findViewById(R.id.Food_Detail_Menu_khoangCach_soTien);

        recyclerView_Food_Detail_Menu_forYou = findViewById(R.id.recyclerView_Food_Detail_Menu_forYou);
        recyclerView_Food_Detail_Menu_menu = findViewById(R.id.recyclerView_Food_Detail_Menu_menu);
        recyclerView_Food_Detail_Menu_drink = findViewById(R.id.recyclerView_Food_Detail_Menu_drink);

        linearLayout_Food_Detail_Menu_vote_show = findViewById(R.id.linearLayout_Food_Detail_Menu_vote_show);
        linearLayout_Food_Detail_Menu_deliveryNow_show = findViewById(R.id.linearLayout_Food_Detail_Menu_deliveryNow_show);
        linearLayout_Food_Detail_Menu_offers_show = findViewById(R.id.linearLayout_Food_Detail_Menu_offers_show);

        btn_Food_Detail_Menu_basket = findViewById(R.id.Food_Detail_Menu_basket);
    }
}