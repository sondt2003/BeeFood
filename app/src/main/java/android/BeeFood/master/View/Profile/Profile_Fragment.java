package android.BeeFood.master.View.Profile;

import android.BeeFood.master.View.Profile.address.Address_Activity;
import android.BeeFood.master.View.Profile.language.Language_Activity;
import android.BeeFood.master.View.Profile.notification.Notification_Activity;
import android.BeeFood.master.View.Profile.profile_update.Profile_Update_Activity;
import android.BeeFood.master.View.Profile.security.Security_Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.BeeFood.master.R;
import android.widget.LinearLayout;

public class Profile_Fragment extends Fragment {

//    hi
    private LinearLayout btnProfile,btnAddress,
            btnNotification,btnSecurity,btnLanguage,btnLogout;

    public Profile_Fragment() {
        // Required empty public constructor
    }

    public static Profile_Fragment newInstance() {
        Profile_Fragment fragment = new Profile_Fragment();
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
        return inflater.inflate(R.layout.fragment_profile_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnProfile = view.findViewById(R.id.profile_profileEdit_OnClick);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Profile_Update_Activity.class);
                startActivity(intent);
            }
        });

        btnAddress = view.findViewById(R.id.profile_address_OnClick);
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Address_Activity.class);
                startActivity(intent);
            }
        });

        btnNotification = view.findViewById(R.id.profile_notification_OnClick);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Notification_Activity.class);
                startActivity(intent);
            }
        });

        btnSecurity = view.findViewById(R.id.profile_security_OnClick);
        btnSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Security_Activity.class);
                startActivity(intent);
            }
        });

        btnLanguage = view.findViewById(R.id.profile_language_OnClick);
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Language_Activity.class);
                startActivity(intent);
            }
        });

        btnLogout = view.findViewById(R.id.profile_Logout_OnClick);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}