package android.BeeFood.master.View.Home_Action_Menu.home;

import android.BeeFood.master.R;
import android.BeeFood.master.View.Onboarding_Sign_up_Sign_in.Object.Loai_food;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapter_Foodtype extends BaseAdapter {

    private Context mContext;
    private ArrayList<Loai_food> lis_loaiFood;
    private int itemLayout;

    public Adapter_Foodtype(Context mContext, ArrayList<Loai_food> lis_loaiFood, int itemLayout) {
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

        holder.img_item_home_ActionMenu_FoodType_avt.setImageResource(lis_loaiFood.get(i).getAvt());
        holder.tv_item_home_ActionMenu_FoodType_name.setText(lis_loaiFood.get(i).getName());
        
        return view;
    }

    public static class itemHolder{
        private ImageView img_item_home_ActionMenu_FoodType_avt;
        private TextView tv_item_home_ActionMenu_FoodType_name;
    }
}
