package android.BeeFood.master.view.home_action_menu.home.recommended_for_you;

import android.BeeFood.master.R;
import android.BeeFood.master.model.loaiFood;
import android.BeeFood.master.view.food_details.Add_To_Basket;
import android.BeeFood.master.view.object.Loai_food;
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



public class Adapter_recommended_foodSort extends RecyclerView.Adapter<Adapter_recommended_foodSort.UserViewHoler>{

    private Context mContext;
    private ArrayList<loaiFood> lis_food;
    public static String nameLoai = "all";


    public Adapter_recommended_foodSort(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<loaiFood> lis_food){
        this.lis_food = lis_food;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_avt_item_sort,parent,false);
        return new UserViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHoler holder, int position) {
        loaiFood food = lis_food.get(position);
        if (food == null) return;

            Glide.with(mContext).load(food.getUrl()).into(holder.img_foodAvt_item_sort_avt);

            holder.tv_foodAvt_item_sort_name.setText(food.getNameloai());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nameLoai = food.getNameloai();
                }
            });




    }

    @Override
    public int getItemCount() {
        if (lis_food != null) return lis_food.size();
        return 0;
    }

    public class UserViewHoler extends RecyclerView.ViewHolder{
        private ImageView img_foodAvt_item_sort_avt;
        private TextView tv_foodAvt_item_sort_name;

        public UserViewHoler(@NonNull View itemView) {
            super(itemView);

            img_foodAvt_item_sort_avt = itemView.findViewById(R.id.foodAvt_item_sort_avt);
            tv_foodAvt_item_sort_name = itemView.findViewById(R.id.foodAvt_item_sort_name);
        }
    }

}
