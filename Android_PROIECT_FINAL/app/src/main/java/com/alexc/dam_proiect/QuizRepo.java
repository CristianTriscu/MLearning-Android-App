package com.alexc.dam_proiect;

import android.app.Application;
import android.os.AsyncTask;

import androidx.room.Database;

public class QuizRepo { // CourseRepository

    private QuizWithAnswersDao quizWithAnswersDao;

    public QuizRepo(Application application) {
        CoursesDB db = CoursesDB.getInstance(application);
        quizWithAnswersDao = db.quizWithAnswersDao();
    }

    public void insert(QuizWithAnswers quizWithAnswers) {
        new insertAsync(quizWithAnswersDao).execute(quizWithAnswers);
    }

    private static class insertAsync extends AsyncTask<QuizWithAnswers, Void, Void> {
        private QuizWithAnswersDao qDaoAsync;

        insertAsync(QuizWithAnswersDao quizWithAnswersDao) {
            qDaoAsync = quizWithAnswersDao;
        }

        @Override
        protected Void doInBackground(QuizWithAnswers... quizWithAnswers) {

            long identifier = qDaoAsync.insertQuiz(quizWithAnswers[0].quizEntity);

            for (AnswersEntity answer : quizWithAnswers[0].answersEntityList) {
                answer.setQuiz_fkid(identifier);
            }
            qDaoAsync.insertAnswers(quizWithAnswers[0].answersEntityList);
            return null;
        }
    }
}
