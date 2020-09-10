package com.safia.lamzone;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddUser extends AppCompatActivity {
    private EditText mEditEmail;
    private Button mBtnAddEmail;
    String name, emailPattern;
    public static final String BUNDLE_EXTRA_NAME = "BUNDLE_EXTRA_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        setUpViews();
        setBtnAddEmail();
    }

    public void setUpViews() {
        mEditEmail = findViewById(R.id.edit_text_user_email);
        mBtnAddEmail = findViewById(R.id.btn_new_user);
    }

    public void setBtnAddEmail() {

        mBtnAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditEmail();
                Intent intent = new Intent();
                intent.putExtra(BUNDLE_EXTRA_NAME, name);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void setEditEmail() {
        name = mEditEmail.getText().toString();
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "enter email address", Toast.LENGTH_SHORT).show();
        } else {
            if (name.trim().matches(emailPattern)) {
                Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
