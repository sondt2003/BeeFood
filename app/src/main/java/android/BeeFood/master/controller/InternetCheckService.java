package android.BeeFood.master.controller;

import android.BeeFood.master.R;
import android.BeeFood.master.view.home_action_menu.HomeActivity;
import android.BeeFood.master.view.onboarding_sign_up_sign_in.MainActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InternetCheckService extends BroadcastReceiver {
    Context mContext;
    String Activity = "MainActivity";

    public InternetCheckService(Context mContext, String activity) {
        this.mContext = mContext;
        Activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        String status = NetworkUtil.getNetWorkState(context);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_isconnect_wifi, null);
        Button btnRetry = view.findViewById(R.id.btnRetry);
        builder.setView(view);
        final Dialog dialog = builder.create();

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (Activity.equals("MainActivity")) {
                    intent = new Intent(mContext, MainActivity.class);
                } else {
                    intent = new Intent(mContext, HomeActivity.class);
                }
                mContext.startActivity(intent);
            }
        });
        if (status.isEmpty() || status.equals("No internet")) {
            dialog.show();
        }
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }
}
