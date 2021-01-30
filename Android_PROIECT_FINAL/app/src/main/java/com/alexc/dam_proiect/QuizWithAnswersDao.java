package com.alexc.dam_proiect;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface QuizWithAnswersDao {
//    @Transaction
//    @Query("SELECT * FROM quizes")
//    public List<QuizWithAnswers> getQuizWithAnswers();

    @Transaction
    @Insert
    long insertQuiz(QuizEntity quizEntity); // insertCourse

    @Insert
    void insertAnswers(List<AnswersEntity> answersEntities); // insertStudents

    @Query("SELECT * FROM quizes WHERE course_id LIKE :courseId AND stage_id LIKE :stageId")
    QuizWithAnswers getQuizWithAnswers(int courseId, int stageId);

    @Query("DELETE FROM quizes")
    void deleteAll();

    @Query("SELECT * FROM quizes")
    List<QuizWithAnswers> getAll();
}
