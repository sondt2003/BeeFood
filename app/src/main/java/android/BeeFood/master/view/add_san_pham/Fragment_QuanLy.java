package android.BeeFood.master.view.add_san_pham;

import android.BeeFood.master.view.orders.adapter.OderViewPagerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.BeeFood.master.R;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment_QuanLy extends Fragment {

    Adapter_QuanLy adapter_quanLy;
    TabLayout tab_addSanPham_QuanLy_TabLayout;
    ViewPager2 viewPager2_addSanPham_QuanLy_Layout;

    public Fragment_QuanLy() {
        // Required empty public constructor
    }


    public static Fragment_QuanLy newInstance() {
        Fragment_QuanLy fragment = new Fragment_QuanLy();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__quan_ly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa(view);

        adapter_quanLy = new Adapter_QuanLy(getActivity());
        viewPager2_addSanPham_QuanLy_Layout.setAdapter(adapter_quanLy);

        TabLayoutMediator mediator = new TabLayoutMediator(tab_addSanPham_QuanLy_TabLayout, viewPager2_addSanPham_QuanLy_Layout, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Add sản phẩm");
                        break;
                    case 1:
                        tab.setText("Quản lý sản phẩm");
                        break;
                }
            }
        });

    }

    public void AnhXa(View v){
        tab_addSanPham_QuanLy_TabLayout = v.findViewById(R.id.addSanPham_QuanLy_TabLayout);
        viewPager2_addSanPham_QuanLy_Layout = v.findViewById(R.id.addSanPham_QuanLy_Layout);
    }
}