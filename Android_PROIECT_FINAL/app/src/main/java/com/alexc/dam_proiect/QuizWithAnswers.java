package com.alexc.dam_proiect;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class QuizWithAnswers {
    @Embedded
    public QuizEntity quizEntity;
    @Relation(
            parentColumn = "qid",
            entityColumn = "quiz_fkid"
    )
    public List<AnswersEntity> answersEntityList;

    public QuizWithAnswers(QuizEntity quizEntity, List<AnswersEntity> answersEntityList) {
        this.quizEntity = quizEntity;
        this.answersEntityList = answersEntityList;
    }

    @Override
    public String toString() {
        return "QuizWithAnswers{" +
                "quizEntity=" + quizEntity.toString() +
                ", answersEntityList=" + answersEntityList.toString() +
                '}';
    }
}
