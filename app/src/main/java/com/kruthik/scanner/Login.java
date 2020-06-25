package com.kruthik.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText loginuseername;
    EditText loginpassword;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginuseername=findViewById(R.id.loginusername);
        loginpassword=findViewById(R.id.loginpassword);
        login=findViewById(R.id.login);
        SharedPreferences sharedpreferences=getSharedPreferences("login",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (sharedpreferences.contains("name")) {
            Intent i = new Intent(Login.this,listactivty.class);


            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(validate()){


                    verifylogin(loginuseername.getText().toString(),loginpassword.getText().toString().trim());
                }
            }
        });
    }

    public void verifylogin(final String username, final String password){
        if(username.equals("kruthik")&& password.equals("admin")){
            Intent i = new Intent(Login.this,listactivty.class);
            SharedPreferences sharedpreferences=getSharedPreferences("login",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("name", username);
            editor.commit();
            i.putExtra("email",username);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }else if(username.equals("pratheek")&& password.equals("admin")){
            Intent i = new Intent(Login.this,listactivty.class);
            SharedPreferences sharedpreferences=getSharedPreferences("login",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("name", username);
            editor.commit();
            i.putExtra("email",username);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }else{
            Toast.makeText(this,"enter correct deatils",Toast.LENGTH_LONG).show();
        }

    }
    public boolean validate() {
        boolean valid = true;

        String email = loginuseername.getText().toString();
        String password = loginpassword.getText().toString();

        if (email.isEmpty() ) {
            loginuseername.setError("enter a valid email address");
            valid = false;
        } else {
            loginuseername.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            loginpassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            loginpassword.setError(null);
        }

        return valid;
    }
}