package com.alexc.dam_proiect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button LogInButton;
    Button RegisterButton;
    //Button LogInWithGoggleButton; /*to do in vacanta */
    EditText User;
    EditText Password;
    TextView register;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("learndroid-9bc33-default-rtdb");

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_login);



        LogInButton = findViewById(R.id.button2);
        RegisterButton = findViewById(R.id.btnRegister);
        User = findViewById(R.id.editUser);
        Password = findViewById(R.id.editPassword);

        User.setText("test");
        Password.setText("test");

        register=findViewById(R.id.tvregister);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });




        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String inputUser = User.getText().toString();
                final String inputPassword = Password.getText().toString();

                if (inputUser.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, R.string.please_enter_data, Toast.LENGTH_LONG).show();

                } else {


                    myRef.child("Useri").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                for (DataSnapshot dn : snapshot.getChildren()) {
                                    User user = dn.getValue(User.class);

                                    if (user.getUsername().equals(inputUser) && user.getPassword().equals(inputPassword)) {

                                        Toast.makeText(LoginActivity.this, R.string.login_successful, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                        intent.putExtra("User",  user);
                                        startActivity(intent);
                                        finish();

                                        break;
                                    } else {
                                        //Toast.makeText(LoginActivity.this, "Credentials are not correct!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

            }
        });

    }
}

