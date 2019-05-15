package com.example.pranay.quiz_trivia;

public class TrueFalse {
    private int mQuestionId;
    private boolean mAnswer;

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }

    public TrueFalse(int questionResourceId, boolean trueFalse)
    {
        mQuestionId = questionResourceId;
        mAnswer = trueFalse;
    }

}
