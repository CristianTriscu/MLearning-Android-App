package com.alexc.dam_proiect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button startJavaBtn;
    Button resetJavaBtn;
    Button startAndroidBtn;
    Button resetAndroidBtn;
    Button startXmlBtn;
    Button resetXmlBtn;

    Button resetTotalBtn;

    public static ArrayList<Reminder> arrayList = new ArrayList<>();

    public static  ArrayList<FeedBack> feedBacks=new ArrayList<>();

    /*
    progress bars
     */
    ProgressBar progressBarJava;
    ProgressBar progressBarAndroid;
    ProgressBar progressBarXml;
    ProgressBar progressBarTotal;

    /*
    var lectii
     */
    public static int trackProgresTotal = 0;
    public static int trackJavaTotal = 0;
    public static int trackAndroidTotal = 0;
    public static int trackXmlTotal = 0;
    public static int trackJava1 = 0;
    public static int trackJava2 = 0;
    public static int trackJavaQuiz = 0;
    public static int trackAndroid1 = 0;
    public static int trackAndroid2 = 0;
    public static int trackAndroidQuiz = 0;
    public static int trackXml1 = 0;
    public static int trackXml2 = 0;
    public static int trackXmlQuiz = 0;

    public static boolean isJavaCourse = false;
    public static boolean isAndroidCourse = false;
    public static boolean isXmlCourse = false;

    public static boolean isJava1 = false;
    public static boolean isJava2 = false;
    public static boolean isJavaQuiz = false;
    public static boolean isAndroid1 = false;
    public static boolean isAndroid2 = false;
    public static boolean isAndroidQuiz = false;
    public static boolean isXml1 = false;
    public static boolean isXml2 = false;
    public static boolean isXmlQuiz = false;

    public static int stageJava1 = 1;
    public static int stageJava2 = 1;
    public static int stageJavaQuiz = 1;
    public static int stageAndroid1 = 1;
    public static int stageAndroid2 = 1;
    public static int stageAndroidQuiz = 1;
    public static int stageXml1 = 1;
    public static int stageXml2 = 1;
    public static int stageXmlQuiz = 1;

    public static int scoreJava = 0;
    public static int scoreAndroid = 0;
    public static int scoreXml = 0;


    private View view1;
    private View view2;
    private View view3;
    private View view4;


    private FirebaseDatabase firebase;


    //room
    Resources res;
    String[] quizes;
    String[] answers;
    String[] lesson_contents;
    QuizViewModel qvm;
    List<QuizEntity> quizEntities = new ArrayList<QuizEntity>();
    List<AnswersEntity> answersEntities = new ArrayList<AnswersEntity>();
    List<List<AnswersEntity>> listOfLists = new ArrayList<>();

    public static boolean[] concatArrays(boolean[] src1, boolean[] src2) {
        if (src1 == null) {
            throw new IllegalArgumentException("src1 is required.");
        }
        if (src2 == null) {
            throw new IllegalArgumentException("src2 is required.");
        }

        boolean[] result = new boolean[src1.length + src2.length];

        System.arraycopy(src1, 0, result, 0, src1.length);
        System.arraycopy(src2, 0, result, src1.length, src2.length);

        return result;
    }
    //QuizRepo quizRepo = new QuizRepo(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// ROOM
        final CoursesDB db = CoursesDB.getInstance(getApplicationContext());
        db.contentsDao().deleteAll();
        db.quizWithAnswersDao().deleteAll();
        res = getResources();
        quizes = res.getStringArray(R.array.quiz_questions);
        answers = res.getStringArray(R.array.answers_strings);
        lesson_contents = res.getStringArray(R.array.content_strings);
        boolean[] boolean_1 = {true, false, false, false, true, false, false, false, true};
        boolean[] boolean_2 = concatArrays(boolean_1, boolean_1); // 2
        boolean[] boolean_4 = concatArrays(boolean_2, boolean_2); // 4
        boolean[] boolean_8 = concatArrays(boolean_4, boolean_4); // 8
        boolean[] boolean_10 = concatArrays(boolean_8, boolean_2); // 10
        qvm = new QuizViewModel(getApplication());

        int var_lesson = 0;
        for(int i = 1; i <= 60; i++) {
            int stageId = (i%10 == 0 ? 10 : i%10);
            if(i<=10){
                var_lesson = 1;
            } else if(i>10 && i<=20){
                var_lesson = 2;
            } else if(i>20 && i<=30){
                var_lesson = 3;
            } else if(i>30 && i<=40) {
                var_lesson = 4;
            } else if(i>40 && i<=50) {
                var_lesson = 5;
            } else if(i>50 && i<=60) {
                var_lesson = 6;
            }
            //String contentString = "Content " + lessonId + "." + stageId;
            //String contentString = "Content " + var_lesson + "." + stageId;
            String contentString = lesson_contents[i-1];
            ContentsEntity contentsEntity2 = new ContentsEntity(stageId, contentString, var_lesson);
            db.contentsDao().insert(contentsEntity2);
        }
        /// ROOM TEST 1 --------
        // CREARE LISTA QUIZ ENTITIES
        int course_id = 0;
        for(int i = 1; i <= 30; i++){
            int stageId = (i%10 == 0 ? 10 : i%10);
            //int courseId = (i%3 == 0 ? 3 : i%3);
            if(i<=10){
                course_id = 1;
            } else if(i>10 && i<=20){
                course_id = 2;
            } else if(i>20 && i<=30){
                course_id = 3;
            }
            String quiz_string = quizes[i-1];
            QuizEntity quizEntity = new QuizEntity(course_id, stageId, quiz_string);
            quizEntities.add(quizEntity);

        }

        // CREARE LISTA ANSWERS ENTITIES
        for(int i = 1; i <= 90; i++){
            String ans_string = answers[i-1];
            AnswersEntity answersEntity = new AnswersEntity(boolean_10[i-1], ans_string);
            answersEntities.add(answersEntity);
        }


        // CREARE LISTA DE LISTE (DE CATE 3) DE ANSWERS ENTITIES
        for(int i = 0; i < 90; i = i + 3){
            listOfLists.add(Arrays.asList(new AnswersEntity[]{answersEntities.get(i), answersEntities.get(i + 1), answersEntities.get(i + 2)}));
        }

        // CREARE BAZA DE DATE CU QuizWithAnswers din listele de Quiz Entities si Answers Entities
        for(int j = 0; j < 30; j++) {
            QuizWithAnswers quizWithAnswers = new QuizWithAnswers(quizEntities.get(j), listOfLists.get(j));
            qvm.insertQuizWithAnswers(quizWithAnswers);
        }

        List<AnswersEntity> answeri = db.answersDao().getAll();
        for(AnswersEntity qe : answeri){
            Log.i("ANSWERS", qe.toString());
        }



//////////////////////////////////////////////////////////////////////////////////////////////////
        final User user = (User) getIntent().getSerializableExtra("User");


        view1=findViewById(R.id.view4);
        view1.setBackgroundResource(R.color.colorView);
        view2=findViewById(R.id.view6);
        view2.setBackgroundResource(R.color.colorView);
        view3=findViewById(R.id.view7);
        view3.setBackgroundResource(R.color.colorView);
        view4=findViewById(R.id.view8);
        view4.setBackgroundResource(R.color.colorView);






        trackJava1=user.getTrackJava1();
        trackJava2=user.getTrackJava2();
        trackJavaQuiz=user.getTrackJavaQuiz();

        trackAndroid1=user.getTrackAndroid1();
        trackAndroid2=user.getTrackAndroid2();
        trackAndroidQuiz=user.getTrackAndroidQuiz();

        trackXml1=user.getTrackXML1();
        trackXml2=user.getTrackXML2();
        trackXmlQuiz=user.getTrackXMLQuiz();


        isJavaCourse = false;
        isAndroidCourse = false;
        isXmlCourse = false;

        isJava1 = false;
        isJava2 = false;
        isJavaQuiz = false;
        isAndroid1 = false;
        isAndroid2 = false;
        isAndroidQuiz = false;
        isXml1 = false;
        isXml2 = false;
        isXmlQuiz = false;

        startJavaBtn = findViewById(R.id.startJavaBtn);
        resetJavaBtn = findViewById(R.id.resetJavaBtn);
        startAndroidBtn = findViewById(R.id.startAndroidBtn);
        resetAndroidBtn = findViewById(R.id.resetAndroidBtn);
        startXmlBtn = findViewById(R.id.startXmlBtn);
        resetXmlBtn = findViewById(R.id.resetXmlBtn);

        resetTotalBtn = findViewById(R.id.resetTotalBtn);

        progressBarJava = findViewById(R.id.progressBarJava);
        progressBarAndroid = findViewById(R.id.progressBarAndroid);
        progressBarXml = findViewById(R.id.progressBarXml);
        progressBarTotal = findViewById(R.id.progressBarTotal);

        /////////////////
        trackJavaTotal = (trackJava1 + trackJava2 + trackJavaQuiz)/3;
        trackAndroidTotal = (trackAndroid1 + trackAndroid2 + trackAndroidQuiz)/3;
        trackXmlTotal = (trackXml1 + trackXml2 + trackXmlQuiz)/3;
        trackProgresTotal = (trackJavaTotal + trackAndroidTotal + trackXmlTotal)/3;

        progressBarJava.setProgress(trackJavaTotal);
        progressBarAndroid.setProgress(trackAndroidTotal);
        progressBarXml.setProgress(trackXmlTotal);
        progressBarTotal.setProgress(trackProgresTotal);
        /////////////////

        startJavaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJavaCourse = true;
                isAndroidCourse = false;
                isXmlCourse = false;

                Intent intent = new Intent(MainActivity.this, JavaCourseActivity.class);
                startActivity(intent);
            }
        });

        startAndroidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJavaCourse = false;
                isAndroidCourse = true;
                isXmlCourse = false;

                Intent intent = new Intent(MainActivity.this, AndroidCourseActivity.class);
                startActivity(intent);
            }
        });

        startXmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJavaCourse = false;
                isAndroidCourse = false;
                isXmlCourse = true;

                Intent intent = new Intent(MainActivity.this, XmlCourseActivity.class);
                startActivity(intent);
            }
        });

        resetJavaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                stageJava1 = 1;
                                stageJava2 = 1;
                                stageJavaQuiz = 1;
                                trackJava1 = 0;
                                trackJava2 = 0;
                                trackJavaQuiz = 0;
                                trackJavaTotal = 0;
                                trackProgresTotal = (trackJavaTotal + trackAndroidTotal + trackXmlTotal)/3;

                                progressBarJava.setProgress(trackJavaTotal);
                                progressBarTotal.setProgress(trackProgresTotal);
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

        resetAndroidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                stageAndroid1 = 1;
                                stageAndroid2 = 1;
                                stageAndroidQuiz = 1;
                                trackAndroid1 = 0;
                                trackAndroid2 = 0;
                                trackAndroidQuiz = 0;
                                trackAndroidTotal = 0;
                                trackProgresTotal = (trackJavaTotal + trackAndroidTotal + trackXmlTotal)/3;

                                progressBarAndroid.setProgress(trackAndroidTotal);
                                progressBarTotal.setProgress(trackProgresTotal);
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

        resetXmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                stageXml1 = 1;
                                stageXml2 = 1;
                                stageXmlQuiz = 1;
                                trackXml1 = 0;
                                trackXml2 = 0;
                                trackXmlQuiz = 0;
                                trackXmlTotal = 0;
                                trackProgresTotal = (trackJavaTotal + trackAndroidTotal + trackXmlTotal)/3;

                                progressBarXml.setProgress(trackXmlTotal);
                                progressBarTotal.setProgress(trackProgresTotal);
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

        resetTotalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.resetprogressof);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                stageJava1 = 1;
                                stageJava2 = 1;
                                stageJavaQuiz = 1;
                                trackJava1 = 0;
                                trackJava2 = 0;
                                trackJavaQuiz = 0;
                                trackJavaTotal = 0;
                                stageAndroid1 = 1;
                                stageAndroid2 = 1;
                                stageAndroidQuiz = 1;
                                trackAndroid1 = 0;
                                trackAndroid2 = 0;
                                trackAndroidQuiz = 0;
                                trackAndroidTotal = 0;
                                stageXml1 = 1;
                                stageXml2 = 1;
                                stageXmlQuiz = 1;
                                trackXml1 = 0;
                                trackXml2 = 0;
                                trackXmlQuiz = 0;
                                trackXmlTotal = 0;
                                trackProgresTotal = (trackJavaTotal + trackAndroidTotal + trackXmlTotal)/3;

                                progressBarJava.setProgress(trackJavaTotal);
                                progressBarAndroid.setProgress(trackAndroidTotal);
                                progressBarXml.setProgress(trackXmlTotal);
                                progressBarTotal.setProgress(trackProgresTotal);
                                //Toast.makeText(getApplicationContext(), "Progress reset for all courses", Toast.LENGTH_LONG);
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
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.spinner_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optiune1:
                Intent intent = new Intent(this, Reminders.class);
                startActivity(intent);
                break;
            case R.id.optiune2:
                //

                User user1 = (User) getIntent().getSerializableExtra("User");
                Intent intent3=new Intent(this, FeedBackActivity.class);
                intent3.putExtra("username", user1.getUsername());
                startActivity(intent3);
                break;
            case R.id.optiune3:
                Intent intent2 = new Intent(this, Settings.class);

                startActivity(intent2);
                break;

            case R.id.optiune4:
                Intent intent4 = new Intent(this, Statistics.class);
                startActivity(intent4);
                break;
            case R.id.optiune6:
                Intent intent6 = new Intent(this, extractAdvicesJSON.class);
                startActivity(intent6);
                break;
            case R.id.optiune5:
                //
//                Intent logout=new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(logout);
//                break;


                Intent in = getIntent();
                String string = in.getStringExtra(getString(R.string.message));

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.confirmation_popup).
                        setMessage(R.string.Areyousure);
                builder.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getApplicationContext(),
                                        LoginActivity.class);
                                startActivity(i);
                                finish();
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


        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("lifecycle", "Apel metoda onStart() in main");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("lifecycle", "Apel metoda onResume() in main");

        final User user = (User) getIntent().getSerializableExtra("User");
        user.setTrackJava1(trackJava1);
        user.setTrackJava2(trackJava2);
        user.setTrackJavaQuiz(trackJavaQuiz);

        user.setTrackAndroid1(trackAndroid1);
        user.setTrackAndroid2(trackAndroid2);
        user.setTrackAndroidQuiz(trackAndroidQuiz);

        user.setTrackXML1(trackXml1);
        user.setTrackXML2(trackXml2);
        user.setTrackXMLQuiz(trackXmlQuiz);


        firebase=FirebaseDatabase.getInstance();
        final DatabaseReference myRef=firebase.getReference("learndroid-9bc33-default-rtdb");
        myRef.keepSynced(true);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myRef.child("Useri").child(user.getId()).setValue(user);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("FeedBack").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //feedBackList.clear();
                    for (DataSnapshot dn : snapshot.getChildren()) {
                        FeedBack fb= dn.getValue(FeedBack.class);
                        feedBacks.add(fb);
                        Log.d("testmain",feedBacks.toString());


                    }
                }
                Intent intent=new Intent(getApplicationContext(),Fragment1.class);
                intent.putExtra("fbList",  feedBacks);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









        //Toast.makeText(getApplicationContext(), "ON RESUME", Toast.LENGTH_LONG).show();
        isJavaCourse = false;
        isAndroidCourse = false;
        isXmlCourse = false;

        isJava1 = false;
        isJava2 = false;
        isJavaQuiz = false;
        isAndroid1 = false;
        isAndroid2 = false;
        isAndroidQuiz = false;
        isXml1 = false;
        isXml2 = false;
        isXmlQuiz = false;

        trackJavaTotal = (trackJava1 + trackJava2 + trackJavaQuiz)/3;
        trackAndroidTotal = (trackAndroid1 + trackAndroid2 + trackAndroidQuiz)/3;
        trackXmlTotal = (trackXml1 + trackXml2 + trackXmlQuiz)/3;
        trackProgresTotal = (trackJavaTotal + trackAndroidTotal + trackXmlTotal)/3;

        progressBarJava.setProgress(trackJavaTotal);
        progressBarAndroid.setProgress(trackAndroidTotal);
        progressBarXml.setProgress(trackXmlTotal);
        progressBarTotal.setProgress(trackProgresTotal);


        Log.d(getString(R.string.lyfecycle), "Apel metoda onResume() in main");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(getString(R.string.lifecycle), "Apel metoda onPause() in main");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("lifecycle", "Apel metoda onStop() in main");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("lifecycle", "Apel metoda onRestart() in main");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("lifecycle", "Apel metoda onDestroy() in main");
    }
}