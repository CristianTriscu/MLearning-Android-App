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

public class XmlCourseActivity extends AppCompatActivity {

    Button startXml1Btn;
    Button startXml2Btn;
    Button startXmlQuizBtn;

    Button resetXml1Btn;
    Button resetXml2Btn;
    Button resetXmlQuizBtn;
    Button resetXmlTotalBtn;

    ProgressBar pbXml1;
    ProgressBar pbXml2;
    ProgressBar pbXmlQuiz;
    ProgressBar pbXmlTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_course);

        startXml1Btn = findViewById(R.id.startXml1Btn);
        startXml2Btn = findViewById(R.id.startXml2Btn);
        startXmlQuizBtn = findViewById(R.id.startXmlQuizBtn);

        resetXml1Btn = findViewById(R.id.resetXml1Btn);
        resetXml2Btn = findViewById(R.id.resetXml2Btn);
        resetXmlQuizBtn = findViewById(R.id.resetXmlQuizBtn);
        resetXmlTotalBtn = findViewById(R.id.resetXmlTotalBtn);

        pbXml1 = findViewById(R.id.pbXml1);
        pbXml2 = findViewById(R.id.pbXml2);
        pbXmlQuiz = findViewById(R.id.pbXmlQuiz);
        pbXmlTotal = findViewById(R.id.pbXmlTotal);

        startXml1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isJava1 = false;
                MainActivity.isJava2 = false;
                MainActivity.isJavaQuiz = false;
                MainActivity.isAndroid1 = false;
                MainActivity.isAndroid2 = false;
                MainActivity.isAndroidQuiz = false;
                MainActivity.isXml1 = true;
                MainActivity.isXml2 = false;
                MainActivity.isXmlQuiz = false;


                Intent intent = new Intent(XmlCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        startXml2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isJava1 = false;
                MainActivity.isJava2 = false;
                MainActivity.isJavaQuiz = false;
                MainActivity.isAndroid1 = false;
                MainActivity.isAndroid2 = false;
                MainActivity.isAndroidQuiz = false;
                MainActivity.isXml1 = false;
                MainActivity.isXml2 = true;
                MainActivity.isXmlQuiz = false;

                Intent intent = new Intent(XmlCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        startXmlQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.isJava1 = false;
                MainActivity.isJava2 = false;
                MainActivity.isJavaQuiz = false;
                MainActivity.isAndroid1 = false;
                MainActivity.isAndroid2 = false;
                MainActivity.isAndroidQuiz = false;
                MainActivity.isXml1 = false;
                MainActivity.isXml2 = false;
                MainActivity.isXmlQuiz = true;

                Intent intent = new Intent(XmlCourseActivity.this, LessonActivity.class);
                startActivity(intent);
            }
        });

        resetXml1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(XmlCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageXml1 = 1;
                                MainActivity.trackXml1 = 0;

                                pbXml1.setProgress(MainActivity.trackXml1);
                                //Toast.makeText(getApplicationContext(), "Progress reset for XML Lesson 1", Toast.LENGTH_LONG);
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

        resetXml2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(XmlCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageXml2 = 1;
                                MainActivity.trackXml2 = 0;

                                pbXml2.setProgress(MainActivity.trackXml2);
                                //Toast.makeText(getApplicationContext(), "Progress reset for XML Lesson 2", Toast.LENGTH_LONG);
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

        resetXmlQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(XmlCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageXmlQuiz = 1;
                                MainActivity.trackXmlQuiz = 0;

                                pbXmlQuiz.setProgress(MainActivity.trackXmlQuiz);
                                //Toast.makeText(getApplicationContext(), "Progress reset for XML Quiz", Toast.LENGTH_LONG);
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

        resetXmlTotalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(XmlCourseActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.stageXml1 = 1;
                                MainActivity.stageXml2 = 1;
                                MainActivity.stageXmlQuiz = 1;
                                MainActivity.trackXml1 = 0;
                                MainActivity.trackXml2 = 0;
                                MainActivity.trackXmlQuiz = 0;
                                MainActivity.trackXmlTotal = 0;

                                pbXml1.setProgress(MainActivity.trackXml1);
                                pbXml2.setProgress(MainActivity.trackXml2);
                                pbXmlQuiz.setProgress(MainActivity.trackXmlQuiz);
                                pbXmlTotal.setProgress(MainActivity.trackXmlTotal);
                                //Toast.makeText(getApplicationContext(), "Progress reset for XML Course", Toast.LENGTH_LONG);
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

        MainActivity.trackXmlTotal = (MainActivity.trackXml1 + MainActivity.trackXml2 + MainActivity.trackXmlQuiz)/3;

        pbXml1.setProgress(MainActivity.trackXml1);
        pbXml2.setProgress(MainActivity.trackXml2);
        pbXmlQuiz.setProgress(MainActivity.trackXmlQuiz);
        pbXmlTotal.setProgress(MainActivity.trackXmlTotal);
    }
}