package com.nicoqueijo.cityskylinequiz.model;

import java.util.List;

/**
 * The model class for each question object.
 * COMPLETE JAVADOC @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 */
public class Question {

    private City correctChoice;
    private City choice1;
    private City choice2;
    private City choice3;
    private City choice4;

    /**
     * JAVADOC THIS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
     */
    public Question(List<City> choices) {
        correctChoice = choices.get(0);
        choice1 = choices.get(1);
        choice2 = choices.get(2);
        choice3 = choices.get(3);
        choice4 = choices.get(4);
    }

    public City getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(City correctChoice) {
        this.correctChoice = correctChoice;
    }

    public City getChoice1() {
        return choice1;
    }

    public void setChoice1(City choice1) {
        this.choice1 = choice1;
    }

    public City getChoice2() {
        return choice2;
    }

    public void setChoice2(City choice2) {
        this.choice2 = choice2;
    }

    public City getChoice3() {
        return choice3;
    }

    public void setChoice3(City choice3) {
        this.choice3 = choice3;
    }

    public City getChoice4() {
        return choice4;
    }

    public void setChoice4(City choice4) {
        this.choice4 = choice4;
    }

}
