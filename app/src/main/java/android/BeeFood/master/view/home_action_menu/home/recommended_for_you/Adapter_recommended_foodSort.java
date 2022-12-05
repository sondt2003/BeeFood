package android.BeeFood.master.view.home_action_menu.home.recommended_for_you;

import android.BeeFood.master.R;
import android.BeeFood.master.view.food_details.Add_To_Basket;
import android.BeeFood.master.view.food_details.Food_Details_Menu;
import android.BeeFood.master.view.object.Loai_food;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        switch (position){
            case 0:
                holder.tv_foodAvt_item_sort_name.setText("Add");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "1", Toast.LENGTH_SHORT).show();
                        Dialog dialog = new Dialog(mContext);
                        dialog.setContentView(R.layout.dialog_add_foodtype);

                        ImageView img_dialog_addFoodType_avt = dialog.findViewById(R.id.dialog_addFoodType_avt);
                        EditText edt_dialog_addFoodType_name = dialog.findViewById(R.id.dialog_addFoodType_name);
                        Button btn_dialog_addFoodType_cancel = dialog.findViewById(R.id.dialog_addFoodType_cancel);
                        Button btn_dialog_addFoodType_Add = dialog.findViewById(R.id.dialog_addFoodType_Add);

                        btn_dialog_addFoodType_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });


                        btn_dialog_addFoodType_Add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //.....
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                });
                break;
            case 1:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "2", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                holder.img_foodAvt_item_sort_avt.setImageResource(food.getAvt());
                holder.tv_foodAvt_item_sort_name.setText(food.getName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, Add_To_Basket.class);
                        mContext.startActivity(intent);
                    }
                });
                break;
        }


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
