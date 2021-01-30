package com.alexc.dam_proiect;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quizes")
public class QuizEntity {
    @PrimaryKey(autoGenerate = true)
    public int qid;

    @ColumnInfo(name = "course_id")
    public int courseId;

    @ColumnInfo(name = "stage_id")
    public int stageId;

    @ColumnInfo(name = "quiz_string")
    public String quizString;

    public QuizEntity() {
        //
    }

    public QuizEntity(int courseId, int stageId, String quizString) {
        this.courseId = courseId;
        this.stageId = stageId;
        this.quizString = quizString;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public String getQuizString() {
        return quizString;
    }

    public void setQuizString(String quizString) {
        this.quizString = quizString;
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "qid=" + qid +
                ", courseId=" + courseId +
                ", stageId=" + stageId +
                ", quizString='" + quizString + '\'' +
                '}';
    }
}
