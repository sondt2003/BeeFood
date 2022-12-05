package android.BeeFood.master.view.home_action_menu.home;

import android.BeeFood.master.R;
import android.BeeFood.master.model.loaiFood;
import android.BeeFood.master.view.object.Loai_food;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class Adapter_Foodtype extends BaseAdapter {

    private Context mContext;
    private ArrayList<loaiFood> lis_loaiFood;
    private int itemLayout;

    public Adapter_Foodtype(Context mContext, ArrayList<loaiFood> lis_loaiFood, int itemLayout) {
        this.mContext = mContext;
        this.lis_loaiFood = lis_loaiFood;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return lis_loaiFood.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        itemHolder holder = null;
        if (view == null){
            holder = new itemHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemLayout,null);

            holder.img_item_home_ActionMenu_FoodType_avt = view.findViewById(R.id.item_home_ActionMenu_FoodType_avt);
            holder.tv_item_home_ActionMenu_FoodType_name = view.findViewById(R.id.item_home_ActionMenu_FoodType_name);

            view.setTag(holder);
        }else{
            holder = (itemHolder) view.getTag();
        }

        Glide.with(mContext).load(lis_loaiFood.get(i).getUrl()).into(holder.img_item_home_ActionMenu_FoodType_avt);
        holder.tv_item_home_ActionMenu_FoodType_name.setText(lis_loaiFood.get(i).getNameloai());
        
        return view;
    }

    public static class itemHolder{
        private ImageView img_item_home_ActionMenu_FoodType_avt;
        private TextView tv_item_home_ActionMenu_FoodType_name;
    }
}
