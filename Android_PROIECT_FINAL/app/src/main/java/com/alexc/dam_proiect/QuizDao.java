package com.alexc.dam_proiect;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM quizes")
    List<QuizEntity> getAll();
//
//    @Query("SELECT * FROM quizes WHERE qid IN (:quizIds)")
//    List<QuizEntity> loadAllByIds(int[] quizIds);
//
////    @Query("SELECT * FROM contents WHERE content_string LIKE :contentString LIMIT 1")
////    ContentsEntity findByName(String contentString);
//
//    @Query("SELECT * FROM quizes WHERE course_id LIKE :course_id AND stage_id LIKE :stageId LIMIT 1")
//    QuizEntity findByLessonAndStage(int course_id, int stageId);
//
//
    @Query("SELECT * FROM quizes WHERE course_id LIKE :courseId AND stage_id LIKE :stageId LIMIT 1")
    QuizEntity findByCourseAndStage(int courseId, int stageId);

    @Insert
    void insert(QuizEntity quizEntity);

    @Insert
    void insertAll(QuizEntity... quizEntities);

    @Delete
    void delete(QuizEntity quizEntity);

    @Query("DELETE FROM quizes")
    void deleteAll();
}
