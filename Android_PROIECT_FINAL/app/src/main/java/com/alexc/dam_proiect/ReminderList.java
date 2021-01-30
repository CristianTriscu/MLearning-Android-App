package com.alexc.dam_proiect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReminderList extends AppCompatActivity {


    public ListView listView;

    public static final String EDIT_REMINDER="edit_reminder";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);

        Log.d("lifecycle", "Apel metoda onCreate()");
        listView = findViewById(R.id.lv2);

        MainActivity.arrayList = null;
        MainActivity.arrayList = (ArrayList<Reminder>) getIntent().getSerializableExtra("Reminder");
        listView.invalidateViews();

        if (MainActivity.arrayList != null) {


            final ReminderAdapter adapter = new ReminderAdapter(getApplicationContext(), R.layout.tv_for_lv,
                    MainActivity.arrayList, getLayoutInflater()) {
                @Override
                public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);


                    Reminder reminder11 = MainActivity.arrayList.get(position);

                    //prelucrari la nivelul campurilor
                    return view;
                }
            };
            listView.setAdapter(adapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {

                    final Reminder reminder = MainActivity.arrayList.get(position);


                    AlertDialog dialog = new AlertDialog.Builder(ReminderList.this)
                            .setTitle(R.string.delete_confirmation)
                            .setMessage(R.string.dyrwtdtr).
                                    setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(getApplicationContext(), R.string.nothing_was_deleted,
                                                    Toast.LENGTH_LONG).show();
                                            dialogInterface.cancel();
                                        }
                                    }).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    MainActivity.arrayList.remove(reminder);

                                    //Toast.makeText(getApplicationContext(), MainActivity.arrayList.toString(),
                                    // Toast.LENGTH_LONG).show();
                                    adapter.remove(reminder);

                                    listView.invalidateViews();

                                    Toast.makeText(getApplicationContext(), getString(R.string.reminder_deleted) + reminder.toString(),
                                            Toast.LENGTH_LONG).show();
                                    dialogInterface.cancel();
                                }
                            }).create();

                    dialog.show();


                    return true;
                }
            });


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ArrayList<Reminder> lst=MainActivity.arrayList;
                    Intent intent=new Intent();
                    intent.putExtra("edit", lst);
                    intent.putExtra("index", position);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }


    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.d("lifecycle", "Apel metoda onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("lifecycle", "Apel metoda onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("lifecycle", "Apel metoda onPause() in remidnerList");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("lifecycle", "Apel metoda onStop() in reminderList");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("lifecycle", "Apel metoda onRestart() in reminderList");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("lifecycle", "Apel metoda onDestroy() in reminderList");
    }


}