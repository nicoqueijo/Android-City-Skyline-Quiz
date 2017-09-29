package com.nicoqueijo.cityskylinequiz.models;

import com.nicoqueijo.cityskylinequiz.activities.QuizGameActivity;

public class QuestionReport {

    public enum Mark {
        CORRECT,
        INCORRECT
    }

    private Question question;
    private int questionNumber;
    private Mark choice1mark;
    private Mark choice2mark;
    private Mark choice3mark;
    private Mark choice4mark;

    public QuestionReport(Question question, int questionNumber) {
        this.question = question;
        this.questionNumber = questionNumber + QuizGameActivity.OFF_BY_ONE;
    }

    public void setCorrectMark(int markNumber) {
        switch (markNumber) {
            case QuizGameActivity.CHOICE_1:
                choice1mark = Mark.CORRECT;
                break;
            case QuizGameActivity.CHOICE_2:
                choice2mark = Mark.CORRECT;
                break;
            case QuizGameActivity.CHOICE_3:
                choice3mark = Mark.CORRECT;
                break;
            case QuizGameActivity.CHOICE_4:
                choice4mark = Mark.CORRECT;
                break;
        }
    }

    public void setIncorrectMark(int markNumber) {
        switch (markNumber) {
            case QuizGameActivity.CHOICE_1:
                choice1mark = Mark.INCORRECT;
                break;
            case QuizGameActivity.CHOICE_2:
                choice2mark = Mark.INCORRECT;
                break;
            case QuizGameActivity.CHOICE_3:
                choice3mark = Mark.INCORRECT;
                break;
            case QuizGameActivity.CHOICE_4:
                choice4mark = Mark.INCORRECT;
                break;
        }
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Mark getChoice1mark() {
        return choice1mark;
    }

    public void setChoice1mark(Mark choice1mark) {
        this.choice1mark = choice1mark;
    }

    public Mark getChoice2mark() {
        return choice2mark;
    }

    public void setChoice2mark(Mark choice2mark) {
        this.choice2mark = choice2mark;
    }

    public Mark getChoice3mark() {
        return choice3mark;
    }

    public void setChoice3mark(Mark choice3mark) {
        this.choice3mark = choice3mark;
    }

    public Mark getChoice4mark() {
        return choice4mark;
    }

    public void setChoice4mark(Mark choice4mark) {
        this.choice4mark = choice4mark;
    }
}
