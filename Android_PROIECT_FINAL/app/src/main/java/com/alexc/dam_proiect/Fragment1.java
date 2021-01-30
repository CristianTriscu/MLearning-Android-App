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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment1 extends Fragment {

    LinearLayout layout;
    Map<String,Integer> source;


    public float getMedie() {
        float medie=0;
        float nrTotalStelute=0;
        float total=MainActivity.feedBacks.size();

        for (FeedBack fb : MainActivity.feedBacks) {
           nrTotalStelute += fb.getRating();
        }

        medie=nrTotalStelute/total;
        return medie;
    }



    private Map<String,Integer> getSource(List<FeedBack> feedBackList){
        if(feedBackList==null||feedBackList.isEmpty())
            return new HashMap<>();
        else
        {
            Map<String,Integer> results =new HashMap<>();
            for(FeedBack fb :feedBackList) {
                String rating = String.valueOf(fb.getRating()) + " stelute";

                if (results.containsKey(rating))
                    results.put(rating, results.get(rating)+1) ;
                else
                    results.put(rating, 1);
            }
                return results;

            }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv= getView().findViewById(R.id.tv_test);

        tv.setText(" The avarage stars received from users is: "+String.valueOf(getMedie()));
        Log.d("test4","apel onActivityCreate");







        source = getSource(MainActivity.feedBacks);

        layout = getView().findViewById(R.id.layoutBar);
        layout.addView(new BarChartView(getView().getContext(), source));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_1, container, false);



    }
}