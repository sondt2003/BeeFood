package android.BeeFood.master.View.Home_Action_Menu.home;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Object.Food;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Recommended extends RecyclerView.Adapter<Adapter_Recommended.UserViewHolder>{

    private Context mContext;
    private ArrayList<Food> lis_recommended;

    public Adapter_Recommended(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<Food> lis_recommended){
        this.lis_recommended = lis_recommended;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_recommended,parent,false);
        return new UserViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Food recommended = lis_recommended.get(position);

        if (recommended == null) return;

        holder.img_item_home_ActionMenu_recommended_avt.setImageResource(recommended.getAvt());
        holder.tv_item_home_ActionMenu_recommended_name.setText(recommended.getName());
        holder.tv_item_home_ActionMenu_recommended_khoangCach.setText(recommended.getKhoangCach() + " km");
        holder.tv_item_home_ActionMenu_recommended_danhGia.setText(recommended.getDanhGia() + " (" + recommended.getSoNguoiDanhGia() + "k)");
        holder.tv_item_home_ActionMenu_recommended_phiShip.setText("$" + recommended.getPhiShip());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, recommended.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (lis_recommended != null) return lis_recommended.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_item_home_ActionMenu_recommended_avt;
        private TextView tv_item_home_ActionMenu_recommended_name,tv_item_home_ActionMenu_recommended_khoangCach,tv_item_home_ActionMenu_recommended_danhGia,tv_item_home_ActionMenu_recommended_phiShip;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item_home_ActionMenu_recommended_avt = itemView.findViewById(R.id.item_home_ActionMenu_recommended_avt);
            tv_item_home_ActionMenu_recommended_name = itemView.findViewById(R.id.item_home_ActionMenu_recommended_name);
            tv_item_home_ActionMenu_recommended_khoangCach = itemView.findViewById(R.id.item_home_ActionMenu_recommended_khoangCach);
            tv_item_home_ActionMenu_recommended_danhGia = itemView.findViewById(R.id.item_home_ActionMenu_recommended_danhGia);
            tv_item_home_ActionMenu_recommended_phiShip = itemView.findViewById(R.id.item_home_ActionMenu_recommended_phiShip);

        }
    }
}
