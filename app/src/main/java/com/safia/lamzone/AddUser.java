package com.safia.lamzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddUser extends AppCompatActivity {
    private EditText mEditEmail;
    private Button mBtnAddEmail, mBtnSaveUser;
    String name, emailPattern;
    List <String> mParticipants = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    public static final String BUNDLE_EXTRA_NAME = "BUNDLE_EXTRA_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        setUpViews();
        setBtnAddEmail();
        displayRV();
        setBtnSaveUser();
    }

    public void setUpViews() {
        mEditEmail = findViewById(R.id.edit_text_user_email);
        mBtnAddEmail = findViewById(R.id.btn_new_user);
        mBtnSaveUser = findViewById(R.id.btn_save_user);

    }

    public void setBtnAddEmail() {
        mBtnAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mParticipants.add(mEditEmail.getText().toString());
            }
        });
    }

    public void setBtnSaveUser() {
        mBtnSaveUser.setOnClickListener(new View.OnClickListener() {
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
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        name = mEditEmail.getText().toString();

       if (name.trim().matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
          mBtnAddEmail.setEnabled(true);
       } else {
           mEditEmail.setError("Invalid email address");
           mBtnAddEmail.setEnabled(false);
       }
    }

    public void displayRV() {
        RecyclerView rv = findViewById(R.id.user_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter = new UserRecyclerView(mParticipants);
        rv.setAdapter(mAdapter);

    }
}
