package android.BeeFood.master.controller.Dao;

import android.BeeFood.master.model.Food;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
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

public class FoodDao {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Food> listgetfood = new ArrayList<>();

    public String getEmail(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        return email;
    }
    public boolean AddDataFood(Food food, Context context) {
        Map<String, Object> foods = new HashMap<>();
        foods.put("namefood", food.getName());
        foods.put("price", food.getPrice());
        foods.put("address", food.getAddress());
        foods.put("phonenumber", food.getPhoneNumber());
        foods.put("email", food.getTenloai());
        foods.put("ImageUrl", food.getUrl());
        foods.put("tenloai", food.getTenloai());
        foods.put("idloaifood", food.getIdloai());
        foods.put("describle", food.getDescrible());
        db.collection("food")
                .add(foods)
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

    public boolean updateFood(Food food, Context context, String email, String pin) {
        Map<String, Object> foods = new HashMap<>();
        foods.put("namefood", food.getName());
        foods.put("price", food.getPrice());
        foods.put("address", food.getAddress());
        foods.put("phonenumber", food.getPhoneNumber());
        foods.put("email", food.getEmail());
        foods.put("ImageUrl", food.getUrl());
        foods.put("tenloai", food.getTenloai());
        foods.put("idloaifood", food.getIdloai());
        foods.put("describle", food.getDescrible());
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                    DocumentReference washingtonRef = db.collection("food").document(document.getId());
                                    washingtonRef
                                            .update(foods)
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

    public ArrayList<Food> getFood() {
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listgetfood.add(new Food(document.getData().get("namefood").toString(),
                                        document.getData().get("price").toString(),
                                        document.getData().get("address").toString(),
                                        document.getData().get("phonenumber").toString(),
                                        document.getData().get("email").toString(),
                                        "",
                                        document.getData().get("tenloai").toString(),
                                        document.getData().get("ImageUrl").toString(),
                                        document.getData().get("describle").toString()));
                            }

                        }

                    }
                });
        Log.d("SONDT", listgetfood.size()+"get");
        return listgetfood;
    }
}
