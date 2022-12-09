package android.BeeFood.master.view.orders.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.model.BuyFood;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.food_details.Add_To_Basket;
import android.BeeFood.master.view.orders.model.Oders_Object;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
