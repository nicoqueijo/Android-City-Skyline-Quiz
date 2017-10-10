package com.nicoqueijo.cityskylinequiz.interfaces;

/**
 * A quiz consists of a series of questions.
 * There must be a method to load the next question once a question has been answered.
 * There must be a method to record how the user performed on each question.
 */
public interface Quiz {
    void loadNextQuestion();
    void recordAttemptsOfLastQuestion();
}
