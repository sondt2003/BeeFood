package android.BeeFood.master.view.object.adapter;

import android.BeeFood.master.R;
import android.BeeFood.master.view.object.Food;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_RecyclerView_FoodDetail_menu_and_drink extends RecyclerView.Adapter<Adapter_RecyclerView_FoodDetail_menu_and_drink.UserViewHolder> {

    private Context mContext;
    private ArrayList<Food> mArrayList;

    public Adapter_RecyclerView_FoodDetail_menu_and_drink(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail_item_menu_and_drink,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Food object = mArrayList.get(position);
        if (object == null){
            return;
        }

        holder.img_item_restaurantDetail_menuAndDrink_avatar.setImageResource(object.getAvt());
        holder.tv_item_restaurantDetail_menuAndDrink_name.setText(object.getName());
        holder.tv_item_restaurantDetail_menuAndDrink_gia.setText("$"+object.getGia());
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

        private ImageView img_item_restaurantDetail_menuAndDrink_avatar;
        private TextView tv_item_restaurantDetail_menuAndDrink_name,tv_item_restaurantDetail_menuAndDrink_gia;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item_restaurantDetail_menuAndDrink_avatar = itemView.findViewById(R.id.item_restaurantDetail_menuAndDrink_avatar);
            tv_item_restaurantDetail_menuAndDrink_name = itemView.findViewById(R.id.item_restaurantDetail_menuAndDrink_name);
            tv_item_restaurantDetail_menuAndDrink_gia = itemView.findViewById(R.id.item_restaurantDetail_menuAndDrink_gia);


        }
    }
}
