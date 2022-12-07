package android.BeeFood.master.view.orders.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.BuyFoodDao;
import android.BeeFood.master.model.BuyFood;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.orders.model.Oders_Object;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Adapter_RecyclerView_Active extends RecyclerView.Adapter<Adapter_RecyclerView_Active.UserViewHolder>{

    private Context mContext;
    private ArrayList<BuyFood> mArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public Adapter_RecyclerView_Active(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oders_item_active,parent,false);
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
                        Glide.with(mContext).load(food.getUrl()).into(holder.img_orders_item_active_avt);
                        holder.tv_orders_item_active_name.setText(food.getName());
                    }
                });


        holder.tv_orders_item_active_soLuong.setText(object.getAmountofood()+" items");
        holder.tv_orders_item_active_KhoangCach.setText(object.getKhoangcach()+" km");
        holder.tv_orders_item_active_GiaTien.setText(object.getPriceOderFood() + " vnd");

        holder.btn_orders_item_active_btn_Track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Đang phát triển", Toast.LENGTH_SHORT).show();
            }
        });


        holder.btn_orders_item_active_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Huỷ");
                builder.setMessage("Bạn có chắc muốn hủy đơn ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BuyFoodDao buyFoodDao = new BuyFoodDao();
                        buyFoodDao.updateFood("daHuy",mContext,object.getIdBuyFood());

                        db.collection("buyfood")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        ArrayList<BuyFood> list = new ArrayList<>();
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                if(document.getData().get("status").toString().equalsIgnoreCase("chuaThanhToan")){
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
                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();
            }
        });
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

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_orders_item_active_avt;
        private TextView tv_orders_item_active_name,tv_orders_item_active_soLuong
                ,tv_orders_item_active_GiaTien,tv_orders_item_active_status,tv_orders_item_active_KhoangCach;
        private Button btn_orders_item_active_btn_cancel,btn_orders_item_active_btn_Track;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_orders_item_active_avt = itemView.findViewById(R.id.orders_item_active_avt);
            tv_orders_item_active_name = itemView.findViewById(R.id.orders_item_active_name);
            tv_orders_item_active_soLuong = itemView.findViewById(R.id.orders_item_active_soLuong);
            tv_orders_item_active_KhoangCach = itemView.findViewById(R.id.orders_item_active_KhoangCach);
            tv_orders_item_active_GiaTien = itemView.findViewById(R.id.orders_item_active_GiaTien);
            tv_orders_item_active_status = itemView.findViewById(R.id.orders_item_active_status);
            btn_orders_item_active_btn_cancel = itemView.findViewById(R.id.orders_item_active_btn_cancel);
            btn_orders_item_active_btn_Track = itemView.findViewById(R.id.orders_item_active_btn_Track);


        }
    }
}
