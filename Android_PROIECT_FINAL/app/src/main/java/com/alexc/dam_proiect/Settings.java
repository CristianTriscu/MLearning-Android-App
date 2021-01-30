package com.alexc.dam_proiect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Switch switchDayNight;
    public static final String KEY_ISNIGHTMODE="isNightMode";
    public static final String MyPREFERENCES="nightModePrefs";
    SharedPreferences sharedPreferences;
    EditText firstName;
    EditText lastName;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_mode);
         final ImageView img=findViewById(R.id.imageViewDM);
        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        firstName=findViewById(R.id.editTextTextPersonName2);
        lastName=findViewById(R.id.editTextTextPersonName3);
        radioGroup=findViewById(R.id.radioGroup);

        RadioButton rb=findViewById(R.id.radioButton1);
        RadioButton rb2=findViewById(R.id.radioButton2);

        if(sharedPreferences.getString("sex","not provided").equals("M"))

            rb.setChecked(true);
            else

            rb2.setChecked(true);



        firstName.setText(sharedPreferences.getString("firstName","not provided"));
        lastName.setText(sharedPreferences.getString("lastName","not provided"));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton=(RadioButton)group.findViewById(checkedId);
                String sex =checkedRadioButton.getText().toString();

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("sex",sex);
                    editor.apply();


            }
        });


        //EditText firstName=findViewById(R.id.editTextTextPersonName4);

        Button setName=findViewById(R.id.setName);
        Button setLName=findViewById(R.id.setLname);

        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("firstName",firstName.getText().toString());
                editor.apply();
                Toast.makeText(Settings.this,getString(R.string.Done), Toast.LENGTH_LONG).show();

            }
        });

        setLName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("lastName",lastName.getText().toString());
                editor.apply();
                Toast.makeText(Settings.this,getString(R.string.Done), Toast.LENGTH_LONG).show();
            }
        });




        switchDayNight=findViewById(R.id.sMode);
        checknightModeActivated();
        switchDayNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    img.setImageResource(R.drawable.icons8_night_100);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    saveNightModeState(true);

                    recreate();
                }
                else{
                    img.setImageResource(R.drawable.icons8_smiling_sun_100);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    saveNightModeState(false);

                    switchDayNight.setText(R.string.light_mode);
                    recreate();
                }
            }
        });


    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(KEY_ISNIGHTMODE,nightMode);

        editor.apply();

    }

    public void checknightModeActivated(){
        final ImageView img=findViewById(R.id.imageViewDM);
        if(sharedPreferences.getBoolean(KEY_ISNIGHTMODE,false)){
            switchDayNight.setChecked(true);
            img.setImageResource(R.drawable.icons8_night_100);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            switchDayNight.setChecked(false);
            img.setImageResource(R.drawable.icons8_smiling_sun_100);
            switchDayNight.setText(R.string.light_mode);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}