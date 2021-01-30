package com.alexc.dam_proiect;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ContentsEntity.class, QuizEntity.class, AnswersEntity.class}, version = 2, exportSchema = false)
public abstract class CoursesDB extends RoomDatabase {
    //public abstract LessonsDao lessonsDao();

    private final static String DB_NAME = "contents.db";
    private static CoursesDB instance;

    public static CoursesDB getInstance(Context context) {
        if(instance==null) {
            instance = Room.databaseBuilder(context, CoursesDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract ContentsDao contentsDao();
    public abstract QuizDao quizDao();
    public abstract AnswersDao answersDao();
    public abstract QuizWithAnswersDao quizWithAnswersDao();
}
