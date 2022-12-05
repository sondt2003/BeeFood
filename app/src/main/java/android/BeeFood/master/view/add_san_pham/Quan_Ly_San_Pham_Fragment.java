package android.BeeFood.master.view.add_san_pham;

import android.BeeFood.master.model.Food;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.BeeFood.master.R;

import java.util.ArrayList;

public class Quan_Ly_San_Pham_Fragment extends Fragment {

    private RecyclerView recyclerView_Quan_Ly_San_Pham_RecyclerView;
    private ArrayList<Food> mArrayList = new ArrayList<>();
    private Adapter_QLSP mAdapter_qlsp;



    public Quan_Ly_San_Pham_Fragment() {
        // Required empty public constructor
    }

    public static Quan_Ly_San_Pham_Fragment newInstance() {
        Quan_Ly_San_Pham_Fragment fragment = new Quan_Ly_San_Pham_Fragment();
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
        return inflater.inflate(R.layout.fragment_quan_ly_san_pham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anhXa(view);

        mAdapter_qlsp = new Adapter_QLSP(getActivity());
        mAdapter_qlsp.setData(mArrayList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView_Quan_Ly_San_Pham_RecyclerView.setLayoutManager(layoutManager);
        recyclerView_Quan_Ly_San_Pham_RecyclerView.setAdapter(mAdapter_qlsp);

    }

    public void anhXa(View view) {
        recyclerView_Quan_Ly_San_Pham_RecyclerView = view.findViewById(R.id.Quan_Ly_San_Pham_RecyclerView);

    }
}