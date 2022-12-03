package android.BeeFood.master.controller.Dao;

import android.BeeFood.master.model.Food;
import android.BeeFood.master.model.User;
import android.BeeFood.master.view.accountSetup.Screen_Pin_Code;
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

public class DataFirestore {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

     public String getEmail(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        return email;
    }

    public boolean AddDataUser(User users, Context context) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", users.getEmail());
        user.put("fullname", users.getUserChiTiet().getFullname());
        user.put("nickname", users.getName());
        user.put("dateofbirth", users.getUserChiTiet().getDate());
        user.put("gender", users.getUserChiTiet().getGender());
        user.put("ImageUrl", users.getUserChiTiet().getUrl());
        user.put("pin", users.getUserChiTiet().getPin());
        user.put("address", users.getUserChiTiet().getAddress());
        db.collection("users")
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
    public boolean AddDataFood(Food food, Context context) {
        Map<String, Object> user = new HashMap<>();
        user.put("namefood", food.getName());
        user.put("price", food.getPrice());
        user.put("address", food.getAddress());
        user.put("phonenumber", food.getPhoneNumber());
        user.put("email", food.getTenloai());
        user.put("ImageUrl", food.getUrl());
        user.put("tenloai", food.getTenloai());
        user.put("describle", food.getDescrible());
        db.collection("food")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Thêm Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Thêm Sản Phẩm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                });
        return true;
    }
    public boolean updateUser(User users, Context context,String email,String pin) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", users.getEmail());
        user.put("fullname", users.getUserChiTiet().getFullname());
        user.put("nickname", users.getName());
        user.put("dateofbirth", users.getUserChiTiet().getDate());
        user.put("gender", users.getUserChiTiet().getGender());
        user.put("ImageUrl", users.getUserChiTiet().getUrl());
        user.put("pin", users.getUserChiTiet().getPin());
        user.put("address", users.getUserChiTiet().getAddress());
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (email.equalsIgnoreCase(document.getData().get("email").toString())){
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
