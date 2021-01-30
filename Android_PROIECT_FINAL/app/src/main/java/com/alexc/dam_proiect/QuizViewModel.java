package com.alexc.dam_proiect;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class QuizViewModel extends AndroidViewModel {

    public QuizViewModel(@NonNull Application application) {
        super(application);
        quizRepository = new QuizRepo(application);
    }

    private QuizRepo quizRepository;

    public void insertQuizWithAnswers(QuizWithAnswers quizWithAnswers){ // insertCourseWithStudents
        quizRepository.insert(quizWithAnswers);
    }
}