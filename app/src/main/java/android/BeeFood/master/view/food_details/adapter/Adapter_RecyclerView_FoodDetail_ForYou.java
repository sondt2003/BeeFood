package android.BeeFood.master.view.food_details.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_RecyclerView_FoodDetail_ForYou extends RecyclerView.Adapter<Adapter_RecyclerView_FoodDetail_ForYou.UserViewHoler>{

    private Context mContext;
    private ArrayList<Food> mArrayList;

    public Adapter_RecyclerView_FoodDetail_ForYou(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail_item_foryou,parent,false);
        return new UserViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHoler holder, int position) {
        Food object = mArrayList.get(position);
        if (object == null){
            return;
        }

//        holder.img_item_restaurantDetail_forYou_avatar.setImageResource(object.getAvt());
//        holder.tv_item_restaurantDetail_forYou_name.setText(object.getName());
//        holder.tv_item_restaurantDetail_forYou_gia.setText("$"+object.getGia());
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

    public class UserViewHoler extends RecyclerView.ViewHolder {

        private ImageView img_item_restaurantDetail_forYou_avatar;
        private TextView tv_item_restaurantDetail_forYou_name,tv_item_restaurantDetail_forYou_gia;

        public UserViewHoler(@NonNull View itemView) {
            super(itemView);

            img_item_restaurantDetail_forYou_avatar = itemView.findViewById(R.id.item_restaurantDetail_forYou_avatar);
            tv_item_restaurantDetail_forYou_name = itemView.findViewById(R.id.item_restaurantDetail_forYou_name);
            tv_item_restaurantDetail_forYou_gia = itemView.findViewById(R.id.item_restaurantDetail_forYou_gia);
        }
    }
}
