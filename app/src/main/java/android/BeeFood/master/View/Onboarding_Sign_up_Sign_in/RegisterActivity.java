package android.BeeFood.master.View.Onboarding_Sign_up_Sign_in;

import android.BeeFood.master.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText edtEmail,edtPassword,edtPasswordNhaplai;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
        AnhXa();
        evenClick();
    }

    private void AnhXa() {
                btnEmailTaoTK = findViewById(R.id.btnRegister);
                edtEmail=findViewById(R.id.edtDangKyEmail);
                edtPassword=findViewById(R.id.inputPasswordDangKy);
                edtPasswordNhaplai=findViewById(R.id.inputConformPassword);
         mAuth=FirebaseAuth.getInstance();
    }

    private void evenClick() {
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
