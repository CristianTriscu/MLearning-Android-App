package com.alexc.dam_proiect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Reminders extends AppCompatActivity {

    public static final int REQUEST_CODE=200;
    public static final int REQUEST_CODE1=2001;
    public static final String ADD_REMINDER = "adaugaReminder";
    public static final String EDIT_REMINDER = "adaugaReminder";


    public enum COURSE_TYPE {
        JAVA_COURSE, ANDROID_COURSE, XML_COURSE
    }

    ReminderList rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);


        final Spinner spinner = findViewById(R.id.spinnerCourses);
        String[] values = {"JAVA COURSE", "ANDROID COURSE", "XML COURSE"};

        String[] values2 = new String[COURSE_TYPE.values().length];
        int i = 0;
        for (COURSE_TYPE curs : COURSE_TYPE.values())
            values2[i++] = curs.toString();

        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                values2);

        spinner.setAdapter(adaptor);


        final EditText datePicker = findViewById(R.id.editTextDate);
        //datePicker.setText((CharSequence) DateFormat.getDateInstance());
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println(formattedDate);

        datePicker.setText(formattedDate);


        Button button1 = findViewById(R.id.setTheRemindersBTN);
        Button button2 = findViewById(R.id.ManageRemindersBTN);
        final EditText Message=findViewById(R.id.editTextMessage);
        final Spinner spinnerCourses = findViewById(R.id.spinnerCourses);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Message.getText().toString().isEmpty())
                    Message.setError(getString(R.string.typeamessage));
                else if (datePicker.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), R.string.typeadate, Toast.LENGTH_LONG).show();
                else {


                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

                    try {
                        sdf.parse(datePicker.getText().toString());

                        String Massage = Message.getText().toString();
                        String Date = new String(datePicker.getText().toString());
                        COURSE_TYPE2 courseType = COURSE_TYPE2.valueOf(spinnerCourses.getSelectedItem().toString().toUpperCase());


                        Reminder reminder = new Reminder(Massage, Date, courseType);


                        MainActivity.arrayList.add(reminder);

                        Intent intent = new Intent(getApplicationContext(), ReminderList.class);


                        intent.putExtra("Reminder", MainActivity.arrayList);

                        startActivityForResult(intent,REQUEST_CODE1);


                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), R.string.invalidDate,
                                Toast.LENGTH_LONG).show();

                    }


                }

                //


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reminders.this, ReminderList.class);
                intent.putExtra("Reminder", MainActivity.arrayList);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
        //Reminder reminder = (Reminder) data.getSerializableExtra("edit");


        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK ) {
            ArrayList<Reminder> lst = (ArrayList<Reminder>) data.getSerializableExtra("edit");
            final int index = data.getIntExtra("index", 0);
            //Toast.makeText(getApplicationContext(), lst.toString(), Toast.LENGTH_LONG).show();
            final EditText editText = findViewById(R.id.editTextMessage);
            final EditText editText1=findViewById(R.id.editTextDate);
            final Spinner spinner=findViewById(R.id.spinnerCourses);
            editText.setText(lst.get(index).getMessage().toString());
            editText1.setText((lst.get(index).getDate().toString()));


            Button button1=findViewById(R.id.setTheRemindersBTN);

            button1.setText(R.string.edit_the_reminder);
            button1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (editText.getText().toString().isEmpty())
                        editText.setError(getString(R.string.typeamessage));
                    else if (editText1.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(), R.string.typeadate, Toast.LENGTH_LONG).show();
                    else {
                        //creare obiect Vehicle

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

                        try {
                            sdf.parse(editText1.getText().toString());

                            String Massage = editText.getText().toString();
                            String Date = new String(editText1.getText().toString());
                            COURSE_TYPE2 courseType = COURSE_TYPE2.valueOf(spinner.getSelectedItem().toString().toUpperCase());





                            MainActivity.arrayList.get(index).setMessage(Massage);
                            MainActivity.arrayList.get(index).setDate(Date);
                            MainActivity.arrayList.get(index).setCourse_type(courseType);


                            Toast.makeText(getApplicationContext(), R.string.reminder_edited,Toast.LENGTH_LONG).show();



                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), R.string.invalidDate,
                                    Toast.LENGTH_LONG).show();

                        }


                    }

                    //


                }
            });


        }
        if(requestCode==REQUEST_CODE1 && resultCode==RESULT_OK && data!=null) {
            ArrayList<Reminder> lst = (ArrayList<Reminder>) data.getSerializableExtra("edit");
            final int index = data.getIntExtra("index", 0);
            //Toast.makeText(getApplicationContext(), lst.toString(), Toast.LENGTH_LONG).show();
            final EditText editText = findViewById(R.id.editTextMessage);
            final EditText editText1=findViewById(R.id.editTextDate);
            final Spinner spinner=findViewById(R.id.spinnerCourses);
            editText.setText(lst.get(index).getMessage().toString());
            editText1.setText((lst.get(index).getDate().toString()));


            Button button1=findViewById(R.id.setTheRemindersBTN);
            button1.setText(R.string.edit_the_reminder);
            button1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (editText.getText().toString().isEmpty())
                        editText.setError(getString(R.string.typeamessage));
                    else if (editText1.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(), R.string.typeadate, Toast.LENGTH_LONG).show();
                    else {
                        //creare obiect Vehicle

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

                        try {
                            sdf.parse(editText1.getText().toString());

                            String Massage = editText.getText().toString();
                            String Date = new String(editText1.getText().toString());
                            COURSE_TYPE2 courseType = COURSE_TYPE2.valueOf(spinner.getSelectedItem().toString().toUpperCase());





                            MainActivity.arrayList.get(index).setMessage(Massage);
                            MainActivity.arrayList.get(index).setDate(Date);
                            MainActivity.arrayList.get(index).setCourse_type(courseType);


                            Toast.makeText(getApplicationContext(), R.string.reminder_edited,Toast.LENGTH_LONG).show();



                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), R.string.invalidDate,
                                    Toast.LENGTH_LONG).show();

                        }


                    }

                    //


                }
            });


        }


    }
}
