package android.BeeFood.master.View.EWallet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.BeeFood.master.R;

public class EWallet_Fragment extends Fragment {


    public EWallet_Fragment() {
        // Required empty public constructor
    }
    public static EWallet_Fragment newInstance() {
        EWallet_Fragment fragment = new EWallet_Fragment();
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
        return inflater.inflate(R.layout.fragment_e_wallet_, container, false);
    }
}