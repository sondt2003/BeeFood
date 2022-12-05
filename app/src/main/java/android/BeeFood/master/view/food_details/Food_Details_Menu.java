package android.BeeFood.master.view.food_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.food_details.adapter.Adapter_RecyclerView_FoodDetail_ForYou;
import android.BeeFood.master.view.food_details.adapter.Adapter_RecyclerView_FoodDetail_menu_and_drink;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Food_Details_Menu extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView_Food_Detail_Menu_forYou,recyclerView_Food_Detail_Menu_menu,recyclerView_Food_Detail_Menu_drink;

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
        setSupportActionBar(toolbar_Food_Detail_Menu_toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        adapter_recyclerView_foodDetail_forYou = new Adapter_RecyclerView_FoodDetail_ForYou(Food_Details_Menu.this);
        adapter_recyclerView_foodDetail_forYou.setData(mArrayList);
        LinearLayoutManager layoutManagerForYou = new LinearLayoutManager(Food_Details_Menu.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_Food_Detail_Menu_forYou.setLayoutManager(layoutManagerForYou);
        recyclerView_Food_Detail_Menu_forYou.setAdapter(adapter_recyclerView_foodDetail_forYou);


        adapter_recyclerView_foodDetail_menu_and_drink = new Adapter_RecyclerView_FoodDetail_menu_and_drink(Food_Details_Menu.this);
        adapter_recyclerView_foodDetail_menu_and_drink.setData(mArrayList);
        LinearLayoutManager layoutManagerMenu = new LinearLayoutManager(Food_Details_Menu.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_Food_Detail_Menu_menu.setLayoutManager(layoutManagerMenu);
        recyclerView_Food_Detail_Menu_menu.setAdapter(adapter_recyclerView_foodDetail_menu_and_drink);

        LinearLayoutManager layoutManagerDrink = new LinearLayoutManager(Food_Details_Menu.this,LinearLayoutManager.VERTICAL,false);
        recyclerView_Food_Detail_Menu_drink.setLayoutManager(layoutManagerDrink);
        recyclerView_Food_Detail_Menu_drink .setAdapter(adapter_recyclerView_foodDetail_menu_and_drink);

        btn_Food_Detail_Menu_basket.setOnClickListener(this);

    }

    public void anhXa() {
        toolbar_Food_Detail_Menu_toolbar = findViewById(R.id.Food_Detail_Menu_Toolbar);

        recyclerView_Food_Detail_Menu_forYou = findViewById(R.id.recyclerView_Food_Detail_Menu_forYou);
        recyclerView_Food_Detail_Menu_menu = findViewById(R.id.recyclerView_Food_Detail_Menu_menu);
        recyclerView_Food_Detail_Menu_drink = findViewById(R.id.recyclerView_Food_Detail_Menu_drink);


        btn_Food_Detail_Menu_basket = findViewById(R.id.Food_Detail_Menu_basket);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Food_Detail_Menu_basket:
                Intent intent = new Intent(Food_Details_Menu.this,Checkout_Oders.class);
                startActivity(intent);
                break;
        }
    }
}