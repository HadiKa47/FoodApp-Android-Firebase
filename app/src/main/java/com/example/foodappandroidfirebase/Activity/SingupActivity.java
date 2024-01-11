package com.example.foodappandroidfirebase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodappandroidfirebase.R;
import com.example.foodappandroidfirebase.databinding.ActivitySingupBinding;

public class SingupActivity extends BaseActivity {
    ActivitySingupBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVaribale();
    }

    private void setVaribale() {
        binding.singupBtn.setOnClickListener(v -> {
            String email=binding.userEdt.getText().toString();
            String password=binding.passEdt.getText().toString();

            if(password.length()<6)
            {
                Toast.makeText(SingupActivity.this, "Your Password Must be 6 Characters", Toast.LENGTH_SHORT).show();
                return;
            }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SingupActivity.this, task -> {
                if(task.isSuccessful())
                {
                    Log.i(TAG, "onComplete: ");
                    startActivity(new Intent(SingupActivity.this, MainActivity.class));
                }
                else
                {
                    Log.i(TAG, "failure: "+task.getException());
                    Toast.makeText(SingupActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
                });
        });
        TextView LoginBtn = (TextView) findViewById(R.id.LoginBtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingupActivity.this, LoginActivity.class));
            }
        });
    }
}