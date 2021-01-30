package com.alexc.dam_proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedBackActivity extends AppCompatActivity {

    private FirebaseDatabase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebase=FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        final RatingBar rb=findViewById(R.id.ratingBar2);

        final Spinner spinner =findViewById(R.id.spinner2);
        String[] values = {"GENERAL", "BUG REPORT", "SUGGESTION", "OTHER"};

        String[] values2 = new String[Reminders.COURSE_TYPE.values().length];
        int i = 0;
        for(Reminders.COURSE_TYPE curs: Reminders.COURSE_TYPE.values())
            values2[i++] = curs.toString();

        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                values);

        spinner.setAdapter(adaptor);

        final EditText fbContent=findViewById(R.id.editTextTextPersonName);

        final TextView textView=findViewById(R.id.textView4);
        String username = (String) getIntent().getSerializableExtra("username");

        //textView.setText(user1.getUsername());

        textView.setText("Hello "+username+"!");
        /*to do*/

        final Button button=findViewById(R.id.sendFeedbackBTN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = fbContent.getText().toString();
                String type= spinner.getSelectedItem().toString();
                float rating=rb.getRating();

                FeedBack feedBack=new FeedBack("1",content,type,rating);
                writeFeedbackInDatabase(feedBack);
                Toast.makeText(getApplicationContext(),"FEEDBACK WAS SENT",Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void writeFeedbackInDatabase(final FeedBack fb){
        final DatabaseReference myRef=firebase.getReference("learndroid-9bc33-default-rtdb");
        myRef.keepSynced(true);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fb.setId(myRef.child("learndroid-9bc33-default-rtdb").push().getKey());
                myRef.child("FeedBack").child(fb.getId()).setValue(fb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}