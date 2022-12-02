package android.BeeFood.master.view.profile.profile_update;

import android.BeeFood.master.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Profile_Update_Activity extends AppCompatActivity {
    private ImageView img_back;
    private ImageView img_profile_update_MyLaction;
    private EditText edt_profile_update_FullName,edt_profile_Update_NickName,edt_profile_update_DateOfBirth,edt_profile_update_Email,edt_profile_update_location;
    private Spinner spn_profile_spinner_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        AnhXa();
        setSelect_edt();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Gender");
        spinnerArray.add("Nữ");
        spinnerArray.add("Nam");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spn_profile_spinner_gender.setAdapter(adapter);


        //lấy địa chỉ người dùng 0.5
        img_profile_update_MyLaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_profile_update_location.setText("địa chỉ");
            }
        });
        //

    }

    public void AnhXa(){
        img_back = findViewById(R.id.img_accountsetup_profile_previous);
        edt_profile_update_FullName = findViewById(R.id.profile_update_FullName);
        edt_profile_Update_NickName = findViewById(R.id.profile_Update_NickName);
        edt_profile_update_DateOfBirth = findViewById(R.id.profile_update_DateOfBirth);
        edt_profile_update_Email = findViewById(R.id.profile_update_Email);
        edt_profile_update_location = findViewById(R.id.profile_update_location);
        spn_profile_spinner_gender = findViewById(R.id.profile_spinner_gender);
        img_profile_update_MyLaction = findViewById(R.id.profile_update_MyLaction);
    }

    public void setSelect_edt(){

        edt_profile_update_FullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_update_FullName.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_update_FullName.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edt_profile_Update_NickName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_Update_NickName.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_Update_NickName.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edt_profile_update_DateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_update_DateOfBirth.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_update_DateOfBirth.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edt_profile_update_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_update_Email.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_update_Email.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edt_profile_update_location.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_profile_update_location.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_profile_update_location.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });
    }
}