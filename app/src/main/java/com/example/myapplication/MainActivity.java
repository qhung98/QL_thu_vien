package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.model.User;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        Button btnLogin = findViewById(R.id.btnLogin);
        final EditText edUsername, edPassword;

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);

        Button btnAddUser = findViewById(R.id.btnAddUser);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("a", "a");
                db.addUser(user);
                Toast.makeText(getBaseContext(), "THEM THANH CONG USER", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(edUsername.getText().toString(), edPassword.getText().toString());
                boolean boo = db.checkLogin(user);
                if(boo){
                    Toast.makeText(getBaseContext(), "DANG NHAP THANH CONG", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Home.class));
                }
                else {
                    Toast.makeText(getBaseContext(), "DANG NHAP THAT BAI", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
