package android.BeeFood.master.view.orders.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.model.BuyFood;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.food_details.Add_To_Basket;
import android.BeeFood.master.view.orders.model.Oders_Object;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Adapter_RecyclerView_Completed extends RecyclerView.Adapter<Adapter_RecyclerView_Completed.UserViewHolder>{

    private Context mContext;
    private ArrayList<BuyFood> mArrayList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Adapter_RecyclerView_Completed(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oders_item_completed,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        BuyFood object = mArrayList.get(position);
        if (object == null) return;

        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Food food = new Food();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (object.getIdFood().equalsIgnoreCase(document.getId())){
                                    food = new Food(document.getData().get("namefood").toString(),
                                            document.getData().get("price").toString(),
                                            document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(),
                                            document.getData().get("email").toString(),
                                            "Chưa có id",
                                            document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(),
                                            document.getData().get("describle").toString());
                                }
                            }
                        }
                        Glide.with(mContext).load(food.getUrl()).into(holder.img_orders_item_completed_avt);
                        holder.tv_orders_item_completed_name.setText(food.getName());
                    }
                });

        holder.tv_orders_item_completed_soLuong.setText(object.getAmountofood()+" items");
        holder.tv_orders_item_completed_KhoangCach.setText(object.getKhoangcach()+" km");
        holder.tv_orders_item_completed_GiaTien.setText(object.getPriceOderFood() + " vnd");
        if (object.getStatus().equalsIgnoreCase("thanhCong")){
            holder.tv_orders_item_completed_status.setText("Completed");
        }

        holder.btn_orders_item_completed_Btn_Leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Đang phát triển", Toast.LENGTH_SHORT).show();
//                showDialog(object);
            }
        });


        holder.btn_orders_item_completed_Btn_OderAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Add_To_Basket.class);
                intent.putExtra("key_idFood",object.getIdFood());
                mContext.startActivity(intent);
            }
        });
    }
    public void showDialog(BuyFood ob){
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_shipper_thongtin);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if (window  == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);

        ImageView img_dialog_shipper_thongTin_avtFood,img_dialog_shipper_thongTin_avtUser;
        TextView tv_dialog_shipper_thongTin_nameFood,tv_dialog_shipper_thongTin_nameUser,tv_dialog_shipper_thongTin_soLuong,tv_dialog_shipper_thongTin_giaTien,
                tv_dialog_shipper_thongTin_sdt,tv_dialog_shipper_thongTin_diaChiFood,tv_dialog_shipper_thongTin_diaChiUser,tv_dialog_shipper_thongTin_phiShip;
        Button btn_dialog_shipper_thongTin_complete;

        img_dialog_shipper_thongTin_avtFood = dialog.findViewById(R.id.dialog_shipper_thongTin_avtFood);
        img_dialog_shipper_thongTin_avtUser = dialog.findViewById(R.id.dialog_shipper_thongTin_avtUser);
        tv_dialog_shipper_thongTin_nameFood = dialog.findViewById(R.id.dialog_shipper_thongTin_nameFood);
        tv_dialog_shipper_thongTin_nameUser = dialog.findViewById(R.id.dialog_shipper_thongTin_nameUser);
        tv_dialog_shipper_thongTin_soLuong = dialog.findViewById(R.id.dialog_shipper_thongTin_soLuong);
        tv_dialog_shipper_thongTin_giaTien = dialog.findViewById(R.id.dialog_shipper_thongTin_giaTien);
        tv_dialog_shipper_thongTin_sdt = dialog.findViewById(R.id.dialog_shipper_thongTin_sdt);
        tv_dialog_shipper_thongTin_diaChiFood = dialog.findViewById(R.id.dialog_shipper_thongTin_diaChiFood);
        tv_dialog_shipper_thongTin_diaChiUser = dialog.findViewById(R.id.dialog_shipper_thongTin_diaChiUser);
        tv_dialog_shipper_thongTin_phiShip = dialog.findViewById(R.id.dialog_shipper_thongTin_phiShip);
        btn_dialog_shipper_thongTin_complete = dialog.findViewById(R.id.dialog_shipper_thongTin_complete);


        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Food food = new Food();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (ob.getIdFood().equalsIgnoreCase(document.getId())){
                                    food = new Food(document.getData().get("namefood").toString(),
                                            document.getData().get("price").toString(),
                                            document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(),
                                            document.getData().get("email").toString(),
                                            "Chưa có id",
                                            document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(),
                                            document.getData().get("describle").toString());
                                }
                            }
                        }
                        Glide.with(mContext).load(food.getUrl()).into(img_dialog_shipper_thongTin_avtFood);
                        tv_dialog_shipper_thongTin_nameFood.setText(food.getName());
                        tv_dialog_shipper_thongTin_diaChiFood.setText("địa chỉ: " + food.getAddress());
                    }
                });
        tv_dialog_shipper_thongTin_soLuong.setText(ob.getAmountofood() + " items");
        tv_dialog_shipper_thongTin_giaTien.setText(ob.getPriceOderFood() + " vnd");


        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                UserChiTiet user = new UserChiTiet();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i("duy_test","out : " + document.getData().get("email").toString());
                                if (document.getData().get("email").toString().equalsIgnoreCase(ob.getEmailUser())){
                                    Glide.with(mContext).load(document.getData().get("ImageUrl").toString()).into(img_dialog_shipper_thongTin_avtUser);
                                    tv_dialog_shipper_thongTin_nameUser.setText(document.getData().get("fullname").toString());
                                    tv_dialog_shipper_thongTin_sdt.setText("sdt: 0123456789");
                                    Log.i("duy_test",document.getData().get("email").toString());
                                    tv_dialog_shipper_thongTin_diaChiUser.setText("địa chỉ: " + document.getData().get("address").toString());
//                                            user = new UserChiTiet(document.getData().get("email").toString(),document.getData().get("fullname").toString(),document.getData().get("gender").toString(), document.getData().get("dateofbirth").toString(),document.getData().get("ImageUrl").toString(),document.getData().get("address").toString(),document.getData().get("pin").toString());
                                }
                            }
                        }

                    }
                });




        btn_dialog_shipper_thongTin_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference washingtonRef = db.collection("buyfood").document(ob.getIdBuyFood());
                washingtonRef
                        .update("status", "thanhCong")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(mContext, "Hoàn thành", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(mContext, "Thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });

                db.collection("buyfood")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                ArrayList<BuyFood> list = new ArrayList<>();
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if(document.getData().get("status").toString().equalsIgnoreCase("choVanChuyen")){
                                            list.add(new BuyFood(
                                                    document.getId(),
                                                    document.getData().get("idfood").toString(),
                                                    document.getData().get("emailuser").toString(),
                                                    document.getData().get("emailfood").toString(),
                                                    document.getData().get("amountofood").toString(),
                                                    document.getData().get("priceOderFood").toString(),
                                                    "1.8",  //chưa có khoảng cách
                                                    document.getData().get("status").toString()));
                                        }

                                    }
                                }
                                setData(list);
                            }
                        });
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void setData(ArrayList<BuyFood> mArrayList){
        this.mArrayList = mArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mArrayList != null )return mArrayList.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_orders_item_completed_avt;
        private TextView tv_orders_item_completed_name,tv_orders_item_completed_soLuong
                ,tv_orders_item_completed_GiaTien,tv_orders_item_completed_status,tv_orders_item_completed_KhoangCach;
        private Button btn_orders_item_completed_Btn_Leave,btn_orders_item_completed_Btn_OderAgain;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_orders_item_completed_avt = itemView.findViewById(R.id.orders_item_completed_avt);
            tv_orders_item_completed_name = itemView.findViewById(R.id.orders_item_completed_name);
            tv_orders_item_completed_soLuong = itemView.findViewById(R.id.orders_item_completed_soLuong);
            tv_orders_item_completed_KhoangCach = itemView.findViewById(R.id.orders_item_completed_KhoangCach);
            tv_orders_item_completed_GiaTien = itemView.findViewById(R.id.orders_item_completed_GiaTien);
            tv_orders_item_completed_status = itemView.findViewById(R.id.orders_item_completed_status);
            btn_orders_item_completed_Btn_Leave = itemView.findViewById(R.id.orders_item_completed_Btn_Leave);
            btn_orders_item_completed_Btn_OderAgain = itemView.findViewById(R.id.orders_item_completed_Btn_OderAgain);

        }
    }
}
