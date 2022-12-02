package android.BeeFood.master.View.Orders;

import android.BeeFood.master.View.Orders.adapter.OderViewPagerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.BeeFood.master.R;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class OrdersFragment extends Fragment {

    private TabLayout oders_TabLayout;
    private ViewPager2 oders_ViewPager2;
    private OderViewPagerAdapter viewPagerAdapter;
    private Toolbar oders_toolbar;

    public OrdersFragment() {
        // Required empty public constructor
    }

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
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
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anhXa(view);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
//
//        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
//        activity.getSupportActionBar().setIcon(R.drawable.logo_app);
//        activity.getSupportActionBar().setTitle("Orders");

        viewPagerAdapter =new OderViewPagerAdapter(getActivity());
        oders_ViewPager2.setAdapter(viewPagerAdapter);


        TabLayoutMediator mediator = new TabLayoutMediator(oders_TabLayout, oders_ViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Active");
                        break;
                    case 1:
                        tab.setText("Completed");
                        break;
                    case 2:
                        tab.setText("Cancelled");
                        break;
                }
            }
        });
        mediator.attach();

    }

    public void anhXa(View view){
        oders_TabLayout = view.findViewById(R.id.oders_TabLayout);
        oders_ViewPager2 = view.findViewById(R.id.oders_ViewPager2);
    }
}