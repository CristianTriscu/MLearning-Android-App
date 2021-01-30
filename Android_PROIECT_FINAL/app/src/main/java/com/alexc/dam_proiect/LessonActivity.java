package com.alexc.dam_proiect;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Single;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.List;

public class LessonActivity extends AppCompatActivity {

    Button nextBtn;
    Button backBtn;
    TextView tvLessonText;
    TextView tvLessonStage;
    TextView tvContent;
    int stage;
    int score;

    int chosenAnswer;
    int correctAnswer;

    RadioGroup rgAnswers;
    RadioButton rgAnswer1;
    RadioButton rgAnswer2;
    RadioButton rgAnswer3;
    //Resources res = getResources();

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        stage = 1;
        score = 0;

        nextBtn = findViewById(R.id.lessonNextBtn);
        backBtn = findViewById(R.id.lessonBackBtn);
        tvLessonText = findViewById(R.id.tvLessonText);
        tvLessonStage = findViewById(R.id.tvLessonStage);
        tvContent = findViewById(R.id.tvContent);
        rgAnswers = (RadioGroup) findViewById(R.id.rgAnswers);
        rgAnswer1 = findViewById(R.id.rgAnswer1);
        rgAnswer2 = findViewById(R.id.rgAnswer2);
        rgAnswer3 = findViewById(R.id.rgAnswer3);

        //String[] quizes = res.getStringArray(R.array.quiz_questions);


        /// ROOM TEST 1 <<<<<<<<
        /// mutata in MainActivity
        /// ROOM TEST 1 >>>>>>>>
        final CoursesDB db = CoursesDB.getInstance(getApplicationContext());


        if(MainActivity.isJava1){
            tvLessonText.setText(R.string.java_lesson_1);
            //stage = MainActivity.trackJava1;
            stage = MainActivity.stageJava1;
            tvLessonStage.setText(stage + " / 10");
            ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(1, stage);
            tvContent.setText(contentsEntity.getContentString());


        }
        else if(MainActivity.isJava2){
            tvLessonText.setText(R.string.java_lesson_2);
            stage = MainActivity.stageJava2;
            tvLessonStage.setText(stage + " / 10");
            ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(2, stage);
            tvContent.setText(contentsEntity.getContentString());

        } else if(MainActivity.isJavaQuiz){
            tvLessonText.setText(R.string.java_lesson_quiz);
            stage = MainActivity.stageJavaQuiz;
            //tvContent.setHeight(200);
            tvLessonStage.setText(stage + " / 10");
            rgAnswers.setVisibility(View.VISIBLE);
            score = MainActivity.scoreJava;

            List<QuizEntity> answeri = db.quizDao().getAll();
            for(QuizEntity qe : answeri){
                Log.i("QUIZ LOG TEST", qe.toString());
            }

            QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(1, stage);

            tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
            rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
            rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
            rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());

            Log.i("STAGE", String.valueOf(stage));


        } else if(MainActivity.isAndroid1){
            tvLessonText.setText(R.string.android_lesson_1);
            stage = MainActivity.stageAndroid1;
            tvLessonStage.setText(stage + " / 10");
            ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(3, stage);
            tvContent.setText(contentsEntity.getContentString());

        } else if(MainActivity.isAndroid2){
            tvLessonText.setText(R.string.android_lesson_2);
            stage = MainActivity.stageAndroid2;
            tvLessonStage.setText(stage + " / 10");
            ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(4, stage);
            tvContent.setText(contentsEntity.getContentString());

        } else if(MainActivity.isAndroidQuiz){
            tvLessonText.setText(R.string.android_lesson_quiz);
            stage = MainActivity.stageAndroidQuiz;
            tvLessonStage.setText(stage + " / 10");
            rgAnswers.setVisibility(View.VISIBLE);
            score = MainActivity.scoreAndroid;
            QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(2, stage);
            tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
            rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
            rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
            rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());

        } else if(MainActivity.isXml1){
            tvLessonText.setText(R.string.xml_lesson_1);
            stage = MainActivity.stageXml1;
            tvLessonStage.setText(stage + " / 10");
            ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(5, stage);
            tvContent.setText(contentsEntity.getContentString());

        } else if(MainActivity.isXml2){
            tvLessonText.setText(R.string.xml_lesson_2);
            stage = MainActivity.stageXml2;
            tvLessonStage.setText(stage + " / 10");
            ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(6, stage);
            tvContent.setText(contentsEntity.getContentString());

        } else if(MainActivity.isXmlQuiz){
            tvLessonText.setText(R.string.xml_lesson_quiz);
            stage = MainActivity.stageXmlQuiz;
            tvLessonStage.setText(stage + " / 10");
            rgAnswers.setVisibility(View.VISIBLE);
            score = MainActivity.scoreXml;
            QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(3, stage);
            tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
            rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
            rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
            rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());

        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stage == 10) {
                    if(MainActivity.isJavaQuiz){
                        chosenAnswer = 0;
                        correctAnswer = 0;
                        if(rgAnswer1.isChecked()) {
                            chosenAnswer = 1;
                        } else if(rgAnswer2.isChecked()) {
                            chosenAnswer = 2;
                        } else if(rgAnswer3.isChecked()) {
                            chosenAnswer = 3;
                        }
                        QuizWithAnswers qwa = db.quizWithAnswersDao().getQuizWithAnswers(1, stage);
                        if(qwa.answersEntityList.get(0).correctness) {
                            correctAnswer = 1;
                        } else if(qwa.answersEntityList.get(1).correctness) {
                            correctAnswer = 2;
                        } else if (qwa.answersEntityList.get(2).correctness) {
                            correctAnswer = 3;
                        }
                        if(chosenAnswer == correctAnswer) {
                            score++;
                        }
                        MainActivity.scoreJava = score;
                        Toast.makeText(getApplicationContext(), "Final score: " + score + "/10", Toast.LENGTH_SHORT).show();
                    }
                    if(MainActivity.isAndroidQuiz){
                        chosenAnswer = 0;
                        correctAnswer = 0;
                        if(rgAnswer1.isChecked()) {
                            chosenAnswer = 1;
                        } else if(rgAnswer2.isChecked()) {
                            chosenAnswer = 2;
                        } else if(rgAnswer3.isChecked()) {
                            chosenAnswer = 3;
                        }
                        QuizWithAnswers qwa = db.quizWithAnswersDao().getQuizWithAnswers(2, stage);
                        if(qwa.answersEntityList.get(0).correctness) {
                            correctAnswer = 1;
                        } else if(qwa.answersEntityList.get(1).correctness) {
                            correctAnswer = 2;
                        } else if (qwa.answersEntityList.get(2).correctness) {
                            correctAnswer = 3;
                        }
                        if(chosenAnswer == correctAnswer) {
                            score++;
                        }
                        MainActivity.scoreAndroid = score;
                        Toast.makeText(getApplicationContext(),  "Final score: " + score + "/10", Toast.LENGTH_SHORT).show();
                    }
                    if(MainActivity.isXmlQuiz){
                        chosenAnswer = 0;
                        correctAnswer = 0;
                        if(rgAnswer1.isChecked()) {
                            chosenAnswer = 1;
                        } else if(rgAnswer2.isChecked()) {
                            chosenAnswer = 2;
                        } else if(rgAnswer3.isChecked()) {
                            chosenAnswer = 3;
                        }
                        QuizWithAnswers qwa = db.quizWithAnswersDao().getQuizWithAnswers(3, stage);
                        if(qwa.answersEntityList.get(0).correctness) {
                            correctAnswer = 1;
                        } else if(qwa.answersEntityList.get(1).correctness) {
                            correctAnswer = 2;
                        } else if (qwa.answersEntityList.get(2).correctness) {
                            correctAnswer = 3;
                        }
                        if(chosenAnswer == correctAnswer) {
                            score++;
                        }
                        MainActivity.scoreXml = score;
                        Toast.makeText(getApplicationContext(), "Final score: " + score + "/10", Toast.LENGTH_SHORT).show();
                    }
                    nextBtn.setEnabled(false);
                    return;
                }

                if(stage >= 10) {

                    return;
                }
                if(MainActivity.isJavaQuiz || MainActivity.isAndroidQuiz || MainActivity.isXmlQuiz){
                    if(rgAnswer1.isChecked() == false && rgAnswer2.isChecked() == false && rgAnswer3.isChecked() == false){
                        Toast.makeText(getApplicationContext(), R.string.choose_an_answer, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(stage < 10){
                    stage += 1;
                }
                tvLessonStage.setText(stage + " / 10");

                if(MainActivity.isJava1){
                    //MainActivity.trackJava1 = stage;
                    MainActivity.stageJava1 = stage;
                    MainActivity.trackJava1 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(1, stage);
                    tvContent.setText(contentsEntity.getContentString());
                }
                else if(MainActivity.isJava2){
                    MainActivity.stageJava2 = stage;
                    MainActivity.trackJava2 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(2, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isJavaQuiz){
                    chosenAnswer = 0;
                    correctAnswer = 0;
                    Log.i("STAGEB", String.valueOf(stage%3));
                    if(rgAnswer1.isChecked()) {
                        chosenAnswer = 1;
                    } else if(rgAnswer2.isChecked()) {
                        chosenAnswer = 2;
                    } else if(rgAnswer3.isChecked()) {
                        chosenAnswer = 3;
                    }
                    QuizWithAnswers qwa = db.quizWithAnswersDao().getQuizWithAnswers(1, stage-1);
                    if(qwa.answersEntityList.get(0).correctness) {
                        correctAnswer = 1;
                    } else if(qwa.answersEntityList.get(1).correctness) {
                        correctAnswer = 2;
                    } else if (qwa.answersEntityList.get(2).correctness) {
                        correctAnswer = 3;
                    }
                    Log.i("CORRECTNESS LOG", String.valueOf(qwa.answersEntityList.get(0).isCorrectness()));
                    Log.i("CORRECTNESS LOG", String.valueOf(qwa.answersEntityList.get(1).isCorrectness()));
                    Log.i("CORRECTNESS LOG", String.valueOf(qwa.answersEntityList.get(2).isCorrectness()));
                    Log.i("STAGECHO", String.valueOf(chosenAnswer));
                    Log.i("STAGECORR", String.valueOf(correctAnswer));
                    Log.i("STAGESC", String.valueOf(score));
                    if(chosenAnswer == correctAnswer) {
                        score++;
                    }
                    Log.i("STAGESCA", String.valueOf(score));

                    MainActivity.scoreJava = score;
                    MainActivity.stageJavaQuiz = stage;
                    MainActivity.trackJavaQuiz = stage * 10;
                    QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(1, stage);
                    tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
                    rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
                    rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
                    rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());


                } else if(MainActivity.isAndroid1){
                    MainActivity.stageAndroid1 = stage;
                    MainActivity.trackAndroid1 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(3, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isAndroid2){
                    MainActivity.stageAndroid2 = stage;
                    MainActivity.trackAndroid2 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(4, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isAndroidQuiz){
                    chosenAnswer = 0;
                    correctAnswer = 0;
                    if(rgAnswer1.isChecked()) {
                        chosenAnswer = 1;
                    } else if(rgAnswer2.isChecked()) {
                        chosenAnswer = 2;
                    } else if(rgAnswer3.isChecked()) {
                        chosenAnswer = 3;
                    }
                    QuizWithAnswers qwa = db.quizWithAnswersDao().getQuizWithAnswers(2, stage-1);
                    if(qwa.answersEntityList.get(0).correctness) {
                        correctAnswer = 1;
                    } else if(qwa.answersEntityList.get(1).correctness) {
                        correctAnswer = 2;
                    } else if (qwa.answersEntityList.get(2).correctness) {
                        correctAnswer = 3;
                    }
                    if(chosenAnswer == correctAnswer) {
                        score++;
                    }

                    MainActivity.scoreAndroid = score;
                    MainActivity.stageAndroidQuiz = stage;
                    MainActivity.trackAndroidQuiz = stage * 10;
                    QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(2, stage);
                    tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
                    rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
                    rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
                    rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());

                } else if(MainActivity.isXml1){
                    MainActivity.stageXml1 = stage;
                    MainActivity.trackXml1 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(5, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isXml2){
                    MainActivity.stageXml2 = stage;
                    MainActivity.trackXml2 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(6, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isXmlQuiz){
                    chosenAnswer = 0;
                    correctAnswer = 0;
                    if(rgAnswer1.isChecked()) {
                        chosenAnswer = 1;
                    } else if(rgAnswer2.isChecked()) {
                        chosenAnswer = 2;
                    } else if(rgAnswer3.isChecked()) {
                        chosenAnswer = 3;
                    }
                    QuizWithAnswers qwa = db.quizWithAnswersDao().getQuizWithAnswers(3, stage-1);
                    if(qwa.answersEntityList.get(0).correctness) {
                        correctAnswer = 1;
                    } else if(qwa.answersEntityList.get(1).correctness) {
                        correctAnswer = 2;
                    } else if (qwa.answersEntityList.get(2).correctness) {
                        correctAnswer = 3;
                    }
                    if(chosenAnswer == correctAnswer) {
                        score++;
                    }

                    MainActivity.scoreXml = score;
                    MainActivity.stageXmlQuiz = stage;
                    MainActivity.trackXmlQuiz = stage * 10;
                    QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(3, stage);
                    tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
                    rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
                    rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
                    rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());

                }

                rgAnswer1.setChecked(false);
                rgAnswer2.setChecked(false);
                rgAnswer3.setChecked(false);

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextBtn.setEnabled(true);
                if(stage==10){ // pt bug-ul de la ultimul stage
                    score--;
                }
                if(stage <= 1) {
                    //backBtn.setEnabled(false);
                    return;
                }
                if(stage > 0){
                    stage -= 1;
                }
                tvLessonStage.setText(stage + " / 10");

                if(MainActivity.isJava1){
                    //MainActivity.trackJava1 = stage;
                    MainActivity.stageJava1 = stage;
                    MainActivity.trackJava1 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(1, stage);
                    tvContent.setText(contentsEntity.getContentString());
                }
                else if(MainActivity.isJava2){
                    MainActivity.stageJava2 = stage;
                    MainActivity.trackJava2 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(2, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isJavaQuiz){
                    MainActivity.stageJavaQuiz = stage;
                    MainActivity.trackJavaQuiz = stage * 10;
                    score--;
                    MainActivity.scoreJava = score;
                    QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(1, stage);
                    tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
                    rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
                    rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
                    rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());
                    Log.i("BACKSCORE", "Score: " + score);

                } else if(MainActivity.isAndroid1){
                    MainActivity.stageAndroid1 = stage;
                    MainActivity.trackAndroid1 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(3, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isAndroid2){
                    MainActivity.stageAndroid2 = stage;
                    MainActivity.trackAndroid2 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(4, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isAndroidQuiz){
                    MainActivity.stageAndroidQuiz = stage;
                    MainActivity.trackAndroidQuiz = stage * 10;
                    score--;
                    MainActivity.scoreAndroid = score;
                    QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(2, stage);
                    tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
                    rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
                    rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
                    rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());

                } else if(MainActivity.isXml1){
                    MainActivity.stageXml1 = stage;
                    MainActivity.trackXml1 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(5, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isXml2){
                    MainActivity.stageXml2 = stage;
                    MainActivity.trackXml2 = stage * 10;
                    ContentsEntity contentsEntity = db.contentsDao().findByLessonAndStage(6, stage);
                    tvContent.setText(contentsEntity.getContentString());

                } else if(MainActivity.isXmlQuiz){
                    MainActivity.stageXmlQuiz = stage;
                    MainActivity.trackXmlQuiz = stage * 10;
                    score--;
                    MainActivity.scoreXml = score;
                    QuizWithAnswers quizWithAnswers = db.quizWithAnswersDao().getQuizWithAnswers(3, stage);
                    tvContent.setText(quizWithAnswers.quizEntity.getQuizString());
                    rgAnswer1.setText(quizWithAnswers.answersEntityList.get(0).getAnswerString());
                    rgAnswer2.setText(quizWithAnswers.answersEntityList.get(1).getAnswerString());
                    rgAnswer3.setText(quizWithAnswers.answersEntityList.get(2).getAnswerString());

                }
            }
        });
    }
}