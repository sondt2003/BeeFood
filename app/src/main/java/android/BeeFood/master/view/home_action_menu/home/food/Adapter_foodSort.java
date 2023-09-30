package android.BeeFood.master.view.home_action_menu.home.food;

import android.BeeFood.master.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_foodSort extends RecyclerView.Adapter<Adapter_foodSort.UserViewHolder>{

    private Context mContext;
    private ArrayList<String> lis_loaiFood;

    public Adapter_foodSort(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<String> lis_loaiFood){
        this.lis_loaiFood = lis_loaiFood;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_sort,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        String loai_food = lis_loaiFood.get(position);
        if (loai_food == null) return;

        holder.tvNameSort.setText(loai_food);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, loai_food, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (lis_loaiFood != null) return lis_loaiFood.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameSort;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameSort = itemView.findViewById(R.id.food_item_tvNameSort);
        }
    }
}
