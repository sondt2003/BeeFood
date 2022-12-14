package android.BeeFood.master.view.home_action_menu.home;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.FoodDao;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.model.loaiFood;
import android.BeeFood.master.view.accountSetup.Screen_Profile;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.BeeFood.master.view.home_action_menu.home.craving.Activity_craving;
import android.BeeFood.master.view.home_action_menu.home.food.Activity_food;
import android.BeeFood.master.view.home_action_menu.home.more_category.Activity_MoreCategory;
import android.BeeFood.master.view.home_action_menu.home.mycart.Activity_MyCart;
import android.BeeFood.master.view.home_action_menu.home.recommended_for_you.Activity_Recommended;
import android.BeeFood.master.view.home_action_menu.home.special_offers.Activity_Special_offers;
import android.BeeFood.master.view.home_action_menu.home.special_offers.Adapter_Special_offers;
import android.BeeFood.master.view.object.Loai_food;
import android.BeeFood.master.view.onboarding_sign_up_sign_in.MainActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Fragment_home extends Fragment implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences sharedPreferences;

    ImageView img_home_ActionMenu_home_Avt;
    TextView tv_home_ActionMenu_home_Name;

    private EditText edt_home_ActionMenu_home_Craving;
    private ImageView img_home_ActionMenu_home_MyCart;
    private TextView tv_home_ActionMenu_homeSpecial_seeAll;
    private TextView tv_home_ActionMenu_homeRecommended_seeAll;

    private ArrayList<Loai_food> list_FoodType = new ArrayList<>();
    private Adapter_Foodtype adapter_FoodType;
    private GridView gridView_home_ActionMenu_home_FoodType;

    private ArrayList<Food> lis_food;
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

        SharedPreferences sharedPref = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(email.equalsIgnoreCase(document.getData().get("email").toString())){
                                    Glide.with(getActivity()).load(document.getData().get("ImageUrl")).into(img_home_ActionMenu_home_Avt);
                                    tv_home_ActionMenu_home_Name.setText(document.getData().get("fullname").toString());
                                }
                            }
                        }
                    }
                });


        FoodDao foodDao = new FoodDao();
        lis_food =  foodDao.getFood();



        db.collection("loaifood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<loaiFood> list = new ArrayList<>();
                        list.add(new loaiFood("https://play-lh.googleusercontent.com/Gzwk-ZmTIr2ehCXsOj_P95L5k1vE1C-ulMb8oYFQTKOHvnGK8enJgDy8MbwdjRRP3Po","All"));
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    list.add(new loaiFood(document.getId(), document.getData().get("ImageUrl").toString(),document.getData().get("NameLoai").toString()));
                                } catch (Exception e) {

                                }
                            }
                        }
                        adapter_FoodType = new Adapter_Foodtype(getContext(),list,R.layout.home_item_foodtype);
                        gridView_home_ActionMenu_home_FoodType.setAdapter(adapter_FoodType);

                        gridView_home_ActionMenu_home_FoodType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                String sItem = list.get(i).getNameloai();

                                if (sItem.equals("All")){
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
                    }
                });


        lis_bannerSale.add(R.drawable.avt_test);
        lis_bannerSale.add(R.drawable.avt_test);
        lis_bannerSale.add(R.drawable.avt_test);
        lis_bannerSale.add(R.drawable.avt_test);


//
        adapter_recommended = new Adapter_Recommended(getContext());
        adapter_discount = new Adapter_Discount(getContext());

        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Food> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(new Food(document.getId(),document.getData().get("namefood").toString(),
                                        document.getData().get("price").toString(),
                                        document.getData().get("address").toString(),
                                        document.getData().get("phonenumber").toString(),
                                        document.getData().get("email").toString(),
                                        "Chưa có id",
                                        document.getData().get("tenloai").toString(),
                                        document.getData().get("ImageUrl").toString(),
                                        document.getData().get("describle").toString()));
                                if (list.size() >= 5) break;
                            }
                            adapter_special_offers.setData(list);
                            adapter_discount.setData(list);
                            adapter_recommended.setData(list);
                        }
                    }
                });

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView_home_ActionMenu_home_Discout.setLayoutManager(manager);
        recyclerView_home_ActionMenu_home_Discout.setAdapter(adapter_discount);


        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView_home_ActionMenu_home_Recommended.setLayoutManager(manager1);
        recyclerView_home_ActionMenu_home_Recommended.setAdapter(adapter_recommended);


        adapter_special_offers = new Adapter_Special_offers(getContext());
//        adapter_special_offers.setData(lis_bannerSale);

        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView_home_ActionMenu_Special_banner.setLayoutManager(manager2);
        recyclerView_home_ActionMenu_Special_banner.setAdapter(adapter_special_offers);


        edt_home_ActionMenu_home_Craving.setOnClickListener(this);
        img_home_ActionMenu_home_MyCart.setOnClickListener(this);
        tv_home_ActionMenu_homeSpecial_seeAll.setOnClickListener(this);
        tv_home_ActionMenu_homeRecommended_seeAll.setOnClickListener(this);

    }

    public void AnhXa(View view){

        img_home_ActionMenu_home_Avt = view.findViewById(R.id.home_ActionMenu_home_Avt);
        tv_home_ActionMenu_home_Name = view.findViewById(R.id.home_ActionMenu_home_Name);

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