package android.BeeFood.master.view.food_details.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.model.khuyenMai;
import android.BeeFood.master.view.food_details.Checkout_Oders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_RecyclerView_Discount extends RecyclerView.Adapter<Adapter_RecyclerView_Discount.UserViewHolder>{

    public Adapter_RecyclerView_Discount(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext;
    private ArrayList<khuyenMai> mArrayList;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail_discount_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        khuyenMai object = mArrayList.get(position);
        if(object == null)return;

        holder.tv_food_detail_discount_item_soTienGiam.setText("Giảm: "+object.getSoTienGiam() +" VNĐ");
        holder.tv_food_detail_discount_item_Min.setText("Min: "+object.getMin() +" VNĐ" );
        holder.tv_food_detail_discount_item_Max.setText("Max: "+object.getMax() +" VNĐ");
        holder.tv_food_detail_discount_item_soLuong.setText(""+object.getSoLuong());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khuyenMai khuyenMai = new khuyenMai(object.getSoTienGiam(),object.getMin(),object.getMax(),object.getSoLuong());
                Intent intent = new Intent(mContext, Checkout_Oders.class);
                Bundle bundle = new Bundle();
                bundle.putInt("checkVisible",1);
                bundle.putString("soTienGiam",object.getSoTienGiam());
                bundle.putString("min",object.getMin());
                bundle.putString("max",object.getMax());
                bundle.putString("soLuong",object.getSoLuong());
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
    }

    public void setData(ArrayList<khuyenMai> mArrayList){
        this.mArrayList = mArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mArrayList != null) return mArrayList.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_food_detail_discount_item_soTienGiam,tv_food_detail_discount_item_Min,tv_food_detail_discount_item_Max,tv_food_detail_discount_item_soLuong;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_food_detail_discount_item_soTienGiam = itemView.findViewById(R.id.food_detail_discount_item_soTienGiam);
            tv_food_detail_discount_item_Min = itemView.findViewById(R.id.food_detail_discount_item_Min);
            tv_food_detail_discount_item_Max = itemView.findViewById(R.id.food_detail_discount_item_Max);
            tv_food_detail_discount_item_soLuong =  itemView.findViewById(R.id.food_detail_discount_item_soLuong);

        }
    }
}
