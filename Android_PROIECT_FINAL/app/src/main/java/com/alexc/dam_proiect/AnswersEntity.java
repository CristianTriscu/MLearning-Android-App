package com.alexc.dam_proiect;

import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "answers")
public class AnswersEntity {
    @PrimaryKey(autoGenerate = true)
    public int aid;

    @ForeignKey(entity = QuizEntity.class,
                        parentColumns = "qid",
                        childColumns = "quiz_fkid",
                        onDelete = CASCADE)
    public long quiz_fkid;

//    @ColumnInfo(name = "quiz_id") // FK
//    public int quiz_id;

    @ColumnInfo(name = "correctness")
    public boolean correctness;

    @ColumnInfo(name = "answer_string")
    public String answerString;

    public AnswersEntity(boolean correctness, String answerString){
        this.correctness = correctness;
        this.answerString = answerString;
    }

//    public int getQuiz_id() {
//        return quiz_id;
//    }
//
//    public void setQuiz_id(int quiz_id) {
//        this.quiz_id = quiz_id;
//    }

    public long getQuiz_fkid() {
        return quiz_fkid;
    }

    public void setQuiz_fkid(long quiz_fkid) {
        this.quiz_fkid = quiz_fkid;
    }

    public boolean isCorrectness() {
        return correctness;
    }

    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    @Override
    public String toString() {
        return "AnswersEntity{" +
                "aid=" + aid +
                ", quiz_fkid=" + quiz_fkid +
                ", correctness=" + correctness +
                ", answerString='" + answerString + '\'' +
                '}';
    }
}
