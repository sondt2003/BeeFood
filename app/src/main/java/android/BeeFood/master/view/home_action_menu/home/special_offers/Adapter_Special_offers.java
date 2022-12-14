package android.BeeFood.master.view.home_action_menu.home.special_offers;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.view.food_details.Add_To_Basket;
import android.BeeFood.master.view.food_details.Food_Details_Menu;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter_Special_offers extends RecyclerView.Adapter<Adapter_Special_offers.UserViewHolder>{

    private Context mContext;
    private ArrayList<Food> lis_Banner;

    public Adapter_Special_offers(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<Food> lis_Banner){
        this.lis_Banner = lis_Banner;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_special,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Food i_banner = lis_Banner.get(position);
//        holder.img_home_item_special_banner.setImageResource(i_banner);
        Glide.with(mContext).load(i_banner.getUrl()).into(holder.img_home_item_special_banner);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Add_To_Basket.class);
                mContext.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Add_To_Basket.class);
                intent.putExtra("key_idFood",i_banner.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (lis_Banner != null) return lis_Banner.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_home_item_special_banner;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_home_item_special_banner = itemView.findViewById(R.id.home_item_special_banner);
        }
    }
}
