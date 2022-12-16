package android.BeeFood.master.controller.Dao;

import android.BeeFood.master.model.Food;
import android.BeeFood.master.model.History;
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

public class HistoryDao {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public String getEmail(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "");
        return email;
    }
    public boolean AddDataHistory(History history, Context context) {
        Map<String, Object> historys = new HashMap<>();
        historys.put("email", history.getEmail());
        historys.put("idfood", history.getIdFood());
        historys.put("time", history.getDayMonthYear());
        historys.put("status", history.getStatus());
        historys.put("iddanhgia", history.getIdDanhGia());
        db.collection("history")
                .add(historys)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Đã Thêm Lịch Sử", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Thêm Lịch Sử Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                });
        return true;
    }

    public boolean updateHistory(History history, Context context, String email, String pin) {
        Map<String, Object> historys = new HashMap<>();
        historys.put("email", history.getEmail());
        historys.put("idfood", history.getIdFood());
        historys.put("time", history.getDayMonthYear());
        historys.put("status", history.getStatus());
        historys.put("iddanhgia", history.getIdDanhGia());
        db.collection("history")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (email.equalsIgnoreCase(document.getData().get("email").toString())) {
                                    DocumentReference washingtonRef = db.collection("food").document(document.getId());
                                    washingtonRef
                                            .update(historys)
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

    public ArrayList<Food> getHistory() {
        ArrayList<Food> listgetfood = new ArrayList<>();
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Food> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(new Food(document.getData().get("namefood").toString(),
                                        document.getData().get("price").toString(),
                                        document.getData().get("address").toString(),
                                        document.getData().get("phonenumber").toString(),
                                        document.getData().get("email").toString(),
                                        "Chưa có id",
                                        document.getData().get("tenloai").toString(),
                                        document.getData().get("ImageUrl").toString(),
                                        document.getData().get("describle").toString()));
                                Log.i("SONDT",list.size()+"");
                            }
                        }
                    }
                });
        return listgetfood;
    }
}
