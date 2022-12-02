package android.BeeFood.master.View.Orders.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Orders.model.Oders_Object;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_RecyclerView_Cancelled extends RecyclerView.Adapter<Adapter_RecyclerView_Cancelled.UserViewHolder>{

    private Context mContext;
    private ArrayList<Oders_Object> mArrayList;

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
        Oders_Object object = mArrayList.get(position);
        if (object == null || object.getStatus() != -1){
            return;
        }

        holder.img_orders_item_cancelled_avt.setImageResource(object.getAvt());
        holder.tv_orders_item_cancelled_name.setText(object.getName());
        holder.tv_orders_item_cancelled_soLuong.setText(object.getSoluong()+" items");
        holder.tv_orders_item_cancelled_KhoangCach.setText(object.getKhoang_Cach()+" km");
        holder.tv_orders_item_cancelled_GiaTien.setText("$"+object.getGiaTien());
    }

    public void setData(ArrayList<Oders_Object> mArrayList){
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
