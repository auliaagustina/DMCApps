package com.example.labta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    ImageView imageViewShowPassword;


    FirebaseAuth auth;

    private boolean isPasswordVisible = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.RidirectTextLogin);
        signupButton = findViewById(R.id.signup_button);
        imageViewShowPassword = findViewById(R.id.imageViewShowPassword);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = signupEmail.getText().toString();
                String pass = signupPassword.getText().toString();


                if (!isValidEmail(user)) {
                    signupEmail.setError("Email tidak boleh kosong");
                }
                if (!isValidPassword(pass)) {
                    signupPassword.setError("Password minamal 6 karakter");
                } else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });

        imageViewShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tooglePasswordVisibility();
            }
        });

    }

    private boolean isValidEmail(String user){
        return Patterns.EMAIL_ADDRESS.matcher(user).matches();
    }
    private boolean isValidPassword(String pass){
        String pattern = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{6,}$";
        return pass.matches(pattern);
    }


    private void tooglePasswordVisibility() {
        if (isPasswordVisible){
            signupPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageViewShowPassword.setImageResource(R.drawable.eye);
        } else {
            signupPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageViewShowPassword.setImageResource(R.drawable.hide_password);
        }
        isPasswordVisible = !isPasswordVisible;
    }
}






