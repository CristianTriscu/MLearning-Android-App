package com.alexc.dam_proiect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AndroidCourseActivity extends AppCompatActivity {

    Button startAndroid1Btn;
    Button startAndroid2Btn;
    Button startAndroidQuizBtn;

    Button resetAndroid1Btn;
    Button resetAndroid2Btn;
    Button resetAndroidQuizBtn;
    Button resetAndroidTotalBtn;

    ProgressBar pbAndroid1;
    ProgressBar pbAndroid2;
    ProgressBar pbAndroidQuiz;
    ProgressBar pbAndroidTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_course);

        startAndroid1Btn = findViewById(R.id.startAndroid1Btn);
        startAndroid2Btn = findViewById(R.id.startAndroid2Btn);
        startAndroidQuizBtn = findViewById(R.id.startAndroidQuizBtn);

        resetAndroid1Btn = findViewById(R.id.resetAndroid1Btn);
        resetAndroid2Btn = findViewById(R.id.resetAndroid2Btn);
        resetAndroidQuizBtn = findViewById(R.id.resetAndroidQuizBtn);
        resetAndroidTotalBtn = findViewById(R.id.resetAndroidTotalBtn);

        pbAndroid1 = findViewById(R.id.pbAndroid1);
        pbAndroid2 = findViewById(R.id.pbAndroid2);
        pbAndroidQuiz = findViewById(R.id.pbAndroidQuiz);
        pbAndroidTotal = findViewById(R.id.pbAndroidTotal);

        startAndroid1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isJava1 = false;
                MainActivity.isJava2 = false;
                MainActivity.isJavaQuiz = false;
                MainActivity.isAndroid1 = true;
                MainActivity.isAndroid2 = false;
                MainActivity.isAndroidQuiz = false;
                MainActivity.isXml1 = false;
                MainActivity.isXml2 = false;
                MainActivity.isXmlQuiz = false;


                Intent intent = new Intent(AndroidCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        startAndroid2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isJava1 = false;
                MainActivity.isJava2 = false;
                MainActivity.isJavaQuiz = false;
                MainActivity.isAndroid1 = false;
                MainActivity.isAndroid2 = true;
                MainActivity.isAndroidQuiz = false;
                MainActivity.isXml1 = false;
                MainActivity.isXml2 = false;
                MainActivity.isXmlQuiz = false;

                Intent intent = new Intent(AndroidCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        startAndroidQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isJava1 = false;
                MainActivity.isJava2 = false;
                MainActivity.isJavaQuiz = false;
                MainActivity.isAndroid1 = false;
                MainActivity.isAndroid2 = false;
                MainActivity.isAndroidQuiz = true;
                MainActivity.isXml1 = false;
                MainActivity.isXml2 = false;
                MainActivity.isXmlQuiz = false;

                Intent intent = new Intent(AndroidCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        resetAndroid1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AndroidCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageAndroid1 = 1;
                                MainActivity.trackAndroid1 = 0;

                                pbAndroid1.setProgress(MainActivity.trackAndroid1);
                                //Toast.makeText(getApplicationContext(), "Progress reset for Android Lesson 1", Toast.LENGTH_LONG);
                            }
                        });
                builder.setNegativeButton(R.string.No,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

        resetAndroid2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AndroidCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageAndroid2 = 1;
                                MainActivity.trackAndroid2 = 0;

                                pbAndroid2.setProgress(MainActivity.trackAndroid2);
                                //Toast.makeText(getApplicationContext(), "Progress reset for Android Lesson 2", Toast.LENGTH_LONG);
                            }
                        });
                builder.setNegativeButton(R.string.No,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

        resetAndroidQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AndroidCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageAndroidQuiz = 1;
                                MainActivity.trackAndroidQuiz = 0;

                                pbAndroidQuiz.setProgress(MainActivity.trackAndroidQuiz);
                                //Toast.makeText(getApplicationContext(), "Progress reset for Android Quiz", Toast.LENGTH_LONG);
                            }
                        });
                builder.setNegativeButton(R.string.No,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

        resetAndroidTotalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AndroidCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageAndroid1 = 1;
                                MainActivity.stageAndroid2 = 1;
                                MainActivity.stageAndroidQuiz = 1;
                                MainActivity.trackAndroid1 = 0;
                                MainActivity.trackAndroid2 = 0;
                                MainActivity.trackAndroidQuiz = 0;
                                MainActivity.trackAndroidTotal = 0;

                                pbAndroid1.setProgress(MainActivity.trackAndroid1);
                                pbAndroid2.setProgress(MainActivity.trackAndroid2);
                                pbAndroidQuiz.setProgress(MainActivity.trackAndroidQuiz);
                                pbAndroidTotal.setProgress(MainActivity.trackAndroidTotal);
                                //Toast.makeText(getApplicationContext(), "Progress reset for Android Course", Toast.LENGTH_LONG);
                            }
                        });
                builder.setNegativeButton(R.string.No,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        MainActivity.isJava1 = false;
        MainActivity.isJava2 = false;
        MainActivity.isJavaQuiz = false;
        MainActivity.isAndroid1 = false;
        MainActivity.isAndroid2 = false;
        MainActivity.isAndroidQuiz = false;
        MainActivity.isXml1 = false;
        MainActivity.isXml2 = false;
        MainActivity.isXmlQuiz = false;

        MainActivity.trackAndroidTotal = (MainActivity.trackAndroid1 + MainActivity.trackAndroid2 + MainActivity.trackAndroidQuiz)/3;

        pbAndroid1.setProgress(MainActivity.trackAndroid1);
        pbAndroid2.setProgress(MainActivity.trackAndroid2);
        pbAndroidQuiz.setProgress(MainActivity.trackAndroidQuiz);
        pbAndroidTotal.setProgress(MainActivity.trackAndroidTotal);


    }
}