package android.BeeFood.master.controller.Dao;

import android.BeeFood.master.model.loaiFood;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

public class LoaiDao {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public boolean addDataLoaiFood(loaiFood loaiFood, Context context) {
        Map<String, Object> loai = new HashMap<>();
        loai.put("NameLoai", loaiFood.getNameloai());
        loai.put("ImageUrl", loaiFood.getUrl());
        db.collection("loaifood")
                .add(loai)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Thêm Loại Thành Công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Thêm Loại Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                });
        return true;
    }
    public void getLoaiFood(Context context) {

        db.collection("loaifood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<loaiFood> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    list.add(new loaiFood(document.getId(), document.getData().get("ImageUrl").toString(),document.getData().get("NameLoai").toString()));
                                } catch (Exception e) {

                                }
                            }
                        }
                    }
                });
    }
}
