package com.safia.lamzone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.safia.lamzone.view.UserRecyclerView;

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity {
    private EditText mEditEmail;
    private Button mBtnAddEmail, mBtnSaveUser;
    String name, emailPattern;
    ArrayList<String> mParticipants = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    public static final String BUNDLE_EXTRA_NAME = "BUNDLE_EXTRA_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        setUpViews();
        setEditEmailWatcher();
        setBtnAddEmail();
        displayRV();
        setBtnSaveUser();
    }

    public void setUpViews() {
        mEditEmail = findViewById(R.id.edit_text_user_email);
        mBtnAddEmail = findViewById(R.id.btn_new_user);
        mBtnSaveUser = findViewById(R.id.btn_save_user);
        mParticipants = getIntent().getStringArrayListExtra(BUNDLE_EXTRA_NAME);
    }

    public void setBtnAddEmail() {
        mBtnAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mParticipants.add(mEditEmail.getText().toString());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setBtnSaveUser() {
        mBtnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra(BUNDLE_EXTRA_NAME,mParticipants);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void setEditEmailWatcher() {
        mEditEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                name = mEditEmail.getText().toString();

                if (name.trim().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), R.string.valid_email_address, Toast.LENGTH_SHORT).show();
                    mBtnAddEmail.setEnabled(true);
                } else {
                    mEditEmail.setError(getString(R.string.invalid_email_address));
                    mBtnAddEmail.setEnabled(false);
                }
            }
        });
    }

    public void displayRV() {
        RecyclerView rv = findViewById(R.id.user_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter = new UserRecyclerView(mParticipants);
        rv.setAdapter(mAdapter);
    }
}
