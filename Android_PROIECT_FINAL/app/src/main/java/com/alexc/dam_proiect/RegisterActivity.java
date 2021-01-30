package com.alexc.dam_proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    EditText User;
    EditText Password;
    private FirebaseDatabase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_register);
        firebase=FirebaseDatabase.getInstance();

        User = findViewById(R.id.editUser1);
        Password = findViewById(R.id.editPassword1);




        Button registerBtn=findViewById(R.id.btnRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = User.getText().toString();
                String password = Password.getText().toString();
                if (username.isEmpty() || username.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.please_enter_data, Toast.LENGTH_LONG).show();

                } else {




                    User usr = new User();
                    usr.setId("14");
                    usr.setPassword(password);
                    usr.setUsername(username);

                    Toast.makeText(RegisterActivity.this, R.string.register_succes, Toast.LENGTH_LONG).show();
                    writeUserInDatabase(usr);

                }

            }
        });


    }

    private  void writeUserInDatabase(final User user){
        final DatabaseReference myRef=firebase.getReference("learndroid-9bc33-default-rtdb");
        myRef.keepSynced(true);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.setId(myRef.child("learndroid-9bc33-default-rtdb").push().getKey());
                myRef.child("Useri").child(user.getId()).setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}