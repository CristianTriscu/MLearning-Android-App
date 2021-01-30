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

public class JavaCourseActivity extends AppCompatActivity {

    Button startJava1Btn;
    Button startJava2Btn;
    Button startJavaQuizBtn;

    Button resetJava1Btn;
    Button resetJava2Btn;
    Button resetJavaQuizBtn;
    Button resetJavaTotalBtn;

    ProgressBar pbJava1;
    ProgressBar pbJava2;
    ProgressBar pbJavaQuiz;
    ProgressBar pbJavaTotal;

//    public void setLesson(boolean lesson){
//        MainActivity.isJava1 = false;
//        MainActivity.isJava2 = false;
//        MainActivity.isJavaQuiz = false;
//        MainActivity.isAndroid1 = false;
//        MainActivity.isAndroid2 = false;
//        MainActivity.isAndroidQuiz = false;
//        MainActivity.isXml1 = false;
//        MainActivity.isXml2 = false;
//        MainActivity.isXmlQuiz = false;
//
//        lesson = true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_course);

        startJava1Btn = findViewById(R.id.startJava1Btn);
        startJava2Btn = findViewById(R.id.startJava2Btn);
        startJavaQuizBtn = findViewById(R.id.startJavaQuizBtn);

        resetJava1Btn = findViewById(R.id.resetJava1Btn);
        resetJava2Btn = findViewById(R.id.resetJava2Btn);
        resetJavaQuizBtn = findViewById(R.id.resetJavaQuizBtn);
        resetJavaTotalBtn = findViewById(R.id.resetJavaTotalBtn);

        pbJava1 = findViewById(R.id.pbJava1);
        pbJava2 = findViewById(R.id.pbJava2);
        pbJavaQuiz = findViewById(R.id.pbJavaQuiz);
        pbJavaTotal = findViewById(R.id.pbJavaTotal);

        startJava1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setLesson(MainActivity.isJava1);
                MainActivity.isJava1 = true;
                MainActivity.isJava2 = false;
                MainActivity.isJavaQuiz = false;
                MainActivity.isAndroid1 = false;
                MainActivity.isAndroid2 = false;
                MainActivity.isAndroidQuiz = false;
                MainActivity.isXml1 = false;
                MainActivity.isXml2 = false;
                MainActivity.isXmlQuiz = false;




                Intent intent = new Intent(JavaCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        startJava2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setLesson(MainActivity.isJava2);
                MainActivity.isJava1 = false;
                MainActivity.isJava2 = true;
                MainActivity.isJavaQuiz = false;
                MainActivity.isAndroid1 = false;
                MainActivity.isAndroid2 = false;
                MainActivity.isAndroidQuiz = false;
                MainActivity.isXml1 = false;
                MainActivity.isXml2 = false;
                MainActivity.isXmlQuiz = false;

                Intent intent = new Intent(JavaCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        startJavaQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setLesson(MainActivity.isJavaQuiz);
                MainActivity.isJava1 = false;
                MainActivity.isJava2 = false;
                MainActivity.isJavaQuiz = true;
                MainActivity.isAndroid1 = false;
                MainActivity.isAndroid2 = false;
                MainActivity.isAndroidQuiz = false;
                MainActivity.isXml1 = false;
                MainActivity.isXml2 = false;
                MainActivity.isXmlQuiz = false;

                Intent intent = new Intent(JavaCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        resetJava1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JavaCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageJava1 = 1;
                                MainActivity.trackJava1 = 0;

                                pbJava1.setProgress(MainActivity.trackJava1);
                                //Toast.makeText(getApplicationContext(), "Progress reset for Java Lesson 1", Toast.LENGTH_LONG);
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

        resetJava2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JavaCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageJava2 = 1;
                                MainActivity.trackJava2 = 0;

                                pbJava2.setProgress(MainActivity.trackJava2);
                                //Toast.makeText(getApplicationContext(), "Progress reset for Java Lesson 2", Toast.LENGTH_LONG);
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

        resetJavaQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JavaCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageJavaQuiz = 1;
                                MainActivity.trackJavaQuiz = 0;

                                pbJavaQuiz.setProgress(MainActivity.trackJavaQuiz);
                                //Toast.makeText(getApplicationContext(), "Progress reset for Java Quiz", Toast.LENGTH_LONG);
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

        resetJavaTotalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JavaCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageJava1 = 1;
                                MainActivity.stageJava2 = 1;
                                MainActivity.stageJavaQuiz = 1;
                                MainActivity.trackJava1 = 0;
                                MainActivity.trackJava2 = 0;
                                MainActivity.trackJavaQuiz = 0;
                                MainActivity.trackJavaTotal = 0;

                                pbJava1.setProgress(MainActivity.trackJava1);
                                pbJava2.setProgress(MainActivity.trackJava2);
                                pbJavaQuiz.setProgress(MainActivity.trackJavaQuiz);
                                pbJavaTotal.setProgress(MainActivity.trackJavaTotal);
                                //Toast.makeText(getApplicationContext(), "Progress reset for Java Course", Toast.LENGTH_LONG);
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

        MainActivity.trackJavaTotal = (MainActivity.trackJava1 + MainActivity.trackJava2 + MainActivity.trackJavaQuiz)/3;

        pbJava1.setProgress(MainActivity.trackJava1);
        pbJava2.setProgress(MainActivity.trackJava2);
        pbJavaQuiz.setProgress(MainActivity.trackJavaQuiz);
        pbJavaTotal.setProgress(MainActivity.trackJavaTotal);
    }
}