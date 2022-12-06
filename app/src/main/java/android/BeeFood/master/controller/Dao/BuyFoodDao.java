package android.BeeFood.master.controller.Dao;

import android.BeeFood.master.model.BuyFood;
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

public class BuyFoodDao {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String getEmail(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        return email;
    }
    public boolean AddBuyFood(BuyFood buyFood, Context context) {
        Map<String, Object> BuyFoods = new HashMap<>();
        BuyFoods.put("idfood", buyFood.getIdFood());
        BuyFoods.put("emailuser", buyFood.getEmailUser());
        BuyFoods.put("emailfood", buyFood.getEmailFood());
        BuyFoods.put("amountofood", buyFood.getAmountofood());
        BuyFoods.put("priceOderFood", buyFood.getPriceOderFood());
        BuyFoods.put("khoangcach", buyFood.getKhoangcach());
        BuyFoods.put("status", buyFood.getStatus());
        db.collection("buyfood")
                .add(BuyFoods)
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

    public boolean updateFood(String status, Context context, String idBuyFood) {

        DocumentReference washingtonRef = db.collection("buyfood").document(idBuyFood);
        washingtonRef
                .update("status", status)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Hủy thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Hủy thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
        return true;
    }

    public void getBuyFood() {
        db.collection("buyfood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<BuyFood> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(new BuyFood(
                                        document.getData().get("idfood").toString(),
                                        document.getData().get("emailuser").toString(),
                                        document.getData().get("emailfood").toString(),
                                        document.getData().get("amountofood").toString(),
                                        document.getData().get("priceOderFood").toString(),
                                        document.getData().get("khoangcach").toString(),
                                        document.getData().get("status").toString()));
                                Log.i("SONDT",list.size()+"");
                            }
                        }
                    }
                });
    }
}
