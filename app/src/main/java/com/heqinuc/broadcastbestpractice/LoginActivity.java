package com.heqinuc.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText account = (EditText) findViewById(R.id.account);
        final EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);

        boolean isRemember = preferences.getBoolean("remember_password", false);
        if (isRemember) {
            //将账号和密码设置到文本上
            String accountStirng = preferences.getString("account", "");
            String passwordString = preferences.getString("password", "");
            account.setText(accountStirng);
            password.setText(passwordString);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountString = account.getText().toString();
                String passwordString = password.getText().toString();
                if (accountString.equals("admin")&& passwordString.equals("123456")) {
                    editor = preferences.edit();
                    if (rememberPass.isChecked()) {
                        //检查是否选中复选框
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",accountString);
                        editor.putString("password",passwordString);
                    }else {
                        editor.clear();
                    }
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"account or password is invald",Toast.LENGTH_SHORT).show();
                }
            }
        });






    }
}
