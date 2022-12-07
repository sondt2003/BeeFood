package android.BeeFood.master.view.orders.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.BuyFoodDao;
import android.BeeFood.master.model.BuyFood;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.orders.model.Oders_Object;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Adapter_RecyclerView_Cancelled extends RecyclerView.Adapter<Adapter_RecyclerView_Cancelled.UserViewHolder>{

    private Context mContext;
    private ArrayList<BuyFood> mArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Adapter_RecyclerView_Cancelled(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oders_item_cancelled,parent,false);
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
                        Glide.with(mContext).load(food.getUrl()).into(holder.img_orders_item_cancelled_avt);
                        holder.tv_orders_item_cancelled_name.setText(food.getName());
                    }
                });

        holder.tv_orders_item_cancelled_soLuong.setText(object.getAmountofood()+" items");
        holder.tv_orders_item_cancelled_KhoangCach.setText(object.getKhoangcach()+" km");
        holder.tv_orders_item_cancelled_GiaTien.setText(object.getPriceOderFood() + " vnd");
        if (object.getStatus().trim().equalsIgnoreCase("daHuy")){
            holder.tv_orders_item_cancelled_status.setText("Cancelled");
        }


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
        private ImageView img_orders_item_cancelled_avt;
        private TextView tv_orders_item_cancelled_name,tv_orders_item_cancelled_soLuong
                ,tv_orders_item_cancelled_GiaTien,tv_orders_item_cancelled_status,tv_orders_item_cancelled_KhoangCach;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_orders_item_cancelled_avt = itemView.findViewById(R.id.orders_item_cancelled_avt);
            tv_orders_item_cancelled_name = itemView.findViewById(R.id.orders_item_cancelled_name);
            tv_orders_item_cancelled_soLuong = itemView.findViewById(R.id.orders_item_cancelled_soLuong);
            tv_orders_item_cancelled_KhoangCach = itemView.findViewById(R.id.orders_item_cancelled_KhoangCach);
            tv_orders_item_cancelled_GiaTien = itemView.findViewById(R.id.orders_item_cancelled_GiaTien);
            tv_orders_item_cancelled_status = itemView.findViewById(R.id.orders_item_cancelled_status);


        }
    }
}
