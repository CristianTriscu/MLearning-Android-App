package com.alexc.dam_proiect;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface ContentsDao {
    @Query("SELECT * FROM contents")
    List<ContentsEntity> getAll();

//    @Query("SELECT * FROM contents WHERE cid IN (:contentIds)")
//    List<ContentsEntity> loadAllByIds(int[] contentIds);

//    @Query("SELECT * FROM contents WHERE content_string LIKE :contentString LIMIT 1")
//    ContentsEntity findByName(String contentString);

    @Query("SELECT * FROM contents WHERE lesson_id LIKE :lessId AND stage_id LIKE :stageId LIMIT 1")
    ContentsEntity findByLessonAndStage(int lessId, int stageId);

//    @Query("SELECT * FROM contents WHERE lesson_id = :lessId AND stage_id = :stageId LIMIT 1")
//    public Single<ContentsEntity> loadByLessonAndStage(int lessId, int stageId);

    @Insert
    void insert(ContentsEntity contentsEntity);

    @Insert
    void insertAll(ContentsEntity... contentsEntities);

    @Delete
    void delete(ContentsEntity contentsEntity);

    @Query("DELETE FROM contents")
    void deleteAll();
}
