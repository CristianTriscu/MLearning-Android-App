package com.alexc.dam_proiect;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AnswersDao {
    @Query("SELECT * FROM answers")
    List<AnswersEntity> getAll();

    @Insert
    void insert(AnswersEntity answersEntity);

    @Insert
    void insertAll(AnswersEntity... answersEntities);

    @Delete
    void delete(AnswersEntity answersEntity);

    @Query("DELETE FROM answers")
    void deleteAll();
}
