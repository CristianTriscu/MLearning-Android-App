package com.alexc.dam_proiect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReminderAdapter extends ArrayAdapter<Reminder> {

    private Context context;
    private int resource;
    private List<Reminder> reminderList;
    private LayoutInflater layoutInflater;

    public ReminderAdapter(@NonNull Context context, int resource, List<Reminder> vehicleList, LayoutInflater layoutInflater) {
        super(context, resource, vehicleList);
        this.context = context;
        this.resource = resource;
        this.reminderList = vehicleList;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View view = layoutInflater.inflate(resource, parent, false);
        Reminder reminder = reminderList.get(position);
        if (reminder != null) {
            TextView tv1 = view.findViewById(R.id.messagetext2);
            tv1.setText("DATE:" +
                    reminder.getDate().toString());


            TextView tv2 = view.findViewById(R.id.coursetext2);
            tv2.setText("COURSE: " + reminder.getCourse_type().toString());

            TextView tv3 = view.findViewById(R.id.datetext2);
            tv3.setText("MESSAGE: " + reminder.getMessage().toString());

        }


        return view;
    }
}
