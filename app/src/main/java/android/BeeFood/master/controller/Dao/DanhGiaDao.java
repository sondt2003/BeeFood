package android.BeeFood.master.controller.Dao;

import android.BeeFood.master.model.DanhGia;
import android.BeeFood.master.model.User;
import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.HashMap;
import java.util.Map;

public class DanhGiaDao {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String getEmail(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        return email;
    }

    public boolean AddDanhGia(DanhGia danhGia, Context context) {
        Map<String, Object> user = new
                HashMap<>();
        user.put("email", danhGia.getEmail());
        user.put("idfood", danhGia.getIdFood());
        user.put("numberstar", danhGia.getNumberStar());
        user.put("comment", danhGia.getComment());
        user.put("time", danhGia.getTime());
        db.collection("DanhGia")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Cập Nhật Thông Tin Thành Công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Cập Nhật Thông Tin Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                });
        return true;
    }

    public boolean updateDanhGia(DanhGia danhGia, Context context,String email) {
        Map<String, Object> user = new
                HashMap<>();
        user.put("email", danhGia.getEmail());
        user.put("idfood", danhGia.getIdFood());
        user.put("numberstar", danhGia.getNumberStar());
        user.put("comment", danhGia.getComment());
        user.put("time", danhGia.getTime());
        db.collection("DanhGia")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                    DocumentReference washingtonRef = db.collection("users").document(document.getId());
                                    washingtonRef
                                            .update(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(context, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                            });
                                }
                            }
                        }
                    }
                });
        return true;
    }
}
