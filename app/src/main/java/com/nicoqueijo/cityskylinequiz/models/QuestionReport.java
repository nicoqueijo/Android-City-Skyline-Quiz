package com.nicoqueijo.cityskylinequiz.models;

import com.nicoqueijo.cityskylinequiz.activities.QuizGameActivity;

/**
 * The model class for each question report object. Each question report includes a Question object,
 * a question number, and a correct/incorrect mark for each of the question's choices if applicable.
 */
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

    /**
     * Constructor for the QuestionReport object
     *
     * @param question       the Question belonging to this question report
     * @param questionNumber the question number of this report
     */
    public QuestionReport(Question question, int questionNumber) {
        this.question = question;
        this.questionNumber = questionNumber + QuizGameActivity.OFF_BY_ONE;
    }

    /**
     * Marks the selected question choice as correct
     *
     * @param markNumber the choice which the user selected from the four available
     */
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

    /**
     * Marks the selected question choice as incorrect
     *
     * @param markNumber the choice which the user selected from the four available
     */
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

    /**
     * Accessor for the question belonging to this report
     *
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Mutator for the question belonging to this report
     *
     * @param question the new question
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * Accessor for the question number
     *
     * @return the question number
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     * Mutator for the question number
     *
     * @param questionNumber the new question number
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    /**
     * Accessor for the first choice mark
     *
     * @return the first choice mark
     */
    public Mark getChoice1mark() {
        return choice1mark;
    }

    /**
     * Mutator for the first choice mark
     *
     * @param choice1mark the new first choice mark
     */
    public void setChoice1mark(Mark choice1mark) {
        this.choice1mark = choice1mark;
    }

    /**
     * Accessor for the second choice mark
     *
     * @return the second choice mark
     */
    public Mark getChoice2mark() {
        return choice2mark;
    }

    /**
     * Mutator for the second choice mark
     *
     * @param choice2mark the new second choice mark
     */
    public void setChoice2mark(Mark choice2mark) {
        this.choice2mark = choice2mark;
    }

    /**
     * Accessor for the third choice mark
     *
     * @return the third choice mark
     */
    public Mark getChoice3mark() {
        return choice3mark;
    }

    /**
     * Mutator for the third choice mark
     *
     * @param choice3mark the new third choice mark
     */
    public void setChoice3mark(Mark choice3mark) {
        this.choice3mark = choice3mark;
    }

    /**
     * Accessor for the fourth choice mark
     *
     * @return the fourth choice mark
     */
    public Mark getChoice4mark() {
        return choice4mark;
    }

    /**
     * Mutator for the fourth choice mark
     *
     * @param choice4mark the new fourth choice mark
     */
    public void setChoice4mark(Mark choice4mark) {
        this.choice4mark = choice4mark;
    }
}
