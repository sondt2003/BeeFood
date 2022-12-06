package android.BeeFood.master.view.add_san_pham;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Adapter_QuanLy extends FragmentStateAdapter {
    public Adapter_QuanLy(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = Fragment_add_san_pham.newInstance();
                break;
            case 1:
                fragment =  Quan_Ly_San_Pham_Fragment.newInstance();
                break;
            case 2:
                fragment = Fragment_KhuyenMai.newInstance();
        }
        return fragment;
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
