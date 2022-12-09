package android.BeeFood.master.controller.Dao;

import android.BeeFood.master.model.OderFood;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OderFoodDao {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String getEmail(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        return email;
    }

    public boolean AddBuyFood(OderFood oderFood, Context context) {
        Map<String, Object> oderFoods = new HashMap<>();
        oderFoods.put("idfood", oderFood.getIdFood());
        oderFoods.put("emailfood", oderFood.getEmailFood());
        oderFoods.put("priceoderfood", oderFood.getPriceOderFood());
        oderFoods.put("khoangcach", oderFood.getKhoangcach());
        oderFoods.put("soluong", oderFood.getAmountoffood());
        db.collection("oderfood")
                .add(oderFoods)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Mua Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Mua Sản Phẩm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                });
        return true;
    }

    public boolean updateFood(OderFood oderFood, Context context, String email) {
        Map<String, Object> oderFoods = new HashMap<>();
        oderFoods.put("idfood", oderFood.getIdFood());
        oderFoods.put("emailfood", oderFood.getEmailFood());
        oderFoods.put("priceoderfood", oderFood.getPriceOderFood());
        oderFoods.put("khoangcach", oderFood.getKhoangcach());
        oderFoods.put("soluong", oderFood.getAmountoffood());
        db.collection("oderfood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                    DocumentReference washingtonRef = db.collection("food").document(document.getId());
                                    washingtonRef
                                            .update(oderFoods)
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

    public void getBuyFood() {
        db.collection("oderfood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<OderFood> oderFoods = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                oderFoods.add(new OderFood(
                                        document.getData().get("idfood").toString(),
                                        document.getData().get("emailfood").toString(),
                                        document.getData().get("priceoderfood").toString(),
                                        document.getData().get("khoangcach").toString(),
                                        document.getData().get("soluong").toString()));
                            }
                        }
                    }
                });
    }
}
