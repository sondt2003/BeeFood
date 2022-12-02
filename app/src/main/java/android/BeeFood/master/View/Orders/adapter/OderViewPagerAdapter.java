package android.BeeFood.master.View.Orders.adapter;

import android.BeeFood.master.View.Orders.item_tablayout.Oders_Active_Fragment;
import android.BeeFood.master.View.Orders.item_tablayout.Oders_Cancelled_Fragment;
import android.BeeFood.master.View.Orders.item_tablayout.Oders_Completed_Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OderViewPagerAdapter extends FragmentStateAdapter {

    public OderViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = Oders_Active_Fragment.newInstance();
                break;
            case 1:
                fragment = Oders_Completed_Fragment.newInstance();
                break;
            case 2:
                fragment = Oders_Cancelled_Fragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
