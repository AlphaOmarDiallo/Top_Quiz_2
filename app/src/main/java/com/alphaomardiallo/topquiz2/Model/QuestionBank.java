package com.alphaomardiallo.topquiz2.Model;

import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        // Shuffle question list before storing it
        mQuestionList = questionList;
        Collections.shuffle(mQuestionList);
    }

    public Question getCurrentQuestion() {
        return mQuestionList.get(mNextQuestionIndex);
    }

    public Question getNextQuestion() {
        // Loop over the questions and return a new one at each call
        mNextQuestionIndex++;
        return getCurrentQuestion();
    }
}
