package android.BeeFood.master.view.add_san_pham;

import android.BeeFood.master.R;
import android.BeeFood.master.view.object.Food;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_QLSP extends RecyclerView.Adapter<Adapter_QLSP.UserViewHolder>{

    private Context mContext;
    private ArrayList<Food> mArrayList;

    public Adapter_QLSP(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qlsp_item_layout,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Food object = mArrayList.get(position);
        if (object == null){
            return;
        }


        holder.img_qlsp_Item_avatar.setImageResource(object.getAvt());
        holder.tv_qlsp_Item_name.setText(object.getName());
        holder.tv_qlsp_Item_loai.setText("Loại: "+object.getId_loai());
        holder.tv_qlsp_Item_gia.setText("$"+object.getGia());


        holder.btn_qlsp_Item_btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn có muốn xóa?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mArrayList.remove(object);
                    }
                });
            }
        });

        holder.btn_qlsp_Item_btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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

        private ImageView img_qlsp_Item_avatar,btn_qlsp_Item_btn_Delete,btn_qlsp_Item_btn_Edit;
        private TextView tv_qlsp_Item_name,tv_qlsp_Item_loai,tv_qlsp_Item_gia;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_qlsp_Item_avatar = itemView.findViewById(R.id.qlsp_Item_avatar);
            btn_qlsp_Item_btn_Delete = itemView.findViewById(R.id.qlsp_Item_btn_Delete);
            btn_qlsp_Item_btn_Edit = itemView.findViewById(R.id.qlsp_Item_btn_Edit);

            tv_qlsp_Item_name = itemView.findViewById(R.id.qlsp_Item_name);
            tv_qlsp_Item_loai = itemView.findViewById(R.id.qlsp_Item_loai);
            tv_qlsp_Item_gia = itemView.findViewById(R.id.qlsp_Item_gia);

        }
    }
}
