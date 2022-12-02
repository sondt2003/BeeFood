package android.BeeFood.master.View.Food_Details.Adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Object.Food;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_RecyclerView_CheckOut_OrderSummary extends RecyclerView.Adapter<Adapter_RecyclerView_CheckOut_OrderSummary.UserViewHolder>{

    private Context mContext;
    private ArrayList<Food> mArrayList;

    public Adapter_RecyclerView_CheckOut_OrderSummary(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_oders_item_oders_summary,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Food object = mArrayList.get(position);
        if (object == null){
            return;
        }

        holder.img_checkout_Orders_Item_avatar.setImageResource(object.getAvt());
        holder.tv_checkout_Orders_Item_name.setText(object.getName());
        holder.tv_checkout_Orders_Item_gia.setText("$"+object.getGia());
        holder.tv_checkout_Orders_Item_SoLuong.setText("");


    }

    public void setData(ArrayList<Food> mArrayList){
        this.mArrayList = mArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mArrayList != null )return mArrayList.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_checkout_Orders_Item_avatar,img_checkout_Orders_Item_Edit;
        private TextView tv_checkout_Orders_Item_name,tv_checkout_Orders_Item_gia,tv_checkout_Orders_Item_SoLuong;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_checkout_Orders_Item_avatar = itemView.findViewById(R.id.checkout_Orders_Item_avatar);
            img_checkout_Orders_Item_Edit = itemView.findViewById(R.id.checkout_Orders_Item_Edit);
            tv_checkout_Orders_Item_name = itemView.findViewById(R.id.checkout_Orders_Item_name);
            tv_checkout_Orders_Item_gia = itemView.findViewById(R.id.checkout_Orders_Item_gia);
            tv_checkout_Orders_Item_SoLuong = itemView.findViewById(R.id.checkout_Orders_Item_SoLuong);

        }
    }
}
