package android.BeeFood.master.controller.Dao;

import android.BeeFood.master.model.OrdersFood;
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

public class OdersFoodDao {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String getEmail(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        return email;
    }

    public boolean AddDataOderFood(OrdersFood ordersFood, Context context){
        Map<String, Object> ordersFoods = new HashMap<>();
        ordersFoods.put("idfood",ordersFood.getIDFood());
        ordersFoods.put("emailuser",ordersFood.getEmailUser());
        ordersFoods.put("priceoderfood",ordersFood.getPriceOderFood());
        ordersFoods.put("khoangcach",ordersFood.getKhoangCach());
        ordersFoods.put("soluong",ordersFood.getSoLuong());
        ordersFoods.put("addressfood",ordersFood.getAddressFood());
        ordersFoods.put("addressuser",ordersFood.getAddressUser());
        db.collection("oderfood")
                .add(ordersFoods)
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

    public boolean updateOderFood( OrdersFood ordersFood, Context context,String email ){
        Map<String, Object> ordersFoods = new HashMap<>();
        ordersFoods.put("idfood",ordersFood.getIDFood());
        ordersFoods.put("emailuser",ordersFood.getEmailUser());
        ordersFoods.put("priceoderfood",ordersFood.getPriceOderFood());
        ordersFoods.put("khoangcach",ordersFood.getKhoangCach());
        ordersFoods.put("soluong",ordersFood.getSoLuong());
        ordersFoods.put("addressfood",ordersFood.getAddressFood());
        ordersFoods.put("addressuser",ordersFood.getAddressUser());
        db.collection("oderfood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                    DocumentReference washingtonRef = db.collection("oderfood").document(document.getId());
                                    washingtonRef
                                            .update(ordersFoods)
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
