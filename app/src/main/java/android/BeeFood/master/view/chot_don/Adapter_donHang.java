package android.BeeFood.master.view.chot_don;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.BuyFoodDao;
import android.BeeFood.master.model.BuyFood;
import android.BeeFood.master.model.Food;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Adapter_donHang extends RecyclerView.Adapter<Adapter_donHang.UserViewHolder>{
    private Context mContext;
    private ArrayList<BuyFood> mArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Adapter_donHang(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<BuyFood> mArrayList){
        this.mArrayList = mArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        BuyFood object = mArrayList.get(position);
        if (object == null) return;
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Food food = new Food();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (object.getIdFood().equalsIgnoreCase(document.getId())){
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
                        Glide.with(mContext).load(food.getUrl()).into(holder.img_item_donhang_avt);
                        holder.tv_item_donhang_name.setText(food.getName());
                    }
                });


        holder.tv_item_donhang_soluong.setText(object.getAmountofood()+" items");
        holder.tv_item_donhang_GiaTien.setText(object.getPriceOderFood() + " vnd");

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().get("email").toString().equalsIgnoreCase(object.getEmailUser())){
                                    holder.tv_item_donhang_sdt.setText(object.getEmailUser());
                                    holder.tv_item_donhang_nameUser.setText(document.getData().get("fullname").toString());
                                }
                            }
                        }
                    }
                });

        holder.btn_item_donhang_huy.setText("Huy");
        holder.btn_item_donhang_huy.setTextColor(Color.RED);
        holder.btn_item_donhang_huy.setBackgroundResource(R.drawable.bg_btn_huy);
        holder.btn_item_donhang_nhan.setText("Nhận đơn");


        holder.btn_item_donhang_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //huy
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Huỷ");
                builder.setMessage("Bạn có chắc muốn hủy đơn ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BuyFoodDao buyFoodDao = new BuyFoodDao();
                        buyFoodDao.updateFood("daHuy",mContext,object.getIdBuyFood());

                        db.collection("buyfood")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        ArrayList<BuyFood> list = new ArrayList<>();
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                if(document.getData().get("status").toString().equalsIgnoreCase("dangDatHang")){
                                                    list.add(new BuyFood(
                                                            document.getId(),
                                                            document.getData().get("idfood").toString(),
                                                            document.getData().get("emailuser").toString(),
                                                            document.getData().get("emailfood").toString(),
                                                            document.getData().get("amountofood").toString(),
                                                            document.getData().get("priceOderFood").toString(),
                                                            "1.8",  //chưa có khoảng cách
                                                            document.getData().get("status").toString()));
                                                }

                                            }
                                        }
                                        setData(list);
                                    }
                                });
                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();
            }
        });

        holder.btn_item_donhang_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuyFoodDao buyFoodDao = new BuyFoodDao();
                buyFoodDao.updateFood("dangVanChuyen",mContext,object.getIdBuyFood());

                db.collection("buyfood")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                SharedPreferences sharedPref = mContext.getSharedPreferences("USER", Context.MODE_PRIVATE);
                                String email = sharedPref.getString("email", "");
                                ArrayList<BuyFood> list = new ArrayList<>();
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if(email.equalsIgnoreCase(document.getData().get("emailfood").toString()) && document.getData().get("status").toString().equalsIgnoreCase("dangDatHang")){
                                            list.add(new BuyFood(
                                                    document.getId(),
                                                    document.getData().get("idfood").toString(),
                                                    document.getData().get("emailuser").toString(),
                                                    document.getData().get("emailfood").toString(),
                                                    document.getData().get("amountofood").toString(),
                                                    document.getData().get("priceOderFood").toString(),
                                                    "1.8",  //chưa có khoảng cách
                                                    document.getData().get("status").toString()));
                                        }

                                    }
                                }
                                setData(list);
                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        if (mArrayList != null) return mArrayList.size();
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_item_donhang_avt;
        private TextView tv_item_donhang_name,tv_item_donhang_soluong,tv_item_donhang_sdt,tv_item_donhang_GiaTien,tv_item_donhang_nameUser;
        private Button btn_item_donhang_huy,btn_item_donhang_nhan;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item_donhang_avt = itemView.findViewById(R.id.item_donhang_avt);
            tv_item_donhang_name = itemView.findViewById(R.id.item_donhang_name);
            tv_item_donhang_soluong = itemView.findViewById(R.id.item_donhang_soluong);
            tv_item_donhang_GiaTien = itemView.findViewById(R.id.item_donhang_GiaTien);
            tv_item_donhang_sdt = itemView.findViewById(R.id.item_donhang_sdt);
            btn_item_donhang_huy = itemView.findViewById(R.id.item_donhang_huy);
            btn_item_donhang_nhan = itemView.findViewById(R.id.item_donhang_nhan);
            tv_item_donhang_nameUser = itemView.findViewById(R.id.item_donhang_nameUser);

        }
    }
}
