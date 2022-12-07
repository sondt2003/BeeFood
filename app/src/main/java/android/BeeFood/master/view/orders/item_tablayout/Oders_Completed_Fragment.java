package android.BeeFood.master.view.orders.item_tablayout;

import android.BeeFood.master.view.orders.adapter.Adapter_RecyclerView_Completed;
import android.BeeFood.master.view.orders.model.Oders_Object;
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

import java.util.ArrayList;


public class Oders_Completed_Fragment extends Fragment {

    private RecyclerView recyclerView_fragment_orders_completed_recyclerView;
    private ArrayList<Oders_Object> mArrayList = new ArrayList<>();
    private Adapter_RecyclerView_Completed mAdapter_recyclerView_completed;
    private LinearLayout empty_fragment_oder_completed_empty;

    public Oders_Completed_Fragment() {
        // Required empty public constructor
    }


    public static Oders_Completed_Fragment newInstance() {
        Oders_Completed_Fragment fragment = new Oders_Completed_Fragment();
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
        return inflater.inflate(R.layout.fragment_oders_completed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);

        mAdapter_recyclerView_completed = new Adapter_RecyclerView_Completed(getActivity());
        mAdapter_recyclerView_completed.setData(mArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView_fragment_orders_completed_recyclerView.setLayoutManager(layoutManager);
        recyclerView_fragment_orders_completed_recyclerView.setAdapter(mAdapter_recyclerView_completed);
    }

    public void anhXa(View view) {
        empty_fragment_oder_completed_empty = view.findViewById(R.id.fragment_oder_completed_empty);
        recyclerView_fragment_orders_completed_recyclerView = view.findViewById(R.id.fragment_orders_completed_recyclerView);
    }
}