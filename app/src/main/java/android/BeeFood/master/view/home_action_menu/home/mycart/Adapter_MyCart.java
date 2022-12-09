package android.BeeFood.master.view.home_action_menu.home.mycart;

import android.BeeFood.master.R;
import android.BeeFood.master.model.Food;
import android.BeeFood.master.model.OderFood;
import android.BeeFood.master.view.object.MyCart;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Adapter_MyCart extends RecyclerView.Adapter<Adapter_MyCart.UserViewHolder>{

    private Context mContext;
    private ArrayList<OderFood> lis_Cart;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Adapter_MyCart(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<OderFood> lis_Cart){
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
        OderFood myCart = lis_Cart.get(position);
        if (myCart == null) return;

        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Food food = new Food();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (myCart.getIdFood().equalsIgnoreCase(document.getId())){
                                    food = new Food(document.getData().get("namefood").toString(),
                                            document.getData().get("price").toString(),
                                            document.getData().get("address").toString(),
                                            document.getData().get("phonenumber").toString(),
                                            document.getData().get("email").toString(),
                                            "Chưa có id",
                                            document.getData().get("tenloai").toString(),
                                            document.getData().get("ImageUrl").toString(),
                                            document.getData().get("describle").toString());
                                }
                            }
                        }
                        Glide.with(mContext).load(food.getUrl()).into(holder.img_item_home_ActionMenu_MyCart_avt);
                        holder.tv_item_home_ActionMenu_MyCart_name.setText(food.getName());
                    }
                });
        holder.tv_item_home_ActionMenu_MyCart_khoangCach.setText(myCart.getKhoangcach() + " km");
        holder.tv_item_home_ActionMenu_MyCart_tongGia.setText(myCart.getPriceOderFood()+"$");
        holder.tv_item_home_ActionMenu_MyCart_countItem.setText("Số Lượng:"+myCart.getAmountoffood());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, myCart.getIdFood(), Toast.LENGTH_SHORT).show();
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
