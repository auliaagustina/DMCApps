package com.example.labta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView signupRedirect, signupForgotPaswword;
    ImageView iconPassword;
    RadioButton radioButtonAdmin,radioButtonRelawan,radioButtonMasyarakat;

    private boolean isPasswordVisible = false;

    private RadioGroup userTypeRadioGroup;
    private FirebaseAuth mAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail= findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        signupRedirect = findViewById(R.id.signupRidirectText);
        signupForgotPaswword = findViewById(R.id.login_forgotpassword);
        iconPassword = findViewById(R.id.passwordIcon);
        loginButton  = findViewById(R.id.login_button);

        userTypeRadioGroup = findViewById(R.id.RadioGroupType);

        radioButtonAdmin =findViewById(R.id.radioButtonAdmin);
        radioButtonRelawan = findViewById(R.id.radioButtonRelawan);
        radioButtonMasyarakat = findViewById(R.id.radioButtonMasyarakat);


        mAuth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();
                int selectedId = userTypeRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String userType = radioButton.getText().toString();
                
                loginUser(email,password,userType);

            }
        });

        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        signupForgotPaswword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot,null);

                EditText emailBox = dialogView.findViewById(R.id.emailBox);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();

                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LoginActivity.this,"Masukkan email saat registrasi",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this,"Silahkan cek email Anda",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(LoginActivity.this,"Gagal dikirim",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });
                if (dialog.getWindow() !=null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();

            }
        });
        iconPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordVisibility();
            }
        });

    }

    private void loginUser(String email, String password, final String userType) {

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            //login sukses, perfom action
                            if (userType.equals("Admin")){
                                Intent nextActvity = new Intent(LoginActivity.this,MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("VAL",1);
                                nextActvity.putExtras(bundle);
                                startActivity(nextActvity);


                            } else if (userType.equals("Masyarakat")){
                               Intent nextActivity = new Intent(LoginActivity.this,MainActivity.class);
                               Bundle bundle = new Bundle();
                               bundle.putInt("VAL",2);
                               nextActivity.putExtras(bundle);
                               startActivity(nextActivity);

                            } else if (userType.equals("Relawan")){
                                Intent nextActivity = new Intent(LoginActivity.this,MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("VAL",3);
                                nextActivity.putExtras(bundle);
                                startActivity(nextActivity);

                            }
                        } else{
                            Toast.makeText(LoginActivity.this,"Autentikasi gagal",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//        userTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                for (int i = 0; i < userTypeRadioGroup.getChildCount(); i++){
//                    RadioButton radioButton = (RadioButton) userTypeRadioGroup.getChildAt(i);
//                    if (radioButton.getId() == checkedId){
//                        radioButton.setVisibility(View.VISIBLE);
//                        updateMenuVisibility(radioButton.getText().toString());
//                    } else {
//                        radioButton.setVisibility(View.GONE);
//                    }
//
//                }
//            }
//        });
    }

//    public void updateMenuVisibility(String userType) {
//
//        ImageView menuLaporan = findViewById(R.id.menuLaporAktivitas);
//        ImageView menuRekruitmen = findViewById(R.id.menuRekruitmen);
//        ImageView menuPeta = findViewById(R.id.menuPeta);
//        ImageView menuBencana = findViewById(R.id.menuLaporBencana);
//
//        if (userType.equals("Admin")){
//
//            menuLaporan.setVisibility(View.VISIBLE);
//            menuRekruitmen.setVisibility(View.VISIBLE);
//            menuPeta.setVisibility(View.VISIBLE);
//            menuBencana.setVisibility(View.VISIBLE);
//        } else if(userType.equals("Masyarakat")){
//            menuLaporan.setVisibility(View.GONE);
//            menuRekruitmen.setVisibility(View.VISIBLE);
//            menuPeta.setVisibility(View.VISIBLE);
//            menuBencana.setVisibility(View.VISIBLE);
//        } else if (userType.equals("Relawan")){
//            menuLaporan.setVisibility(View.VISIBLE);
//            menuRekruitmen.setVisibility(View.GONE);
//            menuPeta.setVisibility(View.VISIBLE);
//            menuBencana.setVisibility(View.VISIBLE);
//        }
//    }


    private void passwordVisibility() {
        if(isPasswordVisible){
            loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            iconPassword.setImageResource(R.drawable.eye);
        } else{
            loginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            iconPassword.setImageResource(R.drawable.hide_password);
        }
        isPasswordVisible = !isPasswordVisible;
    }
}