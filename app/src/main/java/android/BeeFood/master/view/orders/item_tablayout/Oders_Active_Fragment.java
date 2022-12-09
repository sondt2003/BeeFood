package android.BeeFood.master.view.orders.item_tablayout;

import android.BeeFood.master.model.BuyFood;
import android.BeeFood.master.view.orders.adapter.Adapter_RecyclerView_Active;
import android.BeeFood.master.view.orders.model.Oders_Object;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.BeeFood.master.R;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Oders_Active_Fragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private RecyclerView recyclerView_fragment_oder_active_recyclerView;
    private LinearLayout empty_fragment_oder_active_empty;

    private Adapter_RecyclerView_Active mAdapter_recyclerView_active;
    private ArrayList<Oders_Object> mArrayList = new ArrayList<>();



    public Oders_Active_Fragment() {
        // Required empty public constructor
    }

    public static Oders_Active_Fragment newInstance() {
        Oders_Active_Fragment fragment = new Oders_Active_Fragment();
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
        return inflater.inflate(R.layout.fragment_oders_active, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anhXa(view);


        mAdapter_recyclerView_active = new Adapter_RecyclerView_Active(getActivity());
        db.collection("buyfood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
                        String email = sharedPref.getString("email", "");
                        ArrayList<BuyFood> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getData().get("status").toString().equalsIgnoreCase("dangDatHang") && email.equalsIgnoreCase(document.getData().get("emailuser").toString())){
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
                        }
                        mAdapter_recyclerView_active.setData(list);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                        recyclerView_fragment_oder_active_recyclerView.setLayoutManager(layoutManager);
                        recyclerView_fragment_oder_active_recyclerView.setAdapter(mAdapter_recyclerView_active);

                        if (list.isEmpty()){
                            empty_fragment_oder_active_empty.setVisibility(View.VISIBLE);
                        }else{
                            empty_fragment_oder_active_empty.setVisibility(View.INVISIBLE);
                        }
                    }
                });




    }

    public void anhXa(View view){
        recyclerView_fragment_oder_active_recyclerView = view.findViewById(R.id.fragment_oder_active_recyclerView);
        empty_fragment_oder_active_empty = view.findViewById(R.id.fragment_oder_active_empty);

    }
}