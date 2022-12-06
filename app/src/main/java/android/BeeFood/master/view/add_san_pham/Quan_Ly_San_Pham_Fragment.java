package android.BeeFood.master.view.add_san_pham;

import android.BeeFood.master.model.Food;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Quan_Ly_San_Pham_Fragment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
//        mArrayList.add(new Food("NAM", "", "", "", "", "", "", "", ""));
        SharedPreferences sharedPref = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");

        mAdapter_qlsp = new Adapter_QLSP(getActivity());
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Food> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (email.equals(document.getData().get("email").toString())) {
                                    list.add(new Food(document.getData().get("namefood").toString(),
                                            document.getData().get("price").toString(),
                                            document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(),
                                            document.getData().get("email").toString(),
                                            "Chưa có id",
                                            document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(),
                                            document.getData().get("describle").toString()));
                                }

                            }
                            mAdapter_qlsp.setData(list);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            recyclerView_Quan_Ly_San_Pham_RecyclerView.setLayoutManager(layoutManager);
                            recyclerView_Quan_Ly_San_Pham_RecyclerView.setAdapter(mAdapter_qlsp);
                        }
                    }
                });




    }

    public void anhXa(View view) {
        recyclerView_Quan_Ly_San_Pham_RecyclerView = view.findViewById(R.id.Quan_Ly_San_Pham_RecyclerView);

    }
}