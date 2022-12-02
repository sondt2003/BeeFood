package android.BeeFood.master.View.Onboarding_Sign_up_Sign_in;

import android.BeeFood.master.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Button btnEmailTaoTK;
    ImageView img_register_back;
    EditText edtEmail,edtPassword,edtPasswordNhaplai,edt_inputUsername;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        AnhXa();
        evenClick();


        //set onfocus edt
        edt_inputUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edt_inputUsername.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edt_inputUsername.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });

        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edtEmail.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edtEmail.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });
        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edtPassword.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edtPassword.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });
        edtPasswordNhaplai.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    edtPasswordNhaplai.setBackgroundResource(R.drawable.bg_edt_login_select);
                }else{
                    edtPasswordNhaplai.setBackgroundResource(R.drawable.bg_edt_login_noselect);
                }
            }
        });
        //
    }

    private void AnhXa() {
        edt_inputUsername = findViewById(R.id.inputUsername);
        btnEmailTaoTK = findViewById(R.id.btnRegister);
        img_register_back = findViewById(R.id.register_back);
        edtEmail=findViewById(R.id.edtDangKyEmail);
        edtPassword=findViewById(R.id.inputPasswordDangKy);
        edtPasswordNhaplai=findViewById(R.id.inputConformPassword);
        mAuth=FirebaseAuth.getInstance();
    }

    private void evenClick() {

        img_register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnEmailTaoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim(), password = edtPassword.getText().toString().trim(),passwwordnhaplai=edtPasswordNhaplai.getText().toString().trim();
                if (email.trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
                    edtEmail.requestFocus();
                } else if (password.trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập Password", Toast.LENGTH_SHORT).show();
                    edtPassword.requestFocus();
                }  else if (passwwordnhaplai.trim().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng nhập Password nhập lại", Toast.LENGTH_SHORT).show();
                    edtPassword.requestFocus();
                } else if (!passwwordnhaplai.trim().equalsIgnoreCase(password.trim())) {
                    Toast.makeText(RegisterActivity.this, "password và password nhập lại không giống nhau", Toast.LENGTH_SHORT).show();
                    edtPassword.requestFocus();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Tạo tài Khoản thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Tạo tài Khoản thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
