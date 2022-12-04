package android.BeeFood.master.view.add_san_pham;

import android.BeeFood.master.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_QLSP {

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
