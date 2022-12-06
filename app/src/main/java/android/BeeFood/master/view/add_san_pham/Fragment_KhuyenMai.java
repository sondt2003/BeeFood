package android.BeeFood.master.view.add_san_pham;

import android.BeeFood.master.model.khuyenMai;
import android.BeeFood.master.model.loaiFood;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_KhuyenMai extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RecyclerView recyclerView_addSanPham_KhuyenMai_list;
    Button btn_addSanPham_KhuyenMai_Add;
    Adapter_KhuyenMai adapter_khuyenMai;

    public Fragment_KhuyenMai() {
        // Required empty public constructor
    }

    public static Fragment_KhuyenMai newInstance() {
        Fragment_KhuyenMai fragment = new Fragment_KhuyenMai();
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
        return inflater.inflate(R.layout.fragment__khuyen_mai, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anhXa(view);

//        ArrayList<khuyenMai> khuyenMais = new ArrayList<>();
//
//        khuyenMais.add(new khuyenMai("1000","10000","20000","8"));
//        khuyenMais.add(new khuyenMai("1000","10000","20000","0"));

        loadList();


        btn_addSanPham_KhuyenMai_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_khuyenmai_add);

                EditText edt_dialog_khuyenMai_soTienGiam = dialog.findViewById(R.id.dialog_khuyenMai_soTienGiam);
                EditText edt_dialog_khuyenMai_Min = dialog.findViewById(R.id.dialog_khuyenMai_Min);
                EditText edt_dialog_khuyenMai_Max = dialog.findViewById(R.id.dialog_khuyenMai_Max);
                EditText edt_dialog_khuyenMai_soLuong = dialog.findViewById(R.id.dialog_khuyenMai_soLuong);
                Button btn_dialog_khuyenMai_cancel = dialog.findViewById(R.id.dialog_khuyenMai_cancel);
                Button btn_dialog_khuyenMai_Add = dialog.findViewById(R.id.dialog_khuyenMai_Add);

                btn_dialog_khuyenMai_Add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
                        String email = sharedPref.getString("email", "");

                        Map<String, Object> khuyenMai = new HashMap<>();
                        khuyenMai.put("email", email);
                        khuyenMai.put("giam", edt_dialog_khuyenMai_soTienGiam.getText().toString().trim());
                        khuyenMai.put("max", edt_dialog_khuyenMai_Max.getText().toString().trim());
                        khuyenMai.put("min", edt_dialog_khuyenMai_Min.getText().toString().trim());
                        khuyenMai.put("soluong", edt_dialog_khuyenMai_soLuong.getText().toString().trim());
                        db.collection("phieugiamgia")
                                .add(khuyenMai)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(getContext(), "Thêm khuyến mại thành công", Toast.LENGTH_SHORT).show();
                                        loadList();
                                        dialog.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "Thêm khuyến mại Thất Bại", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });


                btn_dialog_khuyenMai_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    public void anhXa(View v){
        recyclerView_addSanPham_KhuyenMai_list = v.findViewById(R.id.addSanPham_KhuyenMai_list);
        btn_addSanPham_KhuyenMai_Add = v.findViewById(R.id.addSanPham_KhuyenMai_Add);
    }

    public void loadList(){
        db.collection("phieugiamgia")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<khuyenMai> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    list.add(new khuyenMai(document.getId(),document.getData().get("giam").toString(),document.getData().get("min").toString(),document.getData().get("max").toString(),document.getData().get("soluong").toString()));
                                } catch (Exception e) {

                                }
                            }
                        }
                        adapter_khuyenMai = new Adapter_KhuyenMai(getContext());
                        adapter_khuyenMai.setData(list);

                        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                        recyclerView_addSanPham_KhuyenMai_list.setLayoutManager(manager);
                        recyclerView_addSanPham_KhuyenMai_list.setAdapter(adapter_khuyenMai);
                    }
                });
    }
}