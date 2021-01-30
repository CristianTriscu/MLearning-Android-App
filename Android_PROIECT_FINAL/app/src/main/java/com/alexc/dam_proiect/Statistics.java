package com.alexc.dam_proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);




    }

    public void clickFragment1(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Fragment1()).commit();



    }

    public void clickFragment2(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Fragment2()).commit();
    }
}