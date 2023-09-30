package android.BeeFood.master.view.home_action_menu.home;

import android.BeeFood.master.R;
import android.BeeFood.master.view.home_action_menu.home.craving.Activity_craving;
import android.BeeFood.master.view.home_action_menu.home.food.Activity_food;
import android.BeeFood.master.view.home_action_menu.home.more_category.Activity_MoreCategory;
import android.BeeFood.master.view.home_action_menu.home.mycart.Activity_MyCart;
import android.BeeFood.master.view.home_action_menu.home.recommended_for_you.Activity_Recommended;
import android.BeeFood.master.view.home_action_menu.home.special_offers.Activity_Special_offers;
import android.BeeFood.master.view.home_action_menu.home.special_offers.Adapter_Special_offers;
import android.BeeFood.master.view.object.Food;
import android.BeeFood.master.view.object.Loai_food;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_home extends Fragment implements View.OnClickListener {

    private EditText edt_home_ActionMenu_home_Craving;
    private ImageView img_home_ActionMenu_home_MyCart;
    private TextView tv_home_ActionMenu_homeSpecial_seeAll;
    private TextView tv_home_ActionMenu_homeRecommended_seeAll;

    private ArrayList<Loai_food> list_FoodType = new ArrayList<>();
    private Adapter_Foodtype adapter_FoodType;
    private GridView gridView_home_ActionMenu_home_FoodType;

    private ArrayList<Food> lis_food = new ArrayList<>();
    private Adapter_Discount adapter_discount;
    private RecyclerView recyclerView_home_ActionMenu_home_Discout;

    private Adapter_Recommended adapter_recommended;
    private RecyclerView recyclerView_home_ActionMenu_home_Recommended;

    private ArrayList<Integer> lis_bannerSale = new ArrayList<>();
    private Adapter_Special_offers adapter_special_offers;
    private RecyclerView recyclerView_home_ActionMenu_Special_banner;

    public Fragment_home() {
    }

    public static Fragment_home newInstance() {
        Fragment_home fragment = new Fragment_home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa(view);

        // bắt buộc 8 item - chuyền vào 7 item - không sửa item cuối
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh miw"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mie"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh mi"));
        list_FoodType.add(new Loai_food(R.drawable.avt_test,"banh m"));

        list_FoodType.add(new Loai_food(R.drawable.avt_test,"More"));


        lis_bannerSale.add(R.drawable.avt_test);
        lis_bannerSale.add(R.drawable.avt_test);
        lis_bannerSale.add(R.drawable.avt_test);
        lis_bannerSale.add(R.drawable.avt_test);

        adapter_FoodType = new Adapter_Foodtype(getContext(),list_FoodType,R.layout.home_item_foodtype);
        gridView_home_ActionMenu_home_FoodType.setAdapter(adapter_FoodType);

        gridView_home_ActionMenu_home_FoodType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String sItem = list_FoodType.get(i).getName();

                if (sItem.equals("More")){
                    Intent intent = new Intent(getActivity(), Activity_MoreCategory.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), sItem, Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getContext(), sItem, Toast.LENGTH_SHORT).show();

                Intent intent_toFood = new Intent(getActivity(), Activity_food.class);
                intent_toFood.putExtra("key_FoodType",sItem);
                intent_toFood.putExtra("key_chkSpecialMore",false);
                startActivity(intent_toFood);
            }
        });


        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));
        lis_food.add(new Food(R.drawable.avt_test,"Name",1.8,4.8,1,6.00,2.00));


        adapter_discount = new Adapter_Discount(getContext());
        adapter_discount.setData(lis_food);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView_home_ActionMenu_home_Discout.setLayoutManager(manager);
        recyclerView_home_ActionMenu_home_Discout.setAdapter(adapter_discount);


        adapter_recommended = new Adapter_Recommended(getContext());
        adapter_recommended.setData(lis_food);

        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView_home_ActionMenu_home_Recommended.setLayoutManager(manager1);
        recyclerView_home_ActionMenu_home_Recommended.setAdapter(adapter_recommended);


        adapter_special_offers = new Adapter_Special_offers(getContext());
        adapter_special_offers.setData(lis_bannerSale);

        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView_home_ActionMenu_Special_banner.setLayoutManager(manager2);
        recyclerView_home_ActionMenu_Special_banner.setAdapter(adapter_special_offers);


        edt_home_ActionMenu_home_Craving.setOnClickListener(this);
        img_home_ActionMenu_home_MyCart.setOnClickListener(this);
        tv_home_ActionMenu_homeSpecial_seeAll.setOnClickListener(this);
        tv_home_ActionMenu_homeRecommended_seeAll.setOnClickListener(this);

    }

    public void AnhXa(View view){
        edt_home_ActionMenu_home_Craving = view.findViewById(R.id.home_ActionMenu_home_Craving);
        img_home_ActionMenu_home_MyCart = view.findViewById(R.id.home_ActionMenu_home_MyCart);
        tv_home_ActionMenu_homeSpecial_seeAll = view.findViewById(R.id.home_ActionMenu_homeSpecial_seeAll);
        tv_home_ActionMenu_homeRecommended_seeAll = view.findViewById(R.id.home_ActionMenu_homeRecommended_seeAll);

        gridView_home_ActionMenu_home_FoodType = view.findViewById(R.id.home_ActionMenu_home_FoodType);
        recyclerView_home_ActionMenu_home_Discout = view.findViewById(R.id.home_ActionMenu_home_Discount);
        recyclerView_home_ActionMenu_home_Recommended = view.findViewById(R.id.home_ActionMenu_home_Recommended);
        recyclerView_home_ActionMenu_Special_banner = view.findViewById(R.id.home_ActionMenu_Special_banner);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_ActionMenu_home_MyCart:
                onClick_MyCart();
                break;
            case R.id.home_ActionMenu_home_Craving:
                onClick_Craving();
                break;
            case R.id.home_ActionMenu_homeSpecial_seeAll:
                onClick_seeAllSpecial();
                break;
            case R.id.home_ActionMenu_homeRecommended_seeAll:
                onClick_seeAllRecommended();
                break;
        }
    }

    public void onClick_MyCart(){
        Intent intent = new Intent(getActivity(), Activity_MyCart.class);
        startActivity(intent);
    }

    public void onClick_Craving(){
        Intent intent = new Intent(getActivity(), Activity_craving.class);
        startActivity(intent);
    }

    public void onClick_seeAllRecommended(){
        Intent intent = new Intent(getActivity(), Activity_Recommended.class);
        startActivity(intent);
    }

    public void onClick_seeAllSpecial(){
        Intent intent = new Intent(getActivity(), Activity_Special_offers.class);
        startActivity(intent);
    }
}