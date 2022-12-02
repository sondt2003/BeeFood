package android.BeeFood.master.View.Orders.item_tablayout;

import android.BeeFood.master.View.Orders.adapter.Adapter_RecyclerView_Active;
import android.BeeFood.master.View.Orders.model.Oders_Object;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.BeeFood.master.R;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class Oders_Active_Fragment extends Fragment {

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
        mAdapter_recyclerView_active.setData(mArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView_fragment_oder_active_recyclerView.setLayoutManager(layoutManager);
        recyclerView_fragment_oder_active_recyclerView.setAdapter(mAdapter_recyclerView_active);

    }

    public void anhXa(View view){
        recyclerView_fragment_oder_active_recyclerView = view.findViewById(R.id.fragment_oder_active_recyclerView);
        empty_fragment_oder_active_empty = view.findViewById(R.id.fragment_oder_active_empty);
    }
}