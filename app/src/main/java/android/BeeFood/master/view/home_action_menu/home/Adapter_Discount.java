package android.BeeFood.master.view.home_action_menu.home;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.food_details.Add_To_Basket;
import android.BeeFood.master.view.food_details.Food_Details_Menu;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter_Discount extends RecyclerView.Adapter<Adapter_Discount.UserViewHolder>{

    private Context mContext;
    private ArrayList<Food> lis_Discount;

    public Adapter_Discount(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<Food> lis_Discount){
        this.lis_Discount = lis_Discount;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_discount,parent,false);
        return new UserViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Food discount = lis_Discount.get(position);
        if (discount == null) return;

        Glide.with(mContext).load(discount.getUrl()).into(holder.img_item_home_ActionMenu_discount_avt);
        holder.tv_item_home_ActionMenu_discount_name.setText(discount.getName());
        holder.tv_item_home_ActionMenu_discount_khoangCach.setText(discount.getAddress() + " km");
        holder.tv_item_home_ActionMenu_discount_danhGia.setText(discount.getIdloai() + " (" + discount.getEmail() + "k)");
        holder.tv_item_home_ActionMenu_discount_gia.setText("$" + discount.getPrice());
        holder.tv_item_home_ActionMenu_discount_phiShip.setText("$" + discount.getTenloai());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Add_To_Basket.class);
                intent.putExtra("key_id_food",discount.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (lis_Discount != null) return lis_Discount.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_item_home_ActionMenu_discount_avt;
        private TextView tv_item_home_ActionMenu_discount_name,tv_item_home_ActionMenu_discount_khoangCach,tv_item_home_ActionMenu_discount_danhGia,tv_item_home_ActionMenu_discount_gia,tv_item_home_ActionMenu_discount_phiShip;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item_home_ActionMenu_discount_avt = itemView.findViewById(R.id.item_home_ActionMenu_discount_avt);
            tv_item_home_ActionMenu_discount_name = itemView.findViewById(R.id.item_home_ActionMenu_discount_name);
            tv_item_home_ActionMenu_discount_khoangCach = itemView.findViewById(R.id.item_home_ActionMenu_discount_khoangCach);
            tv_item_home_ActionMenu_discount_danhGia = itemView.findViewById(R.id.item_home_ActionMenu_discount_danhGia);
            tv_item_home_ActionMenu_discount_gia = itemView.findViewById(R.id.item_home_ActionMenu_discount_gia);
            tv_item_home_ActionMenu_discount_phiShip = itemView.findViewById(R.id.item_home_ActionMenu_discount_phiShip);
        }
    }
}
