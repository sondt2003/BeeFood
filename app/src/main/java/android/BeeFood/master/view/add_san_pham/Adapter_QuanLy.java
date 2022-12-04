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
        switch (position){
            case 0:
                return Fragment_add_san_pham.newInstance();
            case 1:
                return Quan_Ly_San_Pham_Fragment.newInstance();
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
