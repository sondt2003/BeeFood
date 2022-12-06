package android.BeeFood.master.view.add_san_pham;

import android.BeeFood.master.R;
import android.BeeFood.master.model.khuyenMai;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_KhuyenMai extends RecyclerView.Adapter<Adapter_KhuyenMai.UserViewHolder>{
    Context mContext;
    ArrayList<khuyenMai> lis_KhuyenMai;

    public Adapter_KhuyenMai(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<khuyenMai> lis_KhuyenMai){
        this.lis_KhuyenMai = lis_KhuyenMai;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khuyen_mai,parent,false);
        return new UserViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        khuyenMai khuyenMai = lis_KhuyenMai.get(position);
        if (khuyenMai == null) return;

        holder.tv_item_KhuyenMai_ma.setText("Mã : " + khuyenMai.getId());
        holder.tv_item_KhuyenMai_soTienGiam.setText("Giảm : " + khuyenMai.getSoTienGiam()+ " vnd");
        holder.tv_item_KhuyenMai_Min.setText("Min : " + khuyenMai.getMin()+ " vnd");
        holder.tv_item_KhuyenMai_Max.setText("Max : " + khuyenMai.getMax()+ " vnd");
        holder.tv_item_KhuyenMai_soLuong.setText("" + khuyenMai.getSoLuong());

        if (holder.tv_item_KhuyenMai_soLuong.getText().toString().equals("0")){
            holder.tv_item_KhuyenMai_soLuong.setTextColor(Color.RED);
            holder.itemView.setBackgroundResource(R.drawable.bg_het_giam_gia);
            holder.itemView.setFocusable(false);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Phiếu giảm đã hết", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            holder.tv_item_KhuyenMai_soLuong.setTextColor(Color.GREEN);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "có thể sử dụng", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (lis_KhuyenMai != null) return lis_KhuyenMai.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_item_KhuyenMai_ma,tv_item_KhuyenMai_soTienGiam,tv_item_KhuyenMai_Min,tv_item_KhuyenMai_Max,tv_item_KhuyenMai_soLuong;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_KhuyenMai_ma = itemView.findViewById(R.id.item_KhuyenMai_ma);
            tv_item_KhuyenMai_soTienGiam = itemView.findViewById(R.id.item_KhuyenMai_soTienGiam);
            tv_item_KhuyenMai_Min = itemView.findViewById(R.id.item_KhuyenMai_Min);
            tv_item_KhuyenMai_Max = itemView.findViewById(R.id.item_KhuyenMai_Max);
            tv_item_KhuyenMai_soLuong = itemView.findViewById(R.id.item_KhuyenMai_soLuong);
        }
    }
}
