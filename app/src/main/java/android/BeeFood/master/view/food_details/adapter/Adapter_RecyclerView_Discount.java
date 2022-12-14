package android.BeeFood.master.view.food_details.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.model.khuyenMai;
import android.BeeFood.master.view.food_details.Checkout_Oders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_RecyclerView_Discount extends RecyclerView.Adapter<Adapter_RecyclerView_Discount.UserViewHolder>{

    public Adapter_RecyclerView_Discount(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext;
    private ArrayList<khuyenMai> mArrayList;
    private Bundle bundle;

    double Total = 0;
    int soluongSP = 0;
    String idFood,idOrderFood;

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

        Total = bundle.getDouble("key_Total",0);
        soluongSP = bundle.getInt("key_soluongSP",0);
        idFood = bundle.getString("key_idFood");
        idOrderFood = bundle.getString("key_orderFood");

        holder.tv_food_detail_discount_item_soTienGiam.setText("Giảm: "+object.getSoTienGiam() +" VNĐ");
        holder.tv_food_detail_discount_item_Min.setText("Min: "+object.getMin() +" VNĐ" );
        holder.tv_food_detail_discount_item_Max.setText("Max: "+object.getMax() +" VNĐ");
        holder.tv_food_detail_discount_item_soLuong.setText(""+object.getSoLuong());

        double soTienGiam = 0;
        double min = 0,max = 0;
        int soLuong = 0;
        try {
            soTienGiam = Double.parseDouble(object.getSoTienGiam());
            min = Double.parseDouble(object.getMin());
            max = Double.parseDouble(object.getMax());
            soLuong = Integer.parseInt(object.getSoLuong());

            if (soLuong == 0){
                holder.itemView.setFocusable(false);
                holder.itemView.setBackgroundResource(R.drawable.bg_het_giam_gia);
                holder.tv_food_detail_discount_item_soLuong.setTextColor(Color.RED);
            }else if(Total < min || Total > max){
                holder.itemView.setFocusable(false);
                holder.itemView.setBackgroundResource(R.drawable.bg_het_giam_gia);
            }else {
                double finalSoTienGiam = soTienGiam;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext,Checkout_Oders.class);
                        intent.putExtra("key_soluongSP",soluongSP);
                        intent.putExtra("key_idFood",idFood);
                        intent.putExtra("key_orderFood",idOrderFood);
                        intent.putExtra("key_soTienGiam", finalSoTienGiam);
                        mContext.startActivity(intent);
                    }
                });
            }
        }catch (Exception e){

        }

    }

    public void setData(ArrayList<khuyenMai> mArrayList ,Bundle bundle){
        this.mArrayList = mArrayList;
        this.bundle = bundle;
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
