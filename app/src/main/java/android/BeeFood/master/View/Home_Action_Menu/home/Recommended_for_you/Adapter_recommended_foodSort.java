package android.BeeFood.master.View.Home_Action_Menu.home.Recommended_for_you;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Onboarding_Sign_up_Sign_in.Object.Loai_food;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Adapter_recommended_foodSort extends RecyclerView.Adapter<Adapter_recommended_foodSort.UserViewHoler>{

    private Context mContext;
    private ArrayList<Loai_food> lis_food;

    public Adapter_recommended_foodSort(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<Loai_food> lis_food){
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
        Loai_food food = lis_food.get(position);
        if (food == null) return;

        holder.img_foodAvt_item_sort_avt.setImageResource(food.getAvt());
        holder.tv_foodAvt_item_sort_name.setText(food.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
