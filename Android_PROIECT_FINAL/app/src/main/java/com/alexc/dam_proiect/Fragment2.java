package com.alexc.dam_proiect;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment2 extends Fragment {

    LinearLayout layout;
    Map<String,Integer> source;

    private Map<String,Integer> getSource(List<FeedBack> feedBackList){
        if(feedBackList==null||feedBackList.isEmpty())
            return new HashMap<>();
        else
        {
            Map<String,Integer> results =new HashMap<>();
            for(FeedBack fb :feedBackList) {

                if (results.containsKey(fb.getType()))
                    results.put(fb.getType(), results.get(fb.getType())+1);
                else
                    results.put(String.valueOf(fb.getType()), 1);
            }
            return results;

        }
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("test4","apel onActivityCreate");




        source = getSource(MainActivity.feedBacks);

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : source.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        TextView tv= getView().findViewById(R.id.tv_test2);
         tv.setText("The most frequent category for feedback is: "+String.valueOf(maxEntry.getKey()));


        Log.d("verificare_source",source.toString());
        layout = getView().findViewById(R.id.layoutBar);
        layout.addView(new BarChartView(getView().getContext(), source));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        return inflater.inflate(R.layout.fragment_2, container, false);



    }
}