package android.BeeFood.master.View.Home_Action_Menu.home.MyCart;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Object.MyCart;
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

public class Adapter_MyCart extends RecyclerView.Adapter<Adapter_MyCart.UserViewHolder>{

    private Context mContext;
    private ArrayList<MyCart> lis_Cart;

    public Adapter_MyCart(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<MyCart> lis_Cart){
        this.lis_Cart = lis_Cart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new UserViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        MyCart myCart = lis_Cart.get(position);
        if (myCart == null) return;

        holder.img_item_home_ActionMenu_MyCart_avt.setImageResource(myCart.getAvt());
        holder.tv_item_home_ActionMenu_MyCart_name.setText(myCart.getName());
        holder.tv_item_home_ActionMenu_MyCart_countItem.setText(myCart.getCoutItem() + " item");
        holder.tv_item_home_ActionMenu_MyCart_khoangCach.setText(myCart.getKhoangCach() + " km");
        holder.tv_item_home_ActionMenu_MyCart_tongGia.setText("$"+myCart.getTongGia());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, myCart.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (lis_Cart != null) return lis_Cart.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_item_home_ActionMenu_MyCart_avt;
        private TextView tv_item_home_ActionMenu_MyCart_name,tv_item_home_ActionMenu_MyCart_countItem,tv_item_home_ActionMenu_MyCart_khoangCach,tv_item_home_ActionMenu_MyCart_tongGia;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item_home_ActionMenu_MyCart_avt = itemView.findViewById(R.id.item_home_ActionMenu_MyCart_avt);
            tv_item_home_ActionMenu_MyCart_name = itemView.findViewById(R.id.item_home_ActionMenu_MyCart_name);
            tv_item_home_ActionMenu_MyCart_countItem = itemView.findViewById(R.id.item_home_ActionMenu_MyCart_countItem);
            tv_item_home_ActionMenu_MyCart_khoangCach = itemView.findViewById(R.id.item_home_ActionMenu_MyCart_khoangCach);
            tv_item_home_ActionMenu_MyCart_tongGia = itemView.findViewById(R.id.item_home_ActionMenu_MyCart_tongGia);
        }
    }
}
