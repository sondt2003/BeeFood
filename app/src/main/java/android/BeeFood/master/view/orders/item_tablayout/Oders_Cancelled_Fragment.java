package android.BeeFood.master.view.orders.item_tablayout;

import android.BeeFood.master.view.orders.adapter.Adapter_RecyclerView_Cancelled;
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

import java.util.ArrayList;


public class Oders_Cancelled_Fragment extends Fragment {

    private RecyclerView recyclerView_fragment_orders_cancelled_recyclerView;
    private ArrayList<Oders_Object> mArrayList = new ArrayList<>();
    private Adapter_RecyclerView_Cancelled mAdapter_recyclerView_cancelled;


    public Oders_Cancelled_Fragment() {
        // Required empty public constructor
    }


    public static Oders_Cancelled_Fragment newInstance() {
        Oders_Cancelled_Fragment fragment = new Oders_Cancelled_Fragment();
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
        return inflater.inflate(R.layout.fragment_oders_cancelled, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);

        mAdapter_recyclerView_cancelled = new Adapter_RecyclerView_Cancelled(getActivity());
        mAdapter_recyclerView_cancelled.setData(mArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView_fragment_orders_cancelled_recyclerView.setLayoutManager(layoutManager);
        recyclerView_fragment_orders_cancelled_recyclerView.setAdapter(mAdapter_recyclerView_cancelled);
    }

    public void anhXa(View view) {
        recyclerView_fragment_orders_cancelled_recyclerView = view.findViewById(R.id.fragment_orders_cancelled_recyclerView);
    }
}