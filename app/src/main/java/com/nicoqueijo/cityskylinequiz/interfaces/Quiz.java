package com.nicoqueijo.cityskylinequiz.interfaces;

/**
 * A quiz consists of a series of questions. There must be a method to load the next question once
 * a question has been answered.
 */
public interface Quiz {
    void loadNextQuestion();
}
